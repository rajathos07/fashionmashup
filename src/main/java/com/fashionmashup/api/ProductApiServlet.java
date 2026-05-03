package com.fashionmashup.api;

import com.fashionmashup.dao.ProductDAO;
import com.fashionmashup.dao.ProductSizeDAO;
import com.fashionmashup.dao.CategoryDAO;
import com.fashionmashup.dao.impl.ProductDAOImpl;
import com.fashionmashup.dao.impl.ProductSizeDAOImpl;
import com.fashionmashup.dao.impl.CategoryDAOImpl;
import com.fashionmashup.model.Product;
import com.fashionmashup.model.ProductSize;
import com.fashionmashup.model.Category;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.util.*;

@WebServlet("/api/products/*")
public class ProductApiServlet extends HttpServlet {

    private ProductDAO productDAO = new ProductDAOImpl();
    private ProductSizeDAO sizeDAO = new ProductSizeDAOImpl();
    private CategoryDAO categoryDAO = new CategoryDAOImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        try {
            String pathInfo = request.getPathInfo();

            if (pathInfo != null && pathInfo.length() > 1) {
                handleGetById(request, response, pathInfo);
            } else {
                handleGetAll(request, response);
            }
        } catch (Exception e) {
            e.printStackTrace();
            try { JsonUtil.sendError(response, 500, "Server error"); } catch (Exception ignored) {}
        }
    }

    private void handleGetAll(HttpServletRequest request, HttpServletResponse response) throws Exception {
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

        List<Map<String, Object>> result = new ArrayList<>();
        for (Product p : products) {
            result.add(buildProductMap(p));
        }

        JsonUtil.sendJson(response, result);
    }

    private void handleGetById(HttpServletRequest request, HttpServletResponse response, String pathInfo) throws Exception {
        int productId;
        try {
            productId = Integer.parseInt(pathInfo.substring(1));
        } catch (NumberFormatException e) {
            JsonUtil.sendError(response, 400, "Invalid product ID");
            return;
        }

        Product product = productDAO.getProductById(productId);
        if (product == null) {
            JsonUtil.sendError(response, 404, "Product not found");
            return;
        }

        JsonUtil.sendJson(response, buildProductMap(product));
    }

    private Map<String, Object> buildProductMap(Product p) {
        Map<String, Object> item = new LinkedHashMap<>();
        item.put("id", p.getProductId());
        item.put("name", p.getProductName() != null ? p.getProductName() : "");
        item.put("description", p.getDescription() != null ? p.getDescription() : "");
        item.put("price", p.getPrice());
        item.put("image", p.getImageUrl() != null && !p.getImageUrl().isEmpty() ? p.getImageUrl() : getDefaultImage(p.getCategoryId()));
        item.put("categoryId", p.getCategoryId());
        item.put("discountPercent", p.getDiscountPercent());
        item.put("isActive", p.isActive());

        List<ProductSize> sizes = sizeDAO.getSizesByProductId(p.getProductId());
        List<Map<String, Object>> sizeList = new ArrayList<>();
        for (ProductSize s : sizes) {
            Map<String, Object> sizeMap = new LinkedHashMap<>();
            sizeMap.put("id", s.getProductSizeId());
            sizeMap.put("size", s.getSizeLabel() != null ? s.getSizeLabel() : "");
            sizeMap.put("stock", s.getStockQuantity());
            sizeMap.put("available", s.isAvailable());
            sizeList.add(sizeMap);
        }
        item.put("sizes", sizeList);

        int totalStock = sizes.stream().mapToInt(ProductSize::getStockQuantity).sum();
        item.put("stock", totalStock);

        return item;
    }

    private String getDefaultImage(int categoryId) {
        switch (categoryId) {
            case 1: return "https://images.pexels.com/photos/1926769/pexels-photo-1926769.jpeg?auto=compress&cs=tinysrgb&w=600";
            case 2: return "https://images.pexels.com/photos/1440680/pexels-photo-1440680.jpeg?auto=compress&cs=tinysrgb&w=600";
            case 3: return "https://images.pexels.com/photos/1267681/pexels-photo-1267681.jpeg?auto=compress&cs=tinysrgb&w=600";
            default: return "https://images.pexels.com/photos/2769274/pexels-photo-2769274.jpeg?auto=compress&cs=tinysrgb&w=600";
        }
    }
}
