package com.iuh.webthietbiamthanh.service.impl;

import com.iuh.webthietbiamthanh.models.Cart;
import com.iuh.webthietbiamthanh.models.OrderAddress;
import com.iuh.webthietbiamthanh.models.OrderRequest;
import com.iuh.webthietbiamthanh.models.ProductOrder;
import com.iuh.webthietbiamthanh.repository.ProductOrderRepository;
import com.iuh.webthietbiamthanh.service.CartService;
import com.iuh.webthietbiamthanh.service.OrderService;
import com.iuh.webthietbiamthanh.util.OrderStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private ProductOrderRepository productOrderRepository;
    @Autowired
    private CartService cartRepository;

    @Override
    public void saveOrder(Integer userId, OrderRequest orderRequest) {
        List<Cart> carts = cartRepository.getCartsByUser(userId);
        for (Cart cart : carts) {
            ProductOrder order = new ProductOrder();
            order.setOrderId(UUID.randomUUID().toString());
            order.setOrderDate(LocalDate.now());

            order.setProduct(cart.getProduct());
            order.setPrice(cart.getProduct().getDiscountPrice());

            order.setQuantity(cart.getQuantity());
            order.setUser(cart.getUser());

            order.setStatus(OrderStatus.IN_PROGRESS.getName());
            order.setPaymentType(orderRequest.getPaymentType());

            OrderAddress orderAddress = new OrderAddress();
            orderAddress.setFirstName(orderRequest.getFirstName());
            orderAddress.setLastName(orderRequest.getLastName());
            orderAddress.setAddress(orderRequest.getAddress());
            orderAddress.setCity(orderRequest.getCity());
            orderAddress.setEmail(orderRequest.getEmail());
            orderAddress.setMobileNo(orderRequest.getMobileNo());
            orderAddress.setState(orderRequest.getState());
            orderAddress.setPincode(orderRequest.getPinCode());

            order.setOrderAddress(orderAddress);
            productOrderRepository.save(order);
        }
    }

    @Override
    public List<ProductOrder> getOrdersByUser(Integer userId) {
        List<ProductOrder> orders = productOrderRepository.findByUserId(userId);
        return orders;
    }

    @Override
    public boolean updateOrderStatus(Integer id, String status) {
        Optional<ProductOrder> order = productOrderRepository.findById(id);
        if (order.isPresent()) {
            ProductOrder productOrder = order.get();
            productOrder.setStatus(status);
            productOrderRepository.save(productOrder);
            return true;
        }
        return false;
    }
}
