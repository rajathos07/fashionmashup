package com.fashionmashup.controller;

import com.fashionmashup.dao.*;
import com.fashionmashup.dao.impl.*;
import com.fashionmashup.model.*;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.time.LocalDateTime;

@WebServlet("/add-to-cart")
public class AddToCartController extends HttpServlet {

    private CartDAO cartDAO = new CartDAOImpl();
    private CartItemDAO cartItemDAO = new CartItemDAOImpl();
    private ProductDAO productDAO = new ProductDAOImpl(); // 🔥 added

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        if (user == null) {
            response.sendRedirect("login");
            return;
        }

        int productId = Integer.parseInt(request.getParameter("productId"));
        String size = request.getParameter("size");
        int quantity = Integer.parseInt(request.getParameter("quantity"));

        // 🔥 FETCH PRODUCT
        Product product = productDAO.getProductById(productId);

        Cart cart = cartDAO.getCartByUserId(user.getUserId());

        if (cart == null) {
            cartDAO.createCart(user.getUserId());
            cart = cartDAO.getCartByUserId(user.getUserId());
        }

        CartItem item = new CartItem();
        item.setCartId(cart.getCartId());
        item.setProductId(productId);
        item.setSizeLabel(size);
        item.setQuantity(quantity);

        // 🔥 REAL PRICE USED
        item.setUnitPrice(product.getPrice());

        item.setAddedAt(LocalDateTime.now());

        cartItemDAO.addCartItem(item);

        response.sendRedirect("cart");
    }
}