/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fatec.garagemlocalhost.services;

import com.fatec.garagemlocalhost.database.DBException;
import com.fatec.garagemlocalhost.exceptions.CampoVazioException;
import com.fatec.garagemlocalhost.model.dao.UsuarioDAO;
import com.fatec.garagemlocalhost.model.entities.Usuario;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Christian
 */
public class UsuarioService {

    private UsuarioDAO usuarioDao;

    public List<Usuario> buscarTodosUsuarios() throws DBException {
        try {
            return usuarioDao.findAll();
        } catch (SQLException e) {
            throw new DBException("Falha ao encontrar usuários!");
        }
    }

    public List<Usuario> buscarPorNome(String nome)throws DBException{
        try {
            return usuarioDao.findByNome(nome);
        } catch (SQLException e) {
            throw new DBException("Erro ao encontrar usuário!");
        }
    }
    
    public void criarUsuario(Usuario u)throws DBException{
        u.setId(null);
        try{
            if(u.getId() == null){
                throw new DBException("Erro! Usuário não foi criado! ");
            }
            usuarioDao.create(u);

        }catch(SQLException e){
            throw new DBException("Erro ao criar usuário!");
        }
    }
    
     public void atualizarUsuario(Usuario u)throws DBException, CampoVazioException{
        try{    
            if(!checkNull(u)){
                throw new CampoVazioException("O usuário não pode ter campos vazios! ");
            }
            usuarioDao.update(u);
            
        }catch(SQLException e){
            throw new DBException("Erro ao criar usuário!");
        }
    }
     
     
    public void deletarUsuarioPorId(Usuario u) throws DBException{
        try{
            usuarioDao.deleteById(u.getId());
        }catch(SQLException e){
            throw new DBException("Erro ao deletar usuário!");
        }
    }
    
     
    private Boolean checkNull(Usuario u){
         return !(u.getId() == null || u.getEmail().trim().isEmpty() || u.getNome().trim().isEmpty() || 
                 u.getSenha().trim().isEmpty() || u.getTipoUsuario() == null);
     }
}
