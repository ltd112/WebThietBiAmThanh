package com.iuh.webthietbiamthanh.service;

import com.iuh.webthietbiamthanh.models.OrderRequest;
import com.iuh.webthietbiamthanh.models.ProductOrder;

import java.util.List;

public interface OrderService {
    void saveOrder(Integer userId, OrderRequest orderRequest);
    List<ProductOrder> getOrdersByUser(Integer userId);

    public boolean updateOrderStatus(Integer id, String status);

}
