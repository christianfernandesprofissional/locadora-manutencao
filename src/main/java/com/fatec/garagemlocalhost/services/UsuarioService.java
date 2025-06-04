/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fatec.garagemlocalhost.services;

import com.fatec.garagemlocalhost.database.DBException;
import com.fatec.garagemlocalhost.database.Database;
import com.fatec.garagemlocalhost.exceptions.CampoVazioException;
import com.fatec.garagemlocalhost.model.dao.UsuarioDAO;
import com.fatec.garagemlocalhost.model.entities.Usuario;
import com.fatec.garagemlocalhost.utils.Verificar;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author Christian
 */
public class UsuarioService {

    private UsuarioDAO usuarioDao;
    
    public UsuarioService(){
        Database db = new Database();
        usuarioDao = new UsuarioDAO(db);
    }

    public List<Usuario> buscarTodosUsuarios() throws DBException {
        try {
            return usuarioDao.findAll();
        } catch (SQLException e) {
            throw new DBException("Falha ao encontrar usuários!");
        }
    }
    
    public Optional<Usuario> buscarPorNome(String nome)throws DBException{
        try {
            return usuarioDao.findByNomeOrEmail(nome);
        } catch (SQLException e) {
            throw new DBException("Erro ao encontrar usuário!");
        }
    }
    
    public void criarUsuario(Usuario u)throws DBException, CampoVazioException{
        u.setId(null);
        try{
            if(!Verificar.todosAtributosPreenchidos(u, "getId")){
                throw new CampoVazioException("A classe contém dados vazios!");
            }
            usuarioDao.create(u);
            
            if(u.getId() == null){
                throw new DBException("Erro! Usuário não foi criado! ");
            }
            
        }catch(SQLException e){
            throw new DBException("Erro ao criar usuário!");
        }
    }
    
     public void atualizarUsuario(Usuario u)throws DBException, CampoVazioException{
        try{    
            if(!Verificar.todosAtributosPreenchidos(u)){
                throw new CampoVazioException("O usuário não pode ter campos vazios! ");
            }
            usuarioDao.update(u);
            
        }catch(SQLException e){
            throw new DBException("Erro ao atualizar usuário! ");
        }
    }
     
     
    public void deletarUsuarioPorId(Usuario u) throws DBException{
        try{
            usuarioDao.deleteById(u.getId());
        }catch(SQLException e){
            throw new DBException("Erro ao deletar usuário!");
        }
    }
    
}
