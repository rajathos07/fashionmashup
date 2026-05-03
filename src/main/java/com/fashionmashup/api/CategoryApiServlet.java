package com.fashionmashup.api;

import com.fashionmashup.dao.CategoryDAO;
import com.fashionmashup.dao.impl.CategoryDAOImpl;
import com.fashionmashup.model.Category;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.util.*;

@WebServlet("/api/categories")
public class CategoryApiServlet extends HttpServlet {

    private CategoryDAO categoryDAO = new CategoryDAOImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        try {
            List<Category> categories = categoryDAO.getAllCategories();
            List<Map<String, Object>> result = new ArrayList<>();
            for (Category c : categories) {
                Map<String, Object> item = new LinkedHashMap<>();
                item.put("id", c.getCategoryId());
                item.put("name", c.getCategoryName() != null ? c.getCategoryName() : "");
                item.put("description", c.getDescription() != null ? c.getDescription() : "");
                item.put("isActive", c.isActive());
                result.add(item);
            }
            JsonUtil.sendJson(response, result);
        } catch (Exception e) {
            e.printStackTrace();
            try { JsonUtil.sendError(response, 500, "Server error"); } catch (Exception ignored) {}
        }
    }
}
