/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fatec.garagemlocalhost.model.entities;

import com.fatec.garagemlocalhost.model.enums.SituacaoVeiculo;
import java.math.BigDecimal;
import java.util.Objects;

/**
 *
 * @author Christian
 */
public class Veiculo {
    private String placa; 
    private String marca; 
    private String cor; 
    private Integer ano; 
    private String chassi;
    private String modelo; 
    private Integer quilometragem; 
    private CategoriaVeiculo categoria; 
    private BigDecimal precoBase;
    private SituacaoVeiculo situacao;
    
    public Veiculo(){
        
    }

    public Veiculo(String placa, String marca, String cor, Integer ano, String chassi, String modelo, Integer quilometragem, CategoriaVeiculo categoria, BigDecimal precoBase, SituacaoVeiculo situacao) {
        this.placa = placa;
        this.marca = marca;
        this.cor = cor;
        this.ano = ano;
        this.chassi = chassi;
        this.modelo = modelo;
        this.quilometragem = quilometragem;
        this.categoria = categoria;
        this.precoBase = precoBase;
        this.situacao = situacao;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public Integer getAno() {
        return ano;
    }

    public void setAno(Integer ano) {
        this.ano = ano;
    }

    public String getChassi() {
        return chassi;
    }

    public void setChassi(String chassi) {
        this.chassi = chassi;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public Integer getQuilometragem() {
        return quilometragem;
    }

    public void setQuilometragem(Integer quilometragem) {
        this.quilometragem = quilometragem;
    }

    public CategoriaVeiculo getCategoria() {
        return categoria;
    }

    public void setCategoria(CategoriaVeiculo categoria) {
        this.categoria = categoria;
    }

    public BigDecimal getPrecoBase() {
        return precoBase;
    }

    public void setPrecoBase(BigDecimal precoBase) {
        this.precoBase = precoBase;
    }

    public SituacaoVeiculo getSituacao() {
        return situacao;
    }

    public void setSituacao(SituacaoVeiculo situacao) {
        this.situacao = situacao;
    }
    
    

    @Override
    public String toString() {
        return  "Placa: " + placa + ", Marca: " + marca + ", Cor: " + cor + ", Ano: " + ano + ", Chassi: " + chassi + ", Modelo: " + modelo + ", Categoria: " + categoria;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 97 * hash + Objects.hashCode(this.placa);
        hash = 97 * hash + Objects.hashCode(this.chassi);
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
        final Veiculo other = (Veiculo) obj;
        if (!Objects.equals(this.placa, other.placa)) {
            return false;
        }
        return Objects.equals(this.chassi, other.chassi);
    }
    
}
