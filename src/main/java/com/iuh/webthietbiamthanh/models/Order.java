package com.iuh.webthietbiamthanh.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name = "Shopping_Cart_Item")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date dateOrder;
    private Double totalPrice;
    private String statusOrder;
    private String addressShipping;

    @ManyToOne
    @JoinColumn(name = "shipping_id")
    private Shipping shipping;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}