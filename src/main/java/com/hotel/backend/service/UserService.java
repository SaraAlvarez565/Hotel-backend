package com.hotel.backend.service;

import com.hotel.backend.dto.RegisterRequest;
import com.hotel.backend.model.User;
import com.hotel.backend.repository.UserRepository;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository repo;
    private final PasswordEncoder encoder;
    private final AuthenticationManager authManager;

    public UserService(
            UserRepository repo,
            PasswordEncoder encoder,
            AuthenticationManager authManager
    ) {
        this.repo = repo;
        this.encoder = encoder;
        this.authManager = authManager;
    }

    public List<User> findAll() {
        return repo.findAll();
    }

    public User register(RegisterRequest request) {

        if (repo.findByEmail(request.getEmail()).isPresent()) {
            throw new RuntimeException("Email ya registrado");
        }

        User user = new User();
        user.setName(request.getName());
        user.setLastname(request.getLastname());
        user.setEmail(request.getEmail());

        if (user.getEmail().equalsIgnoreCase("admin@staybloom.com")) {
            user.setRole("ROLE_ADMIN");
        } else {
            user.setRole("ROLE_USER");
        }

        user.setPassword(encoder.encode(request.getPassword()));

        return repo.save(user);
    }

    public User login(String email, String password) {

        Authentication auth = authManager.authenticate(
                new UsernamePasswordAuthenticationToken(email, password)
        );

        SecurityContextHolder.getContext().setAuthentication(auth);

        return repo.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Usuario no existe"));
    }
}