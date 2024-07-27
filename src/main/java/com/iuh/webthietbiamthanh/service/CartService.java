package com.iuh.webthietbiamthanh.service;

import com.iuh.webthietbiamthanh.models.Cart;

import java.util.List;

public interface CartService {
    Cart saveCart(Integer productId, Integer userId);

    List<Cart> getCartsByUser(Integer userId);

    Integer getCountCart(Integer userId);
     void updateQuantity(String sy, Integer cid);
}
