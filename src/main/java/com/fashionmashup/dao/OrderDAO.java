package com.fashionmashup.dao;

import java.util.List;
import com.fashionmashup.model.Order;

public interface OrderDAO {

    int createOrder(Order order);

    Order getOrderById(int orderId);

    List<Order> getOrdersByUserId(int userId);

    boolean updateOrderStatus(int orderId, String status);

    boolean deleteOrder(int orderId);
    boolean placeOrder(int userId, double totalAmount, String paymentMethod);
}