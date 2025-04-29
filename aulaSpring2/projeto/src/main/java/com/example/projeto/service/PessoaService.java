package com.example.projeto.service;

import com.example.projeto.model.Pessoa;
import com.example.projeto.repository.PessoaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PessoaService {

    private final PessoaRepository pessoaRepository;

    // Injeção de dependência via construtor
    public PessoaService(PessoaRepository pessoaRepository) {
        this.pessoaRepository = pessoaRepository;
    }

    @Transactional
    public Pessoa salvar(Pessoa pessoa) {
        // O Spring gerencia a transação automaticamente aqui
        return pessoaRepository.save(pessoa);
    }

    public List<Pessoa> listarTodos() {
        return pessoaRepository.findAll();
    }
}
