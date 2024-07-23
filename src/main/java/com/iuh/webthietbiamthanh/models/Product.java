package com.iuh.webthietbiamthanh.models;

import jakarta.persistence.*;

@Entity
@Table(name = "Product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;
    private String image;
    private Double price;
    private Integer quantity;

    @ManyToOne
    @JoinColumn(name = "product_category_id")
    private ProductCategory productCategory;


}