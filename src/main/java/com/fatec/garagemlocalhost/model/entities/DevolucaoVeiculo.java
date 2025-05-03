/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fatec.garagemlocalhost.model.entities;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 *
 * @author chris
 */
public class DevolucaoVeiculo {
    private Integer idDevolucao;
    private Integer idPedido; 
    private Integer idAssistente;
    private LocalDateTime instanteDevolucao;
    private Integer kmSaida;
    
    public DevolucaoVeiculo(){
        
    }

    public DevolucaoVeiculo(Integer idDevolucao, Integer idPedido, Integer idAssistente, LocalDateTime instanteDevolucao, Integer kmSaida) {
        this.idDevolucao = idDevolucao;
        this.idPedido = idPedido;
        this.idAssistente = idAssistente;
        this.instanteDevolucao = instanteDevolucao;
        this.kmSaida = kmSaida;
    }

    public Integer getIdDevolucao() {
        return idDevolucao;
    }

    public void setIdDevolucao(Integer idDevolucao) {
        this.idDevolucao = idDevolucao;
    }

    public Integer getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(Integer idPedido) {
        this.idPedido = idPedido;
    }

    public Integer getIdAssistente() {
        return idAssistente;
    }

    public void setIdAssistente(Integer idAssistente) {
        this.idAssistente = idAssistente;
    }

    public LocalDateTime getInstanteDevolucao() {
        return instanteDevolucao;
    }

    public void setInstanteDevolucao(LocalDateTime instanteDevolucao) {
        this.instanteDevolucao = instanteDevolucao;
    }

    public Integer getKmSaida() {
        return kmSaida;
    }

    public void setKmSaida(Integer kmSaida) {
        this.kmSaida = kmSaida;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + Objects.hashCode(this.idDevolucao);
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
        final DevolucaoVeiculo other = (DevolucaoVeiculo) obj;
        return Objects.equals(this.idDevolucao, other.idDevolucao);
    }
    
    
    
}
