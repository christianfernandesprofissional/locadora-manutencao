/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package com.fatec.garagemlocalhost.model.enums;

/**
 *
 * @author chris
 */
public enum SituacaoDevolucao {
    AGUARDANDO_DEVOLUCAO(1), 
    FINALIZADO(2);
    
    private Integer numero; 
    
    SituacaoDevolucao(Integer numero){
        this.numero = numero;
    }
    
    public Integer getNumero(){
        return numero;
    }
    
    public static SituacaoDevolucao setInteiro(Integer numero){
        for(SituacaoDevolucao s : SituacaoDevolucao.values()){
            if(s.getNumero() == numero){
                return s;
            }
        }
        throw new IllegalArgumentException("Valor inválido!");
    }
    
}
