package com.iuh.webthietbiamthanh.service;

import com.iuh.webthietbiamthanh.models.Category;
import org.springframework.data.domain.Page;

import java.util.List;

public interface CategoryService {
    public Category saveCategory(Category category);
    public Boolean existCategory(String name);

    public List<Category> getAllCategory();

    public Boolean deleteCategory(int id);

    public Category getCategoryById(int id);

    public List<Category> getAllActiveCategory();

    Page<Category> getAllCategorPagination(Integer pageNo, Integer pageSize);
}
