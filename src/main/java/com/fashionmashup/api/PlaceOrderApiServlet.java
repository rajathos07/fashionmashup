package com.fashionmashup.api;

import com.fashionmashup.dao.*;
import com.fashionmashup.dao.impl.*;
import com.fashionmashup.model.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.BufferedReader;
import java.util.*;

@WebServlet("/api/placeOrder")
public class PlaceOrderApiServlet extends HttpServlet {

    private CartDAO cartDAO = new CartDAOImpl();
    private CartItemDAO cartItemDAO = new CartItemDAOImpl();
    private OrderDAO orderDAO = new OrderDAOImpl();
    private OrderItemDAO orderItemDAO = new OrderItemDAOImpl();
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
            int userId = user.getUserId();

            String body = readBody(request);
            Map<String, String> params = JsonBodyParser.parse(body);

            String paymentMethod = params.getOrDefault("paymentMethod", "credit_card");
            String shippingAddress = params.getOrDefault("shippingAddress", user.getAddress() != null ? user.getAddress() : "");

            Cart cart = cartDAO.getCartByUserId(userId);
            if (cart == null) {
                JsonUtil.sendError(response, 400, "Cart is empty");
                return;
            }

            List<CartItem> cartItems = cartItemDAO.getCartItemsByCartId(cart.getCartId());
            if (cartItems == null || cartItems.isEmpty()) {
                JsonUtil.sendError(response, 400, "Cart is empty");
                return;
            }

            double totalAmount = 0;
            for (CartItem item : cartItems) {
                totalAmount += item.getUnitPrice() * item.getQuantity();
            }

            Order order = new Order();
            order.setUserId(userId);
            order.setTotalAmount(totalAmount);
            order.setPaymentMethod(paymentMethod);
            order.setOrderStatus("Pending");
            order.setDeliveryAddress(shippingAddress);

            int orderId = orderDAO.createOrder(order);

            if (orderId > 0) {
                for (CartItem item : cartItems) {
                    Product product = productDAO.getProductById(item.getProductId());
                    String productName = (product != null && product.getProductName() != null)
                        ? product.getProductName()
                        : (item.getProductName() != null ? item.getProductName() : "");

                    OrderItem orderItem = new OrderItem();
                    orderItem.setOrderId(orderId);
                    orderItem.setProductId(item.getProductId());
                    orderItem.setProductName(productName);
                    orderItem.setQuantity(item.getQuantity());
                    orderItem.setUnitPrice(item.getUnitPrice());
                    orderItem.setSubtotal(item.getUnitPrice() * item.getQuantity());
                    orderItem.setSizeLabel(item.getSizeLabel() != null ? item.getSizeLabel() : "");
                    orderItemDAO.addOrderItem(orderItem);
                }

                cartItemDAO.clearCart(cart.getCartId());

                JsonUtil.sendJson(response, Map.of(
                    "id", orderId,
                    "message", "Order placed successfully"
                ));
            } else {
                JsonUtil.sendError(response, 500, "Failed to place order");
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
