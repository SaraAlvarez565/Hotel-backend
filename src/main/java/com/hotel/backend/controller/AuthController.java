package com.hotel.backend.controller;

import com.hotel.backend.dto.AuthRequest;
import com.hotel.backend.dto.RegisterRequest;
import com.hotel.backend.dto.UserResponse;
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
    public ResponseEntity<UserResponse> register(
            @Valid @RequestBody RegisterRequest request
    ) {

        User user = service.register(request);

        return ResponseEntity.ok(
                new UserResponse(
                        user.getId(),
                        user.getName(),
                        user.getLastname(),
                        user.getEmail(),
                        user.getRole()
                )
        );
    }

    @PostMapping("/login")
    public ResponseEntity<UserResponse> login(
            @Valid @RequestBody AuthRequest request
    ) {

        User user = service.login(
                request.getEmail(),
                request.getPassword()
        );

        return ResponseEntity.ok(
                new UserResponse(
                        user.getId(),
                        user.getName(),
                        user.getLastname(),
                        user.getEmail(),
                        user.getRole()
                )
        );
    }
}