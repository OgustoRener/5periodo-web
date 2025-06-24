package com.example.projeto.controller;

import com.example.projeto.model.User;
import com.example.projeto.model.Role;
import com.example.projeto.repository.UserRepository;
import com.example.projeto.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/users")
public class UserRestController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    // Listar todos os usuários
    @GetMapping
    public List<User> listarUsuarios() {
        return userRepository.findAll();
    }

    // Buscar usuário por ID
    @GetMapping("/{id}")
    public ResponseEntity<User> buscarPorId(@PathVariable Long id) {
        return userRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Criar novo usuário
    @PostMapping
    public User criarUsuario(@RequestBody User user) {
        // Garante que roles já existem no banco
        if (user.getRoles() != null) {
            Set<Role> rolesPersistidas = new HashSet<>();
            for (Role role : user.getRoles()) {
                Role roleBanco = roleRepository.findByName(role.getName());
                if (roleBanco != null) {
                    rolesPersistidas.add(roleBanco);
                }
            }
            user.setRoles(rolesPersistidas);
        }
        return userRepository.save(user);
    }

    // Atualizar usuário
    @PutMapping("/{id}")
    public ResponseEntity<User> atualizarUsuario(@PathVariable Long id, @RequestBody User userAtualizado) {
        return userRepository.findById(id)
                .map(user -> {
                    user.setUsername(userAtualizado.getUsername());
                    user.setPassword(userAtualizado.getPassword());
                    if (userAtualizado.getRoles() != null) {
                        Set<Role> rolesPersistidas = new HashSet<>();
                        for (Role role : userAtualizado.getRoles()) {
                            Role roleBanco = roleRepository.findByName(role.getName());
                            if (roleBanco != null) {
                                rolesPersistidas.add(roleBanco);
                            }
                        }
                        user.setRoles(rolesPersistidas);
                    }
                    return ResponseEntity.ok(userRepository.save(user));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    // Deletar usuário
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarUsuario(@PathVariable Long id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}