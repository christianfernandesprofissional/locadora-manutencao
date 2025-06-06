/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fatec.garagemlocalhost.utils;

import com.fatec.garagemlocalhost.model.entities.Usuario;

/**
 *
 * @author chris
 */
public class UsuarioHolder {
    private static final UsuarioHolder holder = new UsuarioHolder();
    
    private Usuario usuario;
    
    private UsuarioHolder(){
        
    }
    
    public  void setUsuario(Usuario usuario){
        this.usuario = usuario;
    }
    
    public Usuario getUsuario(){
        return usuario;
    }
    
    public static UsuarioHolder getInstance(){
        return holder;
    }
}
