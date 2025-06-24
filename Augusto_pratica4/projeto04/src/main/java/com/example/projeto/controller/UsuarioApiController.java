// src/main/java/com/example/projeto/controller/UsuarioApiController.java
package com.example.projeto.controller;

import com.example.projeto.model.User;
import com.example.projeto.model.Role;
import com.example.projeto.repository.UserRepository;
import com.example.projeto.repository.RoleRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioApiController {

    private final UserRepository userRepo;
    private final RoleRepository roleRepo;
    private final PasswordEncoder encoder;

    public UsuarioApiController(UserRepository userRepo,
                                RoleRepository roleRepo,
                                PasswordEncoder encoder) {
        this.userRepo = userRepo;
        this.roleRepo = roleRepo;
        this.encoder = encoder;
    }

    /** Criar novo usuário (atribui ROLE_USER automaticamente) */
    @PostMapping
    public ResponseEntity<User> criar(@Valid @RequestBody User user) {
        user.setPassword( encoder.encode(user.getPassword()) );
        Role rUser = roleRepo.findByName("ROLE_USER")
                             .orElseThrow(() -> 
                               new IllegalStateException("ROLE_USER não encontrada"));
        user.setRoles(Set.of(rUser));
        User salvo = userRepo.save(user);
        return ResponseEntity.ok(salvo);
    }

    /** Listar todos os usuários */
    @GetMapping
    public List<User> listar() {
        return userRepo.findAll();
    }

    /** Buscar por ID */
    @GetMapping("/{id}")
    public ResponseEntity<User> buscar(@PathVariable Long id) {
        return userRepo.findById(id)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }

    /** Atualizar usuário existente */
    @PutMapping("/{id}")
    public ResponseEntity<User> atualizar(
            @PathVariable Long id,
            @Valid @RequestBody User dto) 
    {
        return userRepo.findById(id).map(user -> {
            user.setUsername(dto.getUsername());
            // Se senha estiver presente, re-encode
            if (dto.getPassword() != null && !dto.getPassword().isBlank()) {
                user.setPassword(encoder.encode(dto.getPassword()));
            }
            User atualizado = userRepo.save(user);
            return ResponseEntity.ok(atualizado);
        }).orElse(ResponseEntity.notFound().build());
    }

    /** Deletar usuário */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        if (!userRepo.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        userRepo.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
