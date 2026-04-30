package com.fashionmashup.dao;

import java.util.List;
import com.fashionmashup.model.CartItem;

public interface CartItemDAO {

    boolean addCartItem(CartItem cartItem);

    List<CartItem> getCartItemsByCartId(int cartId);

    boolean updateCartItemQuantity(int cartItemId, int quantity);

    boolean removeCartItem(int cartItemId);

    boolean clearCart(int cartId);
}