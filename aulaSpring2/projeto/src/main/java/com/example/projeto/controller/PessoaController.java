package com.example.projeto.controller;

import com.example.projeto.model.Pessoa;
import com.example.projeto.service.PessoaService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class PessoaController {

    private final PessoaService pessoaService;

    public PessoaController(PessoaService pessoaService) {
        this.pessoaService = pessoaService;
    }

    // Exibe a tela de cadastro
    @GetMapping("/pessoas/cadastrar")
    public String exibirFormularioCadastro(Model model) {
        model.addAttribute("pessoa", new Pessoa());
        return "cadastro";
    }

    // Processa o formulário de cadastro
    @PostMapping("/pessoas/salvar")
    public String salvarPessoa(@ModelAttribute Pessoa pessoa, Model model) {
        pessoaService.salvar(pessoa);
        return "redirect:/pessoas/listar";
    }

    // Exibe a lista de pessoas
    @GetMapping("/pessoas/listar")
    public String listarPessoas(Model model) {
        model.addAttribute("pessoas", pessoaService.listarTodos());
        return "lista";
    }
}
