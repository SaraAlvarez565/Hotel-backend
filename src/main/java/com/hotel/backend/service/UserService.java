package com.hotel.backend.service;

import com.hotel.backend.model.User;
import com.hotel.backend.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository repo;
    private final PasswordEncoder encoder;

    public UserService(UserRepository repo, PasswordEncoder encoder) {
        this.repo = repo;
        this.encoder = encoder;
    }

    public List<User> findAll() {
        return repo.findAll();
    }

    public User register(User user) {

        if (repo.findByEmail(user.getEmail()).isPresent()) {
            throw new RuntimeException("Email ya registrado");
        }

        if (user.getEmail().equalsIgnoreCase("admin@staybloom.com")) {
            user.setRole("ADMIN");
        } else {
            user.setRole("USER");
        }

        user.setPassword(
                encoder.encode(user.getPassword())
        );

        return repo.save(user);
    }

    public User login(String email, String password) {

        User user = repo.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Usuario no existe"));

        boolean matches = encoder.matches(
                password,
                user.getPassword()
        );

        if (!matches) {
            throw new RuntimeException("Contraseña incorrecta");
        }

        return user;
    }
}
