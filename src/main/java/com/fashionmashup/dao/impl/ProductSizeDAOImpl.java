package com.fashionmashup.dao.impl;

import com.fashionmashup.dao.ProductSizeDAO;
import com.fashionmashup.model.ProductSize;
import com.fashionmashup.util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ProductSizeDAOImpl implements ProductSizeDAO {

    @Override
    public boolean addProductSize(ProductSize productSize) {
        boolean status = false;

        String sql = "INSERT INTO product_sizes(product_id, size_label, stock_quantity, sku_code, is_available) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, productSize.getProductId());
            ps.setString(2, productSize.getSizeLabel());
            ps.setInt(3, productSize.getStockQuantity());
            ps.setString(4, productSize.getSkuCode());
            ps.setBoolean(5, productSize.isAvailable());

            status = ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return status;
    }

    @Override
    public List<ProductSize> getSizesByProductId(int productId) {
        List<ProductSize> list = new ArrayList<>();

        String sql = "SELECT * FROM product_sizes WHERE product_id = ? AND is_available = true";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, productId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                list.add(mapProductSize(rs));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    @Override
    public ProductSize getSizeById(int productSizeId) {
        ProductSize size = null;

        String sql = "SELECT * FROM product_sizes WHERE product_size_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, productSizeId);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                size = mapProductSize(rs);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return size;
    }

    @Override
    public boolean updateProductSize(ProductSize productSize) {
        boolean status = false;

        String sql = "UPDATE product_sizes SET size_label=?, stock_quantity=?, sku_code=?, is_available=? WHERE product_size_id=?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, productSize.getSizeLabel());
            ps.setInt(2, productSize.getStockQuantity());
            ps.setString(3, productSize.getSkuCode());
            ps.setBoolean(4, productSize.isAvailable());
            ps.setInt(5, productSize.getProductSizeId());

            status = ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return status;
    }

    @Override
    public boolean deleteProductSize(int productSizeId) {
        boolean status = false;

        String sql = "DELETE FROM product_sizes WHERE product_size_id=?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, productSizeId);
            status = ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return status;
    }

    // Helper Method
    private ProductSize mapProductSize(ResultSet rs) throws Exception {
        ProductSize size = new ProductSize();
        size.setProductSizeId(rs.getInt("product_size_id"));
        size.setProductId(rs.getInt("product_id"));
        size.setSizeLabel(rs.getString("size_label"));
        size.setStockQuantity(rs.getInt("stock_quantity"));
        size.setSkuCode(rs.getString("sku_code"));
        size.setAvailable(rs.getBoolean("is_available"));
        return size;
    }
}