package com.example.projeto.controller;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.projeto.model.Pessoa;
import com.example.projeto.service.PessoaService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/pessoas")
public class PessoaWebController {
    
    private final PessoaService pessoaService;

    public PessoaWebController(PessoaService pessoaService) {
        this.pessoaService = pessoaService;
    }

    //mapeia Get /pessoas -> redireciona para /pessoas/listar
    @GetMapping
    public String index(){
        return "redirect:/pessoas/listar";
    }

    //1. Pagina de cadastro
    @GetMapping("/cadastrar")
    public String exibirFormCadastro(Model model){
        model.addAttribute("pessoa", new Pessoa());
        return "pessoas/form";
    }

    @PostMapping("/cadastrar")
    public String cadastrarPessoa(@Valid @ModelAttribute("pessoa") Pessoa pessoa, BindingResult result, RedirectAttributes ra){
        if(result.hasErrors()){
            //repopula o objeto no formulario em caso de erro
            return "pessoas/form";
        }
        pessoaService.salvarPessoa(pessoa);
        ra.addFlashAttribute("mensagem", "Pessoa cadastrada com sucesso!");
        return "redirect:/pessoas/listar";
    }

    //2. Pagina de listagem
    @GetMapping("/listar")
    public String listarPessoas(Model model){
        model.addAttribute("lista", pessoaService.listarPessoas());
        return "pessoas/lista";
    }

    //3. Detalhes e exclusão
    @GetMapping("/{id}")
    public String detalhesPessoa(@PathVariable Long id, Model model){
        Pessoa p = pessoaService.buscarPorId(id).orElseThrow(() -> new ResponseStatusException(
            HttpStatus.NOT_FOUND, "Pessoa não encontrada, id: " + id
        ));
        model.addAttribute("pessoa", p);
        return "pessoas/detalhe";
    }

    @PostMapping("/{id}/excluir")
    public String excluirPessoa(@PathVariable Long id, RedirectAttributes ra){
        pessoaService.deletarPessoa(id);
        ra.addFlashAttribute("sucesso", "Pessoa excluída com sucesso!");
        return "redirect:/pessoas/listar";
    }


}
