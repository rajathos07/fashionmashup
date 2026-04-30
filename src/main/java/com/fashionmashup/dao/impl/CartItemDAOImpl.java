package com.fashionmashup.dao.impl;

import com.fashionmashup.dao.CartItemDAO;
import com.fashionmashup.model.CartItem;
import com.fashionmashup.util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class CartItemDAOImpl implements CartItemDAO {

    @Override
    public boolean addCartItem(CartItem cartItem) {
        boolean status = false;

        String sql = "INSERT INTO cart_items(cart_id, product_id, size_label, quantity, unit_price, added_at) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, cartItem.getCartId());
            ps.setInt(2, cartItem.getProductId());
            ps.setString(3, cartItem.getSizeLabel());
            ps.setInt(4, cartItem.getQuantity());
            ps.setDouble(5, cartItem.getUnitPrice());
            ps.setTimestamp(6, Timestamp.valueOf(cartItem.getAddedAt()));

            status = ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return status;
    }

    @Override
    public List<CartItem> getCartItemsByCartId(int cartId) {
        List<CartItem> list = new ArrayList<>();

        String sql = "SELECT ci.*, p.product_name FROM cart_items ci " +
                     "JOIN products p ON ci.product_id = p.product_id " +
                     "WHERE ci.cart_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, cartId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                CartItem item = new CartItem();

                item.setCartItemId(rs.getInt("cart_item_id"));
                item.setCartId(rs.getInt("cart_id"));
                item.setProductId(rs.getInt("product_id"));
                item.setSizeLabel(rs.getString("size_label"));
                item.setQuantity(rs.getInt("quantity"));
                item.setUnitPrice(rs.getDouble("unit_price"));
                item.setAddedAt(rs.getTimestamp("added_at").toLocalDateTime());

                // 🔥 NEW
                item.setProductName(rs.getString("product_name"));

                list.add(item);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }
    @Override
    public boolean updateCartItemQuantity(int cartItemId, int quantity) {
        boolean status = false;

        String sql = "UPDATE cart_items SET quantity = ? WHERE cart_item_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, quantity);
            ps.setInt(2, cartItemId);

            status = ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return status;
    }

    @Override
    public boolean removeCartItem(int cartItemId) {
        boolean status = false;

        String sql = "DELETE FROM cart_items WHERE cart_item_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, cartItemId);
            status = ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return status;
    }

    @Override
    public boolean clearCart(int cartId) {
        boolean status = false;

        String sql = "DELETE FROM cart_items WHERE cart_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, cartId);
            status = ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return status;
    }
}