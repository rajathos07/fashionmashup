package com.fashionmashup.api;

import java.util.HashMap;
import java.util.Map;

public class JsonBodyParser {

    public static Map<String, String> parse(String body) {
        Map<String, String> map = new HashMap<>();
        if (body == null || body.isEmpty()) return map;
        body = body.trim();
        if (!body.startsWith("{") || !body.endsWith("}")) return map;

        body = body.substring(1, body.length() - 1).trim();

        int i = 0;
        while (i < body.length()) {
            i = skipWhitespace(body, i);

            String key = parseString(body, i);
            if (key == null) break;
            i = skipString(body, i);
            i = skipWhitespace(body, i);

            if (i >= body.length() || body.charAt(i) != ':') break;
            i++;
            i = skipWhitespace(body, i);

            String value = parseValue(body, i);
            int valueEnd = skipValue(body, i);
            i = valueEnd;

            map.put(key, value);

            i = skipWhitespace(body, i);
            if (i < body.length() && body.charAt(i) == ',') i++;
        }

        return map;
    }

    private static int skipWhitespace(String s, int i) {
        while (i < s.length() && Character.isWhitespace(s.charAt(i))) i++;
        return i;
    }

    private static String parseString(String s, int i) {
        if (i >= s.length() || s.charAt(i) != '"') return null;
        StringBuilder sb = new StringBuilder();
        i++;
        while (i < s.length() && s.charAt(i) != '"') {
            if (s.charAt(i) == '\\' && i + 1 < s.length()) {
                i++;
                sb.append(s.charAt(i));
            } else {
                sb.append(s.charAt(i));
            }
            i++;
        }
        return sb.toString();
    }

    private static int skipString(String s, int i) {
        if (i >= s.length() || s.charAt(i) != '"') return i;
        i++;
        while (i < s.length() && s.charAt(i) != '"') {
            if (s.charAt(i) == '\\' && i + 1 < s.length()) i++;
            i++;
        }
        return i + 1;
    }

    private static String parseValue(String s, int i) {
        if (i >= s.length()) return "";

        if (s.charAt(i) == '"') {
            return parseString(s, i) != null ? parseString(s, i) : "";
        }

        if (s.charAt(i) == '{' || s.charAt(i) == '[') {
            return "";
        }

        if (s.substring(i).startsWith("true")) return "true";
        if (s.substring(i).startsWith("false")) return "false";
        if (s.substring(i).startsWith("null")) return "null";

        StringBuilder sb = new StringBuilder();
        while (i < s.length() && s.charAt(i) != ',' && s.charAt(i) != '}' && !Character.isWhitespace(s.charAt(i))) {
            sb.append(s.charAt(i));
            i++;
        }
        return sb.toString().trim();
    }

    private static int skipValue(String s, int i) {
        if (i >= s.length()) return i;

        if (s.charAt(i) == '"') {
            return skipString(s, i);
        }

        if (s.charAt(i) == '{') {
            int depth = 1;
            i++;
            while (i < s.length() && depth > 0) {
                if (s.charAt(i) == '{') depth++;
                else if (s.charAt(i) == '}') depth--;
                else if (s.charAt(i) == '"') i = skipString(s, i) - 1;
                i++;
            }
            return i;
        }

        if (s.charAt(i) == '[') {
            int depth = 1;
            i++;
            while (i < s.length() && depth > 0) {
                if (s.charAt(i) == '[') depth++;
                else if (s.charAt(i) == ']') depth--;
                else if (s.charAt(i) == '"') i = skipString(s, i) - 1;
                i++;
            }
            return i;
        }

        while (i < s.length() && s.charAt(i) != ',' && s.charAt(i) != '}') {
            i++;
        }
        return i;
    }
}
