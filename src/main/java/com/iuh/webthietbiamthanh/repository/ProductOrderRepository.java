package com.iuh.webthietbiamthanh.repository;

import com.iuh.webthietbiamthanh.models.ProductOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductOrderRepository extends JpaRepository<ProductOrder, Integer> {
    List<ProductOrder> findByUserId(Integer userId);
}
