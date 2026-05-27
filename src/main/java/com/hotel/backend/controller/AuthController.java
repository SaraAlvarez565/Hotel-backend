package com.hotel.backend.controller;

import com.hotel.backend.model.User;
import com.hotel.backend.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin("*")
public class AuthController {

    private final UserService service;

    public AuthController(UserService service) {
        this.service = service;
    }

    @PostMapping("/register")
    public ResponseEntity<User> register(
            @Valid @RequestBody User user
    ) {

        return ResponseEntity.ok(
                service.register(user)
        );
    }

    @PostMapping("/login")
    public ResponseEntity<User> login(
            @RequestBody User user
    ) {

        return ResponseEntity.ok(
                service.login(
                        user.getEmail(),
                        user.getPassword()
                )
        );
    }
}