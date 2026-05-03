package com.fashionmashup.api;

import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

public class JsonUtil {

    public static void sendJson(HttpServletResponse response, int status, Object data) throws IOException {
        response.setStatus(status);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        out.print(toJson(data));
        out.flush();
    }

    public static void sendJson(HttpServletResponse response, Object data) throws IOException {
        sendJson(response, 200, data);
    }

    public static void sendError(HttpServletResponse response, int status, String message) throws IOException {
        sendJson(response, status, Map.of("error", message));
    }

    public static String toJson(Object obj) {
        if (obj == null) return "null";
        if (obj instanceof String) return "\"" + escape((String) obj) + "\"";
        if (obj instanceof Number || obj instanceof Boolean) return obj.toString();
        if (obj instanceof Map) return mapToJson((Map<?, ?>) obj);
        if (obj instanceof List) return listToJson((List<?>) obj);
        if (obj.getClass().isArray()) return arrayToJson((Object[]) obj);
        return objectToJson(obj);
    }

    private static String mapToJson(Map<?, ?> map) {
        StringBuilder sb = new StringBuilder("{");
        boolean first = true;
        for (Map.Entry<?, ?> entry : map.entrySet()) {
            if (!first) sb.append(",");
            sb.append("\"").append(entry.getKey()).append("\":").append(toJson(entry.getValue()));
            first = false;
        }
        sb.append("}");
        return sb.toString();
    }

    private static String listToJson(List<?> list) {
        StringBuilder sb = new StringBuilder("[");
        boolean first = true;
        for (Object item : list) {
            if (!first) sb.append(",");
            sb.append(toJson(item));
            first = false;
        }
        sb.append("]");
        return sb.toString();
    }

    private static String arrayToJson(Object[] arr) {
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < arr.length; i++) {
            if (i > 0) sb.append(",");
            sb.append(toJson(arr[i]));
        }
        sb.append("]");
        return sb.toString();
    }

    private static String objectToJson(Object obj) {
        StringBuilder sb = new StringBuilder("{");
        java.lang.reflect.Field[] fields = obj.getClass().getDeclaredFields();
        boolean first = true;
        for (java.lang.reflect.Field field : fields) {
            if (java.lang.reflect.Modifier.isStatic(field.getModifiers())) continue;
            field.setAccessible(true);
            try {
                Object value = field.get(obj);
                if (!first) sb.append(",");
                sb.append("\"").append(field.getName()).append("\":").append(toJson(value));
                first = false;
            } catch (IllegalAccessException e) {
                continue;
            }
        }
        sb.append("}");
        return sb.toString();
    }

    private static String escape(String s) {
        return s.replace("\\", "\\\\")
                .replace("\"", "\\\"")
                .replace("\n", "\\n")
                .replace("\r", "\\r")
                .replace("\t", "\\t");
    }
}
