package com.example.securityapp;

import com.example.securityapp.user.User;
import com.example.securityapp.user.UserRepository;
import com.example.securityapp.user.UserRole;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class SecurityAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(SecurityAppApplication.class, args);
    }

    @Bean
    public CommandLineRunner initDatabase(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        return args -> {
            // Verifica se o usuário já existe para não duplicar
            if (userRepository.findByUsername("user").isEmpty()) {
                String encodedPassword = passwordEncoder.encode("password123");
                User user = new User("user", encodedPassword, UserRole.USER);
                userRepository.save(user);
                System.out.println("Usuário 'user' criado com senha 'password123'");
            }
        };
    }
}