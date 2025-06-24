// src/main/java/com/example/projeto/service/CustomUserDetailsService.java
package com.example.projeto.service;

import com.example.projeto.model.User;
import com.example.projeto.repository.UserRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {

        User user = userRepository.findByUsername(username)
            .orElseThrow(() ->
                new UsernameNotFoundException("Usuário não encontrado: " + username)
            );

        return new org.springframework.security.core.userdetails.User(
            user.getUsername(),
            user.getPassword(),
            user.getRoles().stream()
                .map(r -> new SimpleGrantedAuthority(r.getName()))
                .collect(Collectors.toList())
        );
    }
}
