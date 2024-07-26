package com.iuh.webthietbiamthanh.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.iuh.webthietbiamthanh.models.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {

    List<Product> findByIsActiveTrue();

    List<Product> findByCategory(String category);

}