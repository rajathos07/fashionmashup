package com.fashionmashup.controller;

import com.fashionmashup.dao.UserDAO;
import com.fashionmashup.dao.impl.UserDAOImpl;
import com.fashionmashup.model.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;

@WebServlet("/login")
public class LoginController extends HttpServlet {

    private UserDAO userDAO = new UserDAOImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String email = request.getParameter("email");
        String password = request.getParameter("password");

        User user = userDAO.loginUser(email, password);

        if (user != null) {
            HttpSession session = request.getSession();
            session.setAttribute("user", user);
            
            String redirectPage = (String) session.getAttribute("redirectAfterLogin");

            if(redirectPage != null){
                session.removeAttribute("redirectAfterLogin");
                response.sendRedirect(redirectPage);
            } else {
                response.sendRedirect("products");
            }

//            response.sendRedirect("products"); // next page (we'll build later)
        } else {
            response.sendRedirect("login?error=true");
        }
    }
}