package com.fashionmashup.dao;

import java.util.List;
import com.fashionmashup.model.Product;

public interface ProductDAO {

    boolean addProduct(Product product);

    List<Product> getAllProducts();

    List<Product> getProductsByCategory(int categoryId);

    List<Product> searchProducts(String keyword);

    Product getProductById(int productId);

    boolean updateProduct(Product product);

    boolean deleteProduct(int productId);
}