// src/main/java/com/example/projeto/controller/UserController.java
package com.example.projeto.controller;

import com.example.projeto.model.User;
import com.example.projeto.model.Role;
import com.example.projeto.repository.UserRepository;
import com.example.projeto.repository.RoleRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.Set;

@Controller
@RequestMapping("/usuarios")
public class UserController {

    private final UserRepository userRepo;
    private final RoleRepository roleRepo;
    private final PasswordEncoder encoder;

    public UserController(UserRepository userRepo,
                          RoleRepository roleRepo,
                          PasswordEncoder encoder) {
        this.userRepo = userRepo;
        this.roleRepo = roleRepo;
        this.encoder = encoder;
    }

    // GET form de cadastro
    @GetMapping("/cadastrar")
    public String showCadastroForm(Model model) {
        model.addAttribute("user", new User());
        return "usuarios/form";
    }

    // POST salva usuário com ROLE_USER
    @PostMapping("/cadastrar")
    @Transactional
    public String doCadastro(@Valid @ModelAttribute("user") User user,
                             BindingResult result) {
        if (result.hasErrors()) {
            return "usuarios/form";
        }
        // codifica senha
        user.setPassword( encoder.encode(user.getPassword()) );
        // atribui perfil USER
        Role rUser = roleRepo.findByName("ROLE_USER")
                      .orElseThrow();
        user.setRoles(Set.of(rUser));
        userRepo.save(user);
        return "redirect:/usuarios/listar";
    }

    // GET lista de usuários (qualquer autenticado)
    @GetMapping("/listar")
    public String listarUsuarios(Model model) {
        model.addAttribute("lista", userRepo.findAll());
        return "usuarios/lista";
    }

    // GET detalhe do usuário (somente ADMIN vê o link)
    @GetMapping("/{id}")
    public String detalheUsuario(@PathVariable("id") Long id, Model model) {
        model.addAttribute("user", userRepo.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("ID inválido")));
        return "usuarios/detalhe";
    }

    // POST excluir (opcional)
    @PostMapping("/{id}/excluir")
    @Transactional
    public String excluirUsuario(@PathVariable("id") Long id) {
        userRepo.deleteById(id);
        return "redirect:/usuarios/listar";
    }
}
