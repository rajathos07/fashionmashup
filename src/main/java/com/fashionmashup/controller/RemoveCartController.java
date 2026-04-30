package com.fashionmashup.controller;

import com.fashionmashup.dao.CartItemDAO;
import com.fashionmashup.dao.impl.CartItemDAOImpl;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;

@WebServlet("/remove-cart")
public class RemoveCartController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String idStr = request.getParameter("cartItemId");

        // 🔒 Safety check
        if (idStr == null || idStr.isEmpty()) {
            response.sendRedirect("cart");
            return;
        }

        int cartItemId = Integer.parseInt(idStr);

        CartItemDAO dao = new CartItemDAOImpl();
        dao.removeCartItem(cartItemId);

        response.sendRedirect("cart");
    }
}