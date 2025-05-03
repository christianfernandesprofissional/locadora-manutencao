/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fatec.garagemlocalhost.model.entities;

import java.math.BigDecimal;
import java.util.Objects;

/**
 *
 * @author chris
 */
public class ItemManutencao {
    private Item item; 
    private Manutencao manutencao;
    private Integer quantidade; 
    private BigDecimal valor;

    public ItemManutencao(){
        
    }
    
    public ItemManutencao(Item item, Manutencao manutencao, Integer quantidade, BigDecimal valor) {
        this.item = item;
        this.manutencao = manutencao;
        this.quantidade = quantidade;
        this.valor = valor;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public Manutencao getManutencao() {
        return manutencao;
    }

    public void setManutencao(Manutencao manutencao) {
        this.manutencao = manutencao;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 29 * hash + Objects.hashCode(this.item);
        hash = 29 * hash + Objects.hashCode(this.manutencao);
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
        final ItemManutencao other = (ItemManutencao) obj;
        if (!Objects.equals(this.item, other.item)) {
            return false;
        }
        return Objects.equals(this.manutencao, other.manutencao);
    }
    
    
}
