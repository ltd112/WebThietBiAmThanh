package com.iuh.webthietbiamthanh.service.impl;

import com.iuh.webthietbiamthanh.models.Product;
import com.iuh.webthietbiamthanh.repository.ProductRepository;
import com.iuh.webthietbiamthanh.service.ProductService;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    private ProductRepository productRepository;
    @Override
    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Boolean deleteProduct(Integer id) {
        Product product = productRepository.findById(id).orElse(null);
        if(!ObjectUtils.isEmpty(product)){
            productRepository.delete(product);
            return true;
        }
        return false;
    }

    @Override
    public Product getProductById(Integer id) {
        Product product = productRepository.findById(id).orElse(null);
        return product;
    }

    @Override
    public Product updateProduct(Product product, MultipartFile file) {
        return null;
    }

    @Override
    public List<Product> getAllActiveProducts(String category) {
        List<Product> products = null;
        if(ObjectUtils.isEmpty(category)){
            products = productRepository.findByIsActiveTrue();
        }
        else{
            products = productRepository.findByCategory(category);
        }
        return products;
    }
}
