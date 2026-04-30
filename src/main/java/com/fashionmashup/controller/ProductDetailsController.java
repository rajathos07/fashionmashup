package com.fashionmashup.controller;

import com.fashionmashup.dao.ProductDAO;
import com.fashionmashup.dao.ProductSizeDAO;
import com.fashionmashup.dao.impl.ProductDAOImpl;
import com.fashionmashup.dao.impl.ProductSizeDAOImpl;
import com.fashionmashup.model.Product;
import com.fashionmashup.model.ProductSize;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.util.List;

@WebServlet("/product-details")
public class ProductDetailsController extends HttpServlet {

    private ProductDAO productDAO = new ProductDAOImpl();
    private ProductSizeDAO sizeDAO = new ProductSizeDAOImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int productId = Integer.parseInt(request.getParameter("id"));

        Product product = productDAO.getProductById(productId);
        List<ProductSize> sizes = sizeDAO.getSizesByProductId(productId);

        request.setAttribute("product", product);
        request.setAttribute("sizes", sizes);

        request.getRequestDispatcher("/WEB-INF/views/product-details.jsp")
               .forward(request, response);
    }
}