package com.iuh.webthietbiamthanh.repository;

import com.iuh.webthietbiamthanh.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {
    public  Boolean existsByName(String name);
}
