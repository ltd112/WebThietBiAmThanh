package com.iuh.webthietbiamthanh.service;

import com.iuh.webthietbiamthanh.models.Product;
import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ProductService {
    public Product saveProduct(Product product);

    public List<Product> getAllProducts();

    public Boolean deleteProduct(Integer id);

    public Product getProductById(Integer id);

    public Product updateProduct(Product product, MultipartFile file);

    public List<Product> getAllActiveProducts(String category);

    Page<Product> searchProductPagination(Integer pageNo, Integer pageSize, String ch);

    Page<Product> getAllProductsPagination(Integer pageNo, Integer pageSize);
}
