package com.fashionmashup.api;

import com.fashionmashup.dao.OrderDAO;
import com.fashionmashup.dao.OrderItemDAO;
import com.fashionmashup.dao.ProductDAO;
import com.fashionmashup.dao.impl.OrderDAOImpl;
import com.fashionmashup.dao.impl.OrderItemDAOImpl;
import com.fashionmashup.dao.impl.ProductDAOImpl;
import com.fashionmashup.model.Order;
import com.fashionmashup.model.OrderItem;
import com.fashionmashup.model.Product;
import com.fashionmashup.model.User;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.util.*;

@WebServlet("/api/orders")
public class OrderApiServlet extends HttpServlet {

    private OrderDAO orderDAO = new OrderDAOImpl();
    private OrderItemDAO orderItemDAO = new OrderItemDAOImpl();
    private ProductDAO productDAO = new ProductDAOImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        try {
            HttpSession session = request.getSession(false);
            if (session == null || session.getAttribute("user") == null) {
                JsonUtil.sendError(response, 401, "Not authenticated");
                return;
            }

            User user = (User) session.getAttribute("user");
            List<Order> orders = orderDAO.getOrdersByUserId(user.getUserId());

            List<Map<String, Object>> result = new ArrayList<>();
            for (Order o : orders) {
                Map<String, Object> orderMap = new LinkedHashMap<>();
                orderMap.put("id", o.getOrderId());
                orderMap.put("createdAt", o.getOrderDate() != null ? o.getOrderDate().toString() : "");
                orderMap.put("totalAmount", o.getTotalAmount());
                orderMap.put("status", o.getOrderStatus() != null ? o.getOrderStatus() : "Pending");
                orderMap.put("paymentMethod", o.getPaymentMethod() != null ? o.getPaymentMethod() : "");
                orderMap.put("deliveryAddress", o.getDeliveryAddress() != null ? o.getDeliveryAddress() : "");

                List<OrderItem> orderItems = orderItemDAO.getItemsByOrderId(o.getOrderId());
                List<Map<String, Object>> itemsList = new ArrayList<>();
                for (OrderItem oi : orderItems) {
                    Product product = productDAO.getProductById(oi.getProductId());

                    Map<String, Object> itemMap = new LinkedHashMap<>();
                    itemMap.put("id", oi.getOrderItemId());
                    itemMap.put("productId", oi.getProductId());
                    itemMap.put("name", oi.getProductName() != null ? oi.getProductName() : "");
                    itemMap.put("quantity", oi.getQuantity());
                    itemMap.put("unitPrice", oi.getUnitPrice());
                    itemMap.put("subtotal", oi.getSubtotal());
                    itemMap.put("size", oi.getSizeLabel() != null ? oi.getSizeLabel() : "");

                    Map<String, Object> productMap = new LinkedHashMap<>();
                    productMap.put("name", oi.getProductName() != null ? oi.getProductName() : "");
                    productMap.put("price", oi.getUnitPrice());
                    if (product != null && product.getImageUrl() != null && !product.getImageUrl().isEmpty()) {
                        productMap.put("image", product.getImageUrl());
                    } else {
                        productMap.put("image", "https://images.pexels.com/photos/2769274/pexels-photo-2769274.jpeg?auto=compress&cs=tinysrgb&w=600");
                    }
                    itemMap.put("product", productMap);

                    itemsList.add(itemMap);
                }
                orderMap.put("items", itemsList);

                result.add(orderMap);
            }

            JsonUtil.sendJson(response, result);
        } catch (Exception e) {
            e.printStackTrace();
            try { JsonUtil.sendError(response, 500, "Server error"); } catch (Exception ignored) {}
        }
    }
}
