package com.hotel.backend.controller;

import com.hotel.backend.dto.UserResponse;
import com.hotel.backend.model.User;
import com.hotel.backend.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<UserResponse> getAllUsers() {

        return userService.findAll()
                .stream()
                .map(user -> new UserResponse(
                        user.getId(),
                        user.getName(),
                        user.getLastname(),
                        user.getEmail(),
                        user.getRole()
                ))
                .toList();
    }
}