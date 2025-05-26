package com.example.pratica3.model;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "veiculos")
public class Veiculo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String modelo;
    private Integer ano;
    private BigDecimal valor;
    

    public Veiculo() {}

    public Veiculo(String modelo, Integer ano, BigDecimal valor) {
        this.modelo = modelo;
        this.ano = ano;
        this.valor = valor;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getModelo() {
        return modelo;
    }
    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public Integer getAno() {
        return ano;
    }
    public void setAno(Integer ano) {
        this.ano = ano;
    }

    public BigDecimal getValor() {
        return valor;
    }
    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }
}
