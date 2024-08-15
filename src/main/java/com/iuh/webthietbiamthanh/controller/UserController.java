package com.iuh.webthietbiamthanh.controller;

import com.iuh.webthietbiamthanh.models.*;
import com.iuh.webthietbiamthanh.service.CartService;
import com.iuh.webthietbiamthanh.service.CategoryService;
import com.iuh.webthietbiamthanh.service.OrderService;
import com.iuh.webthietbiamthanh.service.UserService;
import com.iuh.webthietbiamthanh.util.OrderStatus;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private CartService cartService;

    @Autowired
    private OrderService orderService;

    @GetMapping("/")
    public String home() {
        return "user/home";
    }
    @ModelAttribute
    public void getUserDetails(Principal p, Model m) {
        if (p != null) {
            String email = p.getName();
            UserDtls userDtls = userService.getUserByEmail(email);
            m.addAttribute("user", userDtls);
            Integer countCart = cartService.getCountCart(userDtls.getId());
            m.addAttribute("countCart", countCart);
        }

        List<Category> allActiveCategory = categoryService.getAllActiveCategory();
        m.addAttribute("categories", allActiveCategory);
    }
    @GetMapping("/addCart")
    public String addCart(@RequestParam Integer pid, @RequestParam Integer uid, HttpSession session){
        Cart cart = cartService.saveCart(pid, uid);

        if (ObjectUtils.isEmpty(cart)){
            session.setAttribute("errorMsg", "Product add to cart failed");
        }else {
            session.setAttribute("succMsg", "Product added to cart successfully");
        }
        return "redirect:/product/" + pid;
    }

    @GetMapping("/cart")
    public String loadCartPage(Principal p, Model m){
            UserDtls user = getLoggedInUserDetails(p);
            List<Cart> carts = cartService.getCartsByUser(user.getId());
            m.addAttribute("carts", carts);
            if (carts.size() > 0){
                Double totalOrderPrice = carts.stream().mapToDouble(Cart::getTotalPrice).sum();
                m.addAttribute("totalOrderPrice", totalOrderPrice);
            }
        return "user/cart";
    }
    @GetMapping("/cartQuantityUpdate")
    public String updateCartQuantity(@RequestParam String sy, @RequestParam Integer cid){
        cartService.updateQuantity(sy, cid);
        return "redirect:/user/cart";
    }
    private UserDtls getLoggedInUserDetails(Principal p) {
        String email = p.getName();
        UserDtls user = userService.getUserByEmail(email);
        return user;
    }

    @GetMapping("/orders")
    public   String orderPage(Principal p, Model m){
        UserDtls user = getLoggedInUserDetails(p);
        List<Cart> carts = cartService.getCartsByUser(user.getId());
        m.addAttribute("carts", carts);
        if (carts.size() > 0){
            Double orderPrice = carts.stream().mapToDouble(Cart::getTotalPrice).sum();
            Double totalOrderPrice = orderPrice + 300000;
            m.addAttribute("orderPrice", orderPrice);
            m.addAttribute("totalOrderPrice", totalOrderPrice);
        }
        return "user/order";
    }

    @PostMapping("/save-order")
    public String saveOrder(@ModelAttribute OrderRequest orderRequest, Principal p){
        UserDtls user = getLoggedInUserDetails(p);
        orderService.saveOrder(user.getId(), orderRequest);
        return "redirect:/user/success";
    }

    @GetMapping("/success")
    public String successPage(){
        return "user/success";
    }

    @GetMapping("/user-orders")
    public String userOrder(Principal p, Model m){
        UserDtls user = getLoggedInUserDetails(p);
        List<ProductOrder> orders = orderService.getOrdersByUser(user.getId());
        m.addAttribute("orders", orders);
        return "user/my_orders";
    }

    @GetMapping("/update-status")
    public String updateOrderStatus(@RequestParam Integer id, @RequestParam String status, HttpSession session){
        OrderStatus[] orderStatuses = OrderStatus.values();
        String statusName = "";
        for (OrderStatus orderStatus : orderStatuses){
            if (orderStatus.getName().equals(status)){
                statusName = orderStatus.getName();
            }
        }

        boolean updated = orderService.updateOrderStatus(id, statusName);

        if (updated){
            session.setAttribute("succMsg", "Order status updated successfully");
        } else {
            session.setAttribute("errorMsg", "Order status update failed");
        }
        return "redirect:/user/user-orders";
    }

}