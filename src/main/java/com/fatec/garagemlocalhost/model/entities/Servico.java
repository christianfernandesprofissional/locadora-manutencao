/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package com.fatec.garagemlocalhost.model.entities;

import java.math.BigDecimal;
import java.util.Objects;

/**
 *
 * @author chris
 */
public class Servico {
    
    private Integer idServico;
    private String descricao;
    private BigDecimal preco;
    private Boolean isComum;
    private Manutencao manutencao;
    
    public Servico(){
        
    }

    public Servico(Integer idServico, String descricao, BigDecimal preco, Boolean isComum) {
        this.idServico = idServico;
        this.preco = preco;
        this.descricao = descricao;
        this.isComum = isComum;
    }

    public Integer getIdServico() {
        return idServico;
    }

    public void setIdServico(Integer idServico) {
        this.idServico = idServico;
    }

    public Manutencao getManutencao() {
        return manutencao;
    }

    public void setManutencao(Manutencao manutencao) {
        this.manutencao = manutencao;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Boolean getIsComum() {
        return isComum;
    }

    public void setIsComum(Boolean isComum) {
        this.isComum = isComum;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + Objects.hashCode(this.idServico);
        hash = 67 * hash + Objects.hashCode(this.manutencao);
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
        final Servico other = (Servico) obj;
        if (!Objects.equals(this.idServico, other.idServico)) {
            return false;
        }
        return Objects.equals(this.manutencao, other.manutencao);
    }

    @Override
    public String toString() {
        return "Servico{" + "idServico=" + idServico + ", manutencao=" + manutencao + ", preco=" + preco + ", descricao=" + descricao + '}';
    }
   
    
}
