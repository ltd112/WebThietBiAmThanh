package com.iuh.webthietbiamthanh.service.impl;

import com.iuh.webthietbiamthanh.models.Cart;
import com.iuh.webthietbiamthanh.models.Product;
import com.iuh.webthietbiamthanh.models.UserDtls;
import com.iuh.webthietbiamthanh.repository.CartRepository;
import com.iuh.webthietbiamthanh.repository.ProductRepository;
import com.iuh.webthietbiamthanh.repository.UserRepository;
import com.iuh.webthietbiamthanh.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.List;

@Service
public class CartServiceImpl implements CartService {
    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductRepository productRepository;

    public Cart saveCart(Integer productId, Integer userId) {
        UserDtls user = userRepository.findById(userId).get();
        Product product = productRepository.findById(productId).get();

        Cart cartStatus = cartRepository.findByProductIdAndUserId(productId, userId);

        Cart cart = null;
        if(ObjectUtils.isEmpty(cartStatus)){
            cart = new Cart();
            cart.setProduct(product);
            cart.setUser(user);
            cart.setQuantity(1);
            cart.setTotalPrice(1 * product.getDiscountPrice());
        }else{
            cart = cartStatus;
            cart.setQuantity(cart.getQuantity() + 1);
            cart.setTotalPrice(cart.getQuantity() * cart.getProduct().getDiscountPrice());
        }
        Cart saveCart = cartRepository.save(cart);

        return saveCart;
    }
    @Override
    public List<Cart> getCartsByUser(Integer userId) {
        List<Cart> carts = cartRepository.findByUserId(userId);

        Double totalOrderPrice = 0.0;
        List<Cart> cartList = new ArrayList<>();
        for (Cart cart : carts) {
            cart.setTotalPrice(cart.getQuantity() * cart.getProduct().getDiscountPrice());
            totalOrderPrice += cart.getTotalPrice();
            cartList.add(cart);
        }
        return cartList;
    }

    @Override
    public Integer getCountCart(Integer userId) {
        Integer countByUserId = cartRepository.countByUserId(userId);
        return countByUserId;
    }

    @Override
    public void updateQuantity(String sy, Integer cid) {
        Cart cart = cartRepository.findById(cid).get();
        int updateQuantity;
        if (sy.equals("plus")) {
            updateQuantity = cart.getQuantity() + 1;
            cart.setQuantity(updateQuantity);
            cartRepository.save(cart);
        } else {
            updateQuantity = cart.getQuantity() - 1;
            if (updateQuantity <= 0) {
                cartRepository.delete(cart);
            } else {
                cart.setQuantity(updateQuantity);
                cartRepository.save(cart);
            }
        }
    }
}
