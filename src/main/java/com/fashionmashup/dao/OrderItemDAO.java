package com.fashionmashup.dao;

import java.util.List;
import com.fashionmashup.model.OrderItem;

public interface OrderItemDAO {

    boolean addOrderItem(OrderItem orderItem);

    List<OrderItem> getItemsByOrderId(int orderId);

    boolean updateOrderItem(OrderItem orderItem);

    boolean deleteOrderItem(int orderItemId);
}