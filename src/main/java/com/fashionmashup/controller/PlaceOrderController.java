package com.fashionmashup.controller;

import com.fashionmashup.dao.*;
import com.fashionmashup.dao.impl.*;
import com.fashionmashup.model.*;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.util.List;

@WebServlet("/place-order")
public class PlaceOrderController extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();

        // ✅ FIX: get USER object
        User user = (User) session.getAttribute("user");

        if (user == null) {
            response.sendRedirect("login");
            return;
        }

        int userId = user.getUserId();

        String paymentMethod = request.getParameter("paymentMethod");

        CartItemDAO cartDAO = new CartItemDAOImpl();
        List<CartItem> cartItems = cartDAO.getCartItemsByCartId(userId);

        if (cartItems == null || cartItems.isEmpty()) {
            response.sendRedirect("cart");
            return;
        }

        double totalAmount = 0;

        for (CartItem item : cartItems) {
            totalAmount += item.getUnitPrice() * item.getQuantity();
        }

        OrderDAO orderDAO = new OrderDAOImpl();

        boolean orderPlaced = orderDAO.placeOrder(userId, totalAmount, paymentMethod);

        if (orderPlaced) {

            // ✅ clear cart
            cartDAO.clearCart(userId);

            // ✅ success page OR orders
            response.sendRedirect("orders");

        } else {
            response.sendRedirect("checkout");
        }
    }
}