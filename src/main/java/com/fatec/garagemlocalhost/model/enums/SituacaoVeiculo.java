/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package com.fatec.garagemlocalhost.model.enums;

/**
 *
 * @author chris
 */
public enum SituacaoVeiculo {
    
    DISPONÍVEL(1), 
    ALUGADO(2),
    EM_MANUTENÇÃO(3);
    
    private Integer numero; 
    
    SituacaoVeiculo(Integer numero){
        this.numero = numero;
    }
    
    public Integer getNumero(){
        return numero;
    }
    
    public static SituacaoVeiculo setInteiro(Integer numero){
        for(SituacaoVeiculo s : SituacaoVeiculo.values()){
            if(s.getNumero() == numero){
                return s;
            }
        }
        throw new IllegalArgumentException("Valor inválido!");
    }
    
}
