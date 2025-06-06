/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package com.fatec.garagemlocalhost.model.enums;

/**
 *
 * @author chris
 */
public enum SituacaoSaida {
    AGUARDANDO_ENTREGA(1), 
    ENTREGUE_AO_CLIENTE(2);
    
    private Integer numero; 
    
    SituacaoSaida(Integer numero){
        this.numero = numero;
    }
    
    public Integer getNumero(){
        return numero;
    }
    
    public static SituacaoSaida setInteiro(Integer numero){
        for(SituacaoSaida s : SituacaoSaida.values()){
            if(s.getNumero() == numero){
                return s;
            }
        }
        throw new IllegalArgumentException("Valor inválido!");
    }
    
}
