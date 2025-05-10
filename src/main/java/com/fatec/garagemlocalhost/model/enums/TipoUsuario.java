/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package com.fatec.garagemlocalhost.model.enums;

/**
 * Tipos de usuário: AUXILIAR, GERENTE
 * 
 * @author Christian
 */
public enum TipoUsuario {
    AUXILIAR(1),
    GERENTE(2);
    
    private Integer numero; 
    
    TipoUsuario(Integer numero){
        this.numero = numero;
    }
    
    public Integer getNumero(){
        return numero;
    }
    
    public static TipoUsuario setInteiro(Integer numero){
        for(TipoUsuario t : TipoUsuario.values()){
            if(t.getNumero() == numero){
                return t;
            }
        }
        throw new IllegalArgumentException("Valor inválido!");
    }
    
    
}
