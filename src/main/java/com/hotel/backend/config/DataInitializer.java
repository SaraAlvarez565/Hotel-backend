package com.hotel.backend.config;

import com.hotel.backend.model.User;
import com.hotel.backend.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner initAdmin(
            UserRepository repo,
            PasswordEncoder encoder
    ) {

        return args -> {

            if (repo.findByEmail("admin@staybloom.com").isEmpty()) {

                User admin = new User();

                admin.setName("Admin");
                admin.setLastname("StayBloom");
                admin.setEmail("admin@staybloom.com");
                admin.setPassword(
                        encoder.encode("admin123")
                );
                admin.setRole("ADMIN");

                repo.save(admin);
            }
        };
    }
}