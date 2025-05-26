package com.example.pratica3.controller;

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

import com.example.pratica3.model.Veiculo;
import com.example.pratica3.service.VeiculoService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/veiculos")
public class VeiculoWebController {
    
    private final VeiculoService veiculoService;

    public VeiculoWebController(VeiculoService veiculoService) {
        this.veiculoService = veiculoService;
    }

    //mapeia Get /veiculos -> redireciona para /veiculos/listar
    @GetMapping
    public String index(){
        return "redirect:/veiculos/listar";
    }

    //1. Pagina de cadastro
    @GetMapping("/cadastrar")
    public String exibirFormCadastro(Model model){
        model.addAttribute("veiculo", new Veiculo());
        return "veiculos/form";
    }

    @PostMapping("/cadastrar")
    public String cadastrarVeiculo(@Valid @ModelAttribute("veiculo") Veiculo veiculo, BindingResult result, RedirectAttributes ra){
        if(result.hasErrors()){
            //repopula o objeto no formulario em caso de erro
            return "veiculos/form";
        }
        veiculoService.salvarVeiculo(veiculo);
        ra.addFlashAttribute("mensagem", "Deu certo de cadastrar o veículo ta!");
        return "redirect:/veiculos/listar";
    }

    //2. Pagina de listagem
    @GetMapping("/listar")
    public String listarVeiculos(Model model){
        model.addAttribute("lista", veiculoService.listarVeiculos());
        return "veiculos/lista";
    }

    //3. Detalhes e exclusão
    @GetMapping("/{id}")
    public String detalhesVeiculo(@PathVariable Long id, Model model){
        Veiculo p = veiculoService.buscarPorId(id).orElseThrow(() -> new ResponseStatusException(
            HttpStatus.NOT_FOUND, "Achou o veiculo não, id: " + id
        ));
        model.addAttribute("veiculo", p);
        return "veiculos/detalhe";
    }

    @PostMapping("/{id}/excluir")
    public String excluirVeiculo(@PathVariable Long id, RedirectAttributes ra){
        veiculoService.deletarVeiculo(id);
        ra.addFlashAttribute("sucesso", "Veiculo foi de vala!");
        return "redirect:/veiculos/listar";
    }


}
