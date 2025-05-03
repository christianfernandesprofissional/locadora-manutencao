/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fatec.garagemlocalhost.model.entities;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 *
 * @author chris
 */
public class Manutencao {
    private Integer id;
    private Veiculo veiculo;
    private String descricao;
    private Boolean isfinalizado;
    private LocalDateTime instanteChegada; 
    private LocalDateTime instanteSaida; 
    private BigDecimal valor;

    public Manutencao(){
        
    }
    
    public Manutencao(Integer id, Veiculo veiculo, String descricao, Boolean isfinalizado, LocalDateTime instanteChegada, LocalDateTime instanteSaida, BigDecimal valor) {
        this.id = id;
        this.veiculo = veiculo;
        this.descricao = descricao;
        this.isfinalizado = isfinalizado;
        this.instanteChegada = instanteChegada;
        this.instanteSaida = instanteSaida;
        this.valor = valor;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Veiculo getVeiculo() {
        return veiculo;
    }

    public void setVeiculo(Veiculo veiculo) {
        this.veiculo = veiculo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Boolean getIsfinalizado() {
        return isfinalizado;
    }

    public void setIsfinalizado(Boolean isfinalizado) {
        this.isfinalizado = isfinalizado;
    }

    public LocalDateTime getInstanteChegada() {
        return instanteChegada;
    }

    public void setInstanteChegada(LocalDateTime instanteChegada) {
        this.instanteChegada = instanteChegada;
    }

    public LocalDateTime getInstanteSaida() {
        return instanteSaida;
    }

    public void setInstanteSaida(LocalDateTime instanteSaida) {
        this.instanteSaida = instanteSaida;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 19 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Manutencao other = (Manutencao) obj;
        return Objects.equals(this.id, other.id);
    }
    
    
    
    
    
}
