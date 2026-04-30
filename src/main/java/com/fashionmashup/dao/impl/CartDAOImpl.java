package com.fashionmashup.dao.impl;

import com.fashionmashup.dao.CartDAO;
import com.fashionmashup.model.Cart;
import com.fashionmashup.util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.time.LocalDateTime;

public class CartDAOImpl implements CartDAO {

    @Override
    public Cart getCartByUserId(int userId) {
        Cart cart = null;

        String sql = "SELECT * FROM cart WHERE user_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                cart = new Cart();
                cart.setCartId(rs.getInt("cart_id"));
                cart.setUserId(rs.getInt("user_id"));
                cart.setCreatedAt(rs.getTimestamp("created_at").toLocalDateTime());
                cart.setUpdatedAt(rs.getTimestamp("updated_at").toLocalDateTime());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return cart;
    }

    @Override
    public boolean createCart(int userId) {
        boolean status = false;

        String sql = "INSERT INTO cart(user_id, created_at, updated_at) VALUES (?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            LocalDateTime now = LocalDateTime.now();

            ps.setInt(1, userId);
            ps.setTimestamp(2, Timestamp.valueOf(now));
            ps.setTimestamp(3, Timestamp.valueOf(now));

            status = ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return status;
    }

    @Override
    public boolean updateCart(int cartId) {
        boolean status = false;

        String sql = "UPDATE cart SET updated_at = ? WHERE cart_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setTimestamp(1, Timestamp.valueOf(LocalDateTime.now()));
            ps.setInt(2, cartId);

            status = ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return status;
    }

    @Override
    public boolean deleteCart(int cartId) {
        boolean status = false;

        String sql = "DELETE FROM cart WHERE cart_id = ?";

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