package com.fashionmashup.controller;

import com.fashionmashup.dao.ProductDAO;
import com.fashionmashup.dao.impl.ProductDAOImpl;
import com.fashionmashup.model.Product;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.util.List;

@WebServlet("/products")
public class ProductController extends HttpServlet {

    private ProductDAO productDAO = new ProductDAOImpl();

    // 🔹 EXISTING GET (UNCHANGED)
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String categoryIdParam = request.getParameter("categoryId");
        String searchParam = request.getParameter("search");

        List<Product> products;

        if (searchParam != null && !searchParam.isEmpty()) {
            products = productDAO.searchProducts(searchParam);

        } else if (categoryIdParam != null && !categoryIdParam.isEmpty()) {
            int categoryId = Integer.parseInt(categoryIdParam);
            products = productDAO.getProductsByCategory(categoryId);

        } else {
            products = productDAO.getAllProducts();
        }

        request.setAttribute("products", products);
        request.getRequestDispatcher("/WEB-INF/views/products.jsp")
               .forward(request, response);
    }

    // 🔥 NEW: ADD PRODUCT
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            Product product = new Product();

            product.setCategoryId(Integer.parseInt(request.getParameter("categoryId")));
            product.setProductName(request.getParameter("productName"));
            product.setDescription(request.getParameter("description"));
            product.setPrice(Double.parseDouble(request.getParameter("price"))); // 🔥 NEW
            product.setDiscountPercent(Double.parseDouble(request.getParameter("discountPercent")));
            product.setImageUrl(request.getParameter("imageUrl"));
            product.setActive(true);

            boolean status = productDAO.addProduct(product);

            if (status) {
                response.sendRedirect("products");
            } else {
                response.getWriter().println("Error adding product");
            }

        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().println("Invalid input");
        }
    }
}