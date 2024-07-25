package com.iuh.webthietbiamthanh.controller;

import com.iuh.webthietbiamthanh.models.UserDtls;
import com.iuh.webthietbiamthanh.service.UserService;
import com.iuh.webthietbiamthanh.service.impl.UserServiceImpl;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Controller
public class HomeController {
    @Autowired
    private UserServiceImpl userService;

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/register")
    public String register() {
        return "register";
    }

    @GetMapping("/products")
    public String product() {
        return "product";
    }

    @GetMapping("/view_product")
    public String view_product() {
        return "view_product";
    }

    //Register controller
    @PostMapping("/saveUser")
    public String saveUser(@ModelAttribute UserDtls user, @RequestParam("img") MultipartFile file, HttpSession session) throws IOException {
        String imageName = file.isEmpty() ? "default.jpg" : file.getOriginalFilename();
        user.setProfileImage(imageName);
        if (userService.getUserByEmail(user.getEmail()) != null) {
            session.setAttribute("errorMsg", "Người dùng đã tồn tại");
            System.out.println("Người dùng đã tồn tại");
            return "redirect:/register";
        }
        UserDtls saveUser = userService.saveUser(user);

        if (!ObjectUtils.isEmpty(saveUser)) {
            if (file.isEmpty()) {
                File saveFile = new ClassPathResource("static/img").getFile();
                Path path = Paths.get(saveFile.getAbsolutePath() + File.separator + "profile_img" + file.getOriginalFilename());
                System.out.println(path);
                Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
            }
            session.setAttribute("succMsg", "Đăng ký thành công");
        } else {
            session.setAttribute("errorMsg", "Đăng ký thất bại");
        }
        return "redirect:/register";
    }
}
