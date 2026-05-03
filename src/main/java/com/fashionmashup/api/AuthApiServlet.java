package com.fashionmashup.api;

import com.fashionmashup.dao.UserDAO;
import com.fashionmashup.dao.impl.UserDAOImpl;
import com.fashionmashup.model.User;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.BufferedReader;
import java.util.Map;

@WebServlet("/api/login")
public class AuthApiServlet extends HttpServlet {

    private UserDAO userDAO = new UserDAOImpl();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        try {
            String body = readBody(request);
            Map<String, String> params = JsonBodyParser.parse(body);

            String email = params.get("email");
            String password = params.get("password");

            if (email == null || password == null) {
                JsonUtil.sendError(response, 400, "Email and password are required");
                return;
            }

            User user = userDAO.loginUser(email, password);

            if (user != null) {
                HttpSession session = request.getSession(true);
                session.setAttribute("user", user);
                JsonUtil.sendJson(response, Map.of(
                    "id", user.getUserId(),
                    "name", user.getFullName() != null ? user.getFullName() : "",
                    "email", user.getEmail() != null ? user.getEmail() : ""
                ));
            } else {
                JsonUtil.sendError(response, 401, "Invalid email or password");
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
