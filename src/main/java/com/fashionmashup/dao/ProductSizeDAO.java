package com.fashionmashup.dao;

import java.util.List;
import com.fashionmashup.model.ProductSize;

public interface ProductSizeDAO {

    boolean addProductSize(ProductSize productSize);

    List<ProductSize> getSizesByProductId(int productId);

    ProductSize getSizeById(int productSizeId);

    boolean updateProductSize(ProductSize productSize);

    boolean deleteProductSize(int productSizeId);
}