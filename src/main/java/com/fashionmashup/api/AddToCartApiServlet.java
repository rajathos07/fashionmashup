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
import java.time.LocalDateTime;
import java.util.*;

@WebServlet("/api/addToCart")
public class AddToCartApiServlet extends HttpServlet {

    private CartDAO cartDAO = new CartDAOImpl();
    private CartItemDAO cartItemDAO = new CartItemDAOImpl();
    private ProductDAO productDAO = new ProductDAOImpl();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        try {
            HttpSession session = request.getSession(false);
            if (session == null || session.getAttribute("user") == null) {
                JsonUtil.sendError(response, 401, "Not authenticated");
                return;
            }

            User user = (User) session.getAttribute("user");

            String body = readBody(request);
            Map<String, String> params = JsonBodyParser.parse(body);

            int productId = Integer.parseInt(params.getOrDefault("productId", "0"));
            int quantity = Integer.parseInt(params.getOrDefault("quantity", "1"));
            String size = params.getOrDefault("size", "");

            Product product = productDAO.getProductById(productId);
            if (product == null) {
                JsonUtil.sendError(response, 404, "Product not found");
                return;
            }

            Cart cart = cartDAO.getCartByUserId(user.getUserId());
            if (cart == null) {
                cartDAO.createCart(user.getUserId());
                cart = cartDAO.getCartByUserId(user.getUserId());
            }

            // Check if item already exists in cart
            List<CartItem> existingItems = cartItemDAO.getCartItemsByCartId(cart.getCartId());
            boolean itemExists = false;
            for (CartItem existing : existingItems) {
                if (existing.getProductId() == productId && existing.getSizeLabel().equals(size)) {
                    cartItemDAO.updateCartItemQuantity(existing.getCartItemId(), existing.getQuantity() + quantity);
                    itemExists = true;
                    break;
                }
            }

            if (!itemExists) {
                CartItem item = new CartItem();
                item.setCartId(cart.getCartId());
                item.setProductId(productId);
                item.setSizeLabel(size);
                item.setQuantity(quantity);
                item.setUnitPrice(product.getPrice());
                item.setAddedAt(LocalDateTime.now());
                cartItemDAO.addCartItem(item);
            }

            JsonUtil.sendJson(response, Map.of("message", "Item added to cart"));
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
