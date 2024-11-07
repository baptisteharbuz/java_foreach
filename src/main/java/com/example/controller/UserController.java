package com.example.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.model.User;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @GetMapping
    public String getAllUsers() {
        return "{\"message\": \"List of users\"}";
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable int id) {
        return new User(id, "User" + id);
    }
}
