package com.hotel.backend.service;

import com.hotel.backend.model.User;
import com.hotel.backend.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository repo;

    public UserService(UserRepository repo) {
        this.repo = repo;
    }

    public User register(User user) {

        Optional<User> existing = repo.findByEmail(user.getEmail());

        if (existing.isPresent()) {
            throw new RuntimeException("Email ya registrado");
        }

        user.setRole("USER");

        return repo.save(user);
    }

    public User login(String email, String password) {

        User user = repo.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Usuario no existe"));

        if (!user.getPassword().equals(password)) {
            throw new RuntimeException("Contraseña incorrecta");
        }

        return user;
    }
}