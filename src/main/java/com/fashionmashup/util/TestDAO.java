package com.fashionmashup.util;

import com.fashionmashup.dao.UserDAO;
import com.fashionmashup.dao.ProductDAO;
import com.fashionmashup.dao.CategoryDAO;

import com.fashionmashup.dao.impl.UserDAOImpl;
import com.fashionmashup.dao.impl.ProductDAOImpl;
import com.fashionmashup.dao.impl.CategoryDAOImpl;

import com.fashionmashup.model.User;
import com.fashionmashup.model.Product;
import com.fashionmashup.model.Category;

import java.util.List;

public class TestDAO {

    public static void main(String[] args) {

        // ========================
        // TEST USER LOGIN
        // ========================
        UserDAO userDAO = new UserDAOImpl();
        User user = userDAO.loginUser("rajath@gmail.com", "1234");

        if (user != null) {
            System.out.println("User Login Successful!");
            System.out.println("Name: " + user.getFullName());
        } else {
            System.out.println("Login Failed!");
        }

        // ========================
        // TEST CATEGORY FETCH
        // ========================
        CategoryDAO categoryDAO = new CategoryDAOImpl();
        List<Category> categories = categoryDAO.getAllCategories();

        System.out.println("\nCategories:");
        for (Category c : categories) {
            System.out.println(c.getCategoryId() + " - " + c.getCategoryName());
        }

        // ========================
        // TEST PRODUCT FETCH
        // ========================
        ProductDAO productDAO = new ProductDAOImpl();
        List<Product> products = productDAO.getAllProducts();

        System.out.println("\nProducts:");
        for (Product p : products) {
            System.out.println(p.getProductId() + " - " + p.getProductName());
        }
    }
}