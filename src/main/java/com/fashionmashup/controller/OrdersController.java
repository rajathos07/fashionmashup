package com.fashionmashup.controller;

import com.fashionmashup.dao.OrderDAO;
import com.fashionmashup.dao.impl.OrderDAOImpl;
import com.fashionmashup.model.Order;
import com.fashionmashup.model.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.util.List;

@WebServlet("/orders")
public class OrdersController extends HttpServlet {

    private OrderDAO orderDAO = new OrderDAOImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        if (user == null) {
            response.sendRedirect("login");
            return;
        }

        List<Order> orders = orderDAO.getOrdersByUserId(user.getUserId());

        request.setAttribute("orders", orders);
        request.getRequestDispatcher("/WEB-INF/views/orders.jsp")
               .forward(request, response);
    }
}