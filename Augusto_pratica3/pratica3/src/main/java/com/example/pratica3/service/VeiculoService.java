package com.example.pratica3.service;

import org.springframework.stereotype.Service;
import com.example.pratica3.repository.VeiculoRepository;
import com.example.pratica3.model.Veiculo;

import java.util.List;
import java.util.Optional;


@Service
public class VeiculoService{
    private final VeiculoRepository veiculoRepository;

    public VeiculoService(VeiculoRepository veiculoRepository){
        this.veiculoRepository = veiculoRepository;
    }

    public List<Veiculo> listarVeiculos(){
        return veiculoRepository.findAll();
    }

    public Optional<Veiculo> buscarPorId(Long id){
        return veiculoRepository.findById(id);
    }

    public Veiculo salvarVeiculo(Veiculo veiculo){
        return veiculoRepository.save(veiculo);
    }

    public void deletarVeiculo(Long id){
        veiculoRepository.deleteById(id);
    }
}