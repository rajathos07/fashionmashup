package com.fashionmashup.dao;

import java.util.List;
import com.fashionmashup.model.Category;

public interface CategoryDAO {

    boolean addCategory(Category category);

    List<Category> getAllCategories();

    Category getCategoryById(int categoryId);

    boolean updateCategory(Category category);

    boolean deleteCategory(int categoryId);
}