package com.iuh.webthietbiamthanh.repository;

import com.iuh.webthietbiamthanh.models.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartRepository extends JpaRepository<Cart, Integer> {

    Cart findByProductIdAndUserId(Integer productId, Integer userId);
    Integer countByUserId(Integer userId);
    List<Cart> findByUserId(Integer userId);
}
