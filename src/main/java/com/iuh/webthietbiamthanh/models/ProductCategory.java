package com.iuh.webthietbiamthanh.models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "Product_Category")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class ProductCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String categoryName;
}