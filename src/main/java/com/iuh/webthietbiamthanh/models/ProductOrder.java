package com.iuh.webthietbiamthanh.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String orderId;
    private LocalDate orderDate;
    @ManyToOne
    private Product product;
    private Double price;
    private Integer quantity;
    @ManyToOne
    private UserDtls user;
    private String status;
    private String paymentType;
    @OneToOne(cascade = CascadeType.ALL)
    private OrderAddress orderAddress;


}
