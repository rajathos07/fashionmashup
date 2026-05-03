package com.fashionmashup.api;

import com.fashionmashup.model.User;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.util.Map;

@WebServlet("/api/user")
public class UserApiServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        try {
            HttpSession session = request.getSession(false);
            if (session == null || session.getAttribute("user") == null) {
                JsonUtil.sendError(response, 401, "Not authenticated");
                return;
            }

            User user = (User) session.getAttribute("user");
            JsonUtil.sendJson(response, Map.of(
                "id", user.getUserId(),
                "name", user.getFullName() != null ? user.getFullName() : "",
                "email", user.getEmail() != null ? user.getEmail() : ""
            ));
        } catch (Exception e) {
            e.printStackTrace();
            try { JsonUtil.sendError(response, 500, "Server error"); } catch (Exception ignored) {}
        }
    }
}
