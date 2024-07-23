package com.iuh.webthietbiamthanh.controller;

import com.iuh.webthietbiamthanh.dto.request.UserCreationRequest;
import com.iuh.webthietbiamthanh.models.User;
import com.iuh.webthietbiamthanh.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService ;

    @PostMapping()
    public User createUser(@RequestBody UserCreationRequest request){
        return userService.createUser(request);
    }

    @GetMapping()
    public List<User> getAllUser(){
        return userService.getAllUsers();
    }
    @GetMapping("/{userId}")
    public User getUser(@PathVariable String userId){
        return userService.findUserById(userId);
    }

}
