package com.fashionmashup.api;

import com.fashionmashup.dao.UserDAO;
import com.fashionmashup.dao.impl.UserDAOImpl;
import com.fashionmashup.model.User;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.BufferedReader;
import java.util.Map;

@WebServlet("/api/register")
public class RegisterApiServlet extends HttpServlet {

    private UserDAO userDAO = new UserDAOImpl();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        try {
            String body = readBody(request);
            Map<String, String> params = JsonBodyParser.parse(body);

            User user = new User();
            user.setFullName(params.getOrDefault("name", ""));
            user.setEmail(params.getOrDefault("email", ""));
            user.setPassword(params.getOrDefault("password", ""));
            user.setPhone(params.getOrDefault("phone", ""));
            user.setGender(params.getOrDefault("gender", ""));
            user.setAddress(params.getOrDefault("address", ""));

            if (user.getEmail() == null || user.getEmail().isEmpty() ||
                user.getPassword() == null || user.getPassword().isEmpty()) {
                JsonUtil.sendError(response, 400, "Email and password are required");
                return;
            }

            boolean status = userDAO.registerUser(user);

            if (status) {
                User registered = userDAO.loginUser(user.getEmail(), user.getPassword());
                if (registered != null) {
                    HttpSession session = request.getSession(true);
                    session.setAttribute("user", registered);
                    JsonUtil.sendJson(response, Map.of(
                        "id", registered.getUserId(),
                        "name", registered.getFullName() != null ? registered.getFullName() : "",
                        "email", registered.getEmail() != null ? registered.getEmail() : ""
                    ));
                } else {
                    JsonUtil.sendJson(response, Map.of("message", "Registration successful"));
                }
            } else {
                JsonUtil.sendError(response, 400, "Registration failed. Email may already exist.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            try { JsonUtil.sendError(response, 500, "Server error"); } catch (Exception ignored) {}
        }
    }

    private String readBody(HttpServletRequest request) throws Exception {
        BufferedReader reader = request.getReader();
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) sb.append(line);
        return sb.toString();
    }
}
