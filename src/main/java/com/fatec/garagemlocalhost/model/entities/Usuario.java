/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fatec.garagemlocalhost.model.entities;

import com.fatec.garagemlocalhost.exceptions.LoginValidacaoException;
import com.fatec.garagemlocalhost.model.enums.TipoUsuario;
import java.util.Objects;

/**
 *
 * @author Christian
 */
public class Usuario {
    private Integer id;
    private String nome;
    private String email;
    private String senha;
    private TipoUsuario tipoUsuario;
    private Boolean ativo;
    
    public Usuario(){
        
    }
    
    public Usuario(String nome, String email, String senha, TipoUsuario tipoUsuario, Boolean ativo) throws LoginValidacaoException {
        id = null;
        this.nome = nome;
        setEmail(email);
        setSenha(senha);
        this.tipoUsuario = tipoUsuario;
        this.ativo = ativo;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) throws LoginValidacaoException {
        if(!email.contains("@") || !email.contains(".com")){
            throw new LoginValidacaoException("Email inválido");
        }
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) throws LoginValidacaoException {
        if(senha.length() < 8){
            throw new LoginValidacaoException("A senha deve ter ao menos 8 digitos");
        }
        this.senha = senha;
    }

    public TipoUsuario getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(TipoUsuario tipoUsuario) throws LoginValidacaoException {
        if(tipoUsuario == null){
            throw new LoginValidacaoException("É preciso definir um nível de acesso");
        }
        this.tipoUsuario = tipoUsuario;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }
    
    

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 37 * hash + Objects.hashCode(this.id);
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
        final Usuario other = (Usuario) obj;
        return Objects.equals(this.id, other.id);
    }

    @Override
    public String toString() {
        return "Usuario{" + "id=" + id + ", nome=" + nome + ", email=" + email + ", senha=" + senha + ", tipoUsuario=" + tipoUsuario + '}';
    }
    
    
}
