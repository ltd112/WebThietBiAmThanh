package com.iuh.webthietbiamthanh.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserController {
    public String home() {
        return "user/home";
    }
}
