package com.iuh.webthietbiamthanh.controller;

import com.iuh.webthietbiamthanh.models.Cart;
import com.iuh.webthietbiamthanh.models.Category;
import com.iuh.webthietbiamthanh.models.UserDtls;
import com.iuh.webthietbiamthanh.service.CartService;
import com.iuh.webthietbiamthanh.service.CategoryService;
import com.iuh.webthietbiamthanh.service.UserService;
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

}