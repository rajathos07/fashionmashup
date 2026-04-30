package com.fashionmashup.controller;

import com.fashionmashup.dao.CartDAO;
import com.fashionmashup.dao.CartItemDAO;
import com.fashionmashup.dao.impl.CartDAOImpl;
import com.fashionmashup.dao.impl.CartItemDAOImpl;
import com.fashionmashup.model.Cart;
import com.fashionmashup.model.CartItem;
import com.fashionmashup.model.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.util.List;

@WebServlet("/cart")
public class CartController extends HttpServlet {

    private CartDAO cartDAO = new CartDAOImpl();
    private CartItemDAO cartItemDAO = new CartItemDAOImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        if (user == null) {
            response.sendRedirect("login");
            return;
        }
        if(session.getAttribute("user") == null){
            session.setAttribute("redirectAfterLogin", "cart"); // ✅ ADD THIS
            response.sendRedirect("login");
            return;
        }
        Cart cart = cartDAO.getCartByUserId(user.getUserId());

        if (cart != null) {
            List<CartItem> items = cartItemDAO.getCartItemsByCartId(cart.getCartId());
            request.setAttribute("cartItems", items);
        }

        request.getRequestDispatcher("/WEB-INF/views/cart.jsp").forward(request, response);
    }
}