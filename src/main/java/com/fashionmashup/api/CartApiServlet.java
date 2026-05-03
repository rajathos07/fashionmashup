package com.fashionmashup.api;

import com.fashionmashup.dao.CartDAO;
import com.fashionmashup.dao.CartItemDAO;
import com.fashionmashup.dao.ProductDAO;
import com.fashionmashup.dao.impl.CartDAOImpl;
import com.fashionmashup.dao.impl.CartItemDAOImpl;
import com.fashionmashup.dao.impl.ProductDAOImpl;
import com.fashionmashup.model.Cart;
import com.fashionmashup.model.CartItem;
import com.fashionmashup.model.Product;
import com.fashionmashup.model.User;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.BufferedReader;
import java.util.*;

@WebServlet("/api/cart/*")
public class CartApiServlet extends HttpServlet {

    private CartDAO cartDAO = new CartDAOImpl();
    private CartItemDAO cartItemDAO = new CartItemDAOImpl();
    private ProductDAO productDAO = new ProductDAOImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        try {
            User user = getAuthenticatedUser(request, response);
            if (user == null) return;

            Cart cart = cartDAO.getCartByUserId(user.getUserId());
            List<Map<String, Object>> items = new ArrayList<>();

            if (cart != null) {
                List<CartItem> cartItems = cartItemDAO.getCartItemsByCartId(cart.getCartId());
                for (CartItem ci : cartItems) {
                    Product product = productDAO.getProductById(ci.getProductId());
                    Map<String, Object> itemMap = new LinkedHashMap<>();
                    itemMap.put("id", ci.getCartItemId());
                    itemMap.put("productId", ci.getProductId());
                    itemMap.put("size", ci.getSizeLabel() != null ? ci.getSizeLabel() : "");
                    itemMap.put("quantity", ci.getQuantity());
                    itemMap.put("unitPrice", ci.getUnitPrice());

                    Map<String, Object> productMap = new LinkedHashMap<>();
                    if (product != null) {
                        productMap.put("id", product.getProductId());
                        productMap.put("name", product.getProductName() != null ? product.getProductName() : "");
                        productMap.put("price", product.getPrice());
                        productMap.put("image", product.getImageUrl() != null && !product.getImageUrl().isEmpty() ? product.getImageUrl() : "https://images.pexels.com/photos/2769274/pexels-photo-2769274.jpeg?auto=compress&cs=tinysrgb&w=600");
                    } else {
                        productMap.put("id", ci.getProductId());
                        productMap.put("name", ci.getProductName() != null ? ci.getProductName() : "");
                        productMap.put("price", ci.getUnitPrice());
                        productMap.put("image", "https://images.pexels.com/photos/2769274/pexels-photo-2769274.jpeg?auto=compress&cs=tinysrgb&w=600");
                    }
                    itemMap.put("product", productMap);

                    items.add(itemMap);
                }
            }

            JsonUtil.sendJson(response, Map.of("items", items));
        } catch (Exception e) {
            e.printStackTrace();
            try { JsonUtil.sendError(response, 500, "Server error"); } catch (Exception ignored) {}
        }
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) {
        try {
            User user = getAuthenticatedUser(request, response);
            if (user == null) return;

            String pathInfo = request.getPathInfo();
            if (pathInfo != null && pathInfo.length() > 1) {
                String idStr = pathInfo.substring(1);
                int cartItemId;
                try {
                    cartItemId = Integer.parseInt(idStr);
                } catch (NumberFormatException e) {
                    JsonUtil.sendError(response, 400, "Invalid cart item ID");
                    return;
                }

                String body = readBody(request);
                Map<String, String> params = JsonBodyParser.parse(body);
                int quantity = Integer.parseInt(params.getOrDefault("quantity", "1"));

                cartItemDAO.updateCartItemQuantity(cartItemId, quantity);
                JsonUtil.sendJson(response, Map.of("message", "Cart item updated"));
            } else {
                JsonUtil.sendError(response, 400, "Cart item ID is required");
            }
        } catch (Exception e) {
            e.printStackTrace();
            try { JsonUtil.sendError(response, 500, "Server error"); } catch (Exception ignored) {}
        }
    }

    private User getAuthenticatedUser(HttpServletRequest request, HttpServletResponse response) throws Exception {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("user") == null) {
            JsonUtil.sendError(response, 401, "Not authenticated");
            return null;
        }
        return (User) session.getAttribute("user");
    }

    private String readBody(HttpServletRequest request) throws Exception {
        BufferedReader reader = request.getReader();
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) sb.append(line);
        return sb.toString();
    }
}
