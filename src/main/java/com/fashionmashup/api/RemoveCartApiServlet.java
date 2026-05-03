package com.fashionmashup.api;

import com.fashionmashup.dao.CartItemDAO;
import com.fashionmashup.dao.impl.CartItemDAOImpl;
import com.fashionmashup.model.User;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.BufferedReader;
import java.util.*;

@WebServlet("/api/removeCart")
public class RemoveCartApiServlet extends HttpServlet {

    private CartItemDAO cartItemDAO = new CartItemDAOImpl();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        try {
            HttpSession session = request.getSession(false);
            if (session == null || session.getAttribute("user") == null) {
                JsonUtil.sendError(response, 401, "Not authenticated");
                return;
            }

            String body = readBody(request);
            Map<String, String> params = JsonBodyParser.parse(body);

            String cartItemIdStr = params.get("cartItemId");
            if (cartItemIdStr == null || cartItemIdStr.isEmpty()) {
                JsonUtil.sendError(response, 400, "Cart item ID is required");
                return;
            }

            int cartItemId = Integer.parseInt(cartItemIdStr);
            cartItemDAO.removeCartItem(cartItemId);

            JsonUtil.sendJson(response, Map.of("message", "Item removed from cart"));
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
