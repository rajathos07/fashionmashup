package com.fashionmashup.api;

import com.fashionmashup.model.User;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.util.Map;

@WebServlet("/api/logout")
public class LogoutApiServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        try {
            HttpSession session = request.getSession(false);
            if (session != null) {
                session.invalidate();
            }
            JsonUtil.sendJson(response, Map.of("message", "Logged out successfully"));
        } catch (Exception e) {
            e.printStackTrace();
            try { JsonUtil.sendError(response, 500, "Server error"); } catch (Exception ignored) {}
        }
    }
}
