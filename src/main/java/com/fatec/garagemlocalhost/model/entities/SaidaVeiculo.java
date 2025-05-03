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
public class SaidaVeiculo {
    private Integer id; 
    private Integer idPedido; 
    private Usuario usuario; 
    private LocalDateTime instanteSaida; 
    private Integer kmSaida;
    
    public SaidaVeiculo(){
        
    }

    public SaidaVeiculo(Integer id, Integer idPedido, Usuario usuario, LocalDateTime instanteSaida, Integer kmSaida) {
        this.id = id;
        this.idPedido = idPedido;
        this.usuario = usuario;
        this.instanteSaida = instanteSaida;
        this.kmSaida = kmSaida;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(Integer idPedido) {
        this.idPedido = idPedido;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public LocalDateTime getInstanteSaida() {
        return instanteSaida;
    }

    public void setInstanteSaida(LocalDateTime instanteSaida) {
        this.instanteSaida = instanteSaida;
    }

    public Integer getKmSaida() {
        return kmSaida;
    }

    public void setKmSaida(Integer kmSaida) {
        this.kmSaida = kmSaida;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 53 * hash + Objects.hashCode(this.id);
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
        final SaidaVeiculo other = (SaidaVeiculo) obj;
        return Objects.equals(this.id, other.id);
    }
    
    
}
