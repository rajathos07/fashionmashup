package com.fashionmashup.dao;

import com.fashionmashup.model.Cart;

public interface CartDAO {

    Cart getCartByUserId(int userId);

    boolean createCart(int userId);

    boolean updateCart(int cartId);

    boolean deleteCart(int cartId);
}