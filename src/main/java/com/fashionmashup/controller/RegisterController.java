package com.fashionmashup.controller;

import com.fashionmashup.dao.UserDAO;
import com.fashionmashup.dao.impl.UserDAOImpl;
import com.fashionmashup.model.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;

@WebServlet("/register")
public class RegisterController extends HttpServlet {

    private UserDAO userDAO = new UserDAOImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.getRequestDispatcher("/WEB-INF/views/register.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        User user = new User();

        user.setFullName(request.getParameter("fullName"));
        user.setEmail(request.getParameter("email"));
        user.setPhone(request.getParameter("phone"));
        user.setPassword(request.getParameter("password"));
        user.setGender(request.getParameter("gender"));
        user.setAddress(request.getParameter("address"));

        boolean status = userDAO.registerUser(user);

        if (status) {
            response.sendRedirect("login");
        } else {
            response.sendRedirect("register?error=true");
        }
    }
}