/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fatec.garagemlocalhost.model.entities;

import com.fatec.garagemlocalhost.model.enums.SituacaoDevolucao;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 *
 * @author chris
 */
public class DevolucaoVeiculo {
    private Integer idDevolucao;
    private Integer idPedido; 
    private LocalDateTime instanteDevolucao;
    private Integer kmChegada;
    
    private Usuario assistente;
    private Veiculo veiculo;
    private Manutencao manutencao;
    
    private SituacaoDevolucao situacao;
    
    public DevolucaoVeiculo(){
        
    }

    public DevolucaoVeiculo(Integer idDevolucao, Integer idPedido, LocalDateTime instanteDevolucao, Integer kmChegada, Usuario assistente, Veiculo veiculo, Manutencao manutencao) {
        this.idDevolucao = idDevolucao;
        this.idPedido = idPedido;
        this.instanteDevolucao = instanteDevolucao;
        this.kmChegada = kmChegada;
        this.assistente = assistente;
        this.veiculo = veiculo;
        this.manutencao = manutencao;
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

    public Usuario getAssistente() {
        return assistente;
    }

    public void setAssistente(Usuario assistente) {
        this.assistente = assistente;
    }

    public LocalDateTime getInstanteDevolucao() {
        return instanteDevolucao;
    }

    public void setInstanteDevolucao(LocalDateTime instanteDevolucao) {
        this.instanteDevolucao = instanteDevolucao;
    }

    public Integer getKmChegada() {
        return kmChegada;
    }

    public void setKmChegada(Integer kmChegada) {
        this.kmChegada = kmChegada;
    }

    public Veiculo getVeiculo() {
        return veiculo;
    }

    public void setVeiculo(Veiculo veiculo) {
        this.veiculo = veiculo;
    }

    public Manutencao getManutencao() {
        return manutencao;
    }

    public void setManutencao(Manutencao manutencao) {
        this.manutencao = manutencao;
    }

    public SituacaoDevolucao getSituacao() {
        return situacao;
    }

    public void setSituacao(SituacaoDevolucao situacao) {
        this.situacao = situacao;
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
