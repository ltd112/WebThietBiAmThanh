package com.iuh.webthietbiamthanh.util;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@AllArgsConstructor
public enum OrderStatus {
    IN_PROGRESS(1, "InProgress"), ORDER_RECEIVED(2, "Order Received"), PRODUCT_PACKED(3, "Product Packed"),
    OUT_FOR_DELIVERY(4, "Out for Delivery"), DELIVERED(5, "Delivered"),CANCEL(6,"Cancelled");
    private Integer id;
    private String name;
}
