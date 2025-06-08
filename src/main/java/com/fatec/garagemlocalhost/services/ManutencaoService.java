/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fatec.garagemlocalhost.services;

import com.fatec.garagemlocalhost.database.DBException;
import com.fatec.garagemlocalhost.database.Database;
import com.fatec.garagemlocalhost.exceptions.CampoVazioException;
import com.fatec.garagemlocalhost.exceptions.IdInvalidoException;
import com.fatec.garagemlocalhost.model.dao.ManutencaoDAO;
import com.fatec.garagemlocalhost.model.entities.Manutencao;
import com.fatec.garagemlocalhost.utils.Verificar;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author guuhs
 */
public class ManutencaoService {
    
    private ManutencaoDAO manutencaoServiceDao;
    
    public ManutencaoService(){
        Database db = new Database();
        manutencaoServiceDao = new ManutencaoDAO(db); 
    }
    
    public List<Manutencao> buscarTodasManutencoes() throws DBException{
        try{
           return manutencaoServiceDao.findAll();
        }catch(SQLException e){
            throw new DBException("Erro ao buscar Manutencoes");
        }
}
    public Optional<Manutencao> buscarManutencaoPorId(Manutencao manutencao) throws DBException, IdInvalidoException{
        try{
            if(manutencao.getId() <=0){
                throw new IdInvalidoException("Id nao pode ser menor ou igual a 0 (zero)");
            }
            return manutencaoServiceDao.findById(manutencao.getId());
        }catch(SQLException e ){
            throw new DBException("Erro ao buscar Manutencao por ID");
        }
}
    public void cadastrarManutencao(Manutencao manutencao) throws DBException, IdInvalidoException{
        try{
            if(!Verificar.todosAtributosPreenchidos(manutencao, "getId")){
                throw new IdInvalidoException("Existem campos vazios !");
            }
            manutencaoServiceDao.createManutencao(manutencao);
        }catch(SQLException e){
             throw new DBException("Erro ao criar nova Manutencao");
        }
    }
    public void atualizarManutencao(Manutencao manutencao) throws DBException, IdInvalidoException{
          try{
            if(!Verificar.todosAtributosPreenchidos(manutencao, "getId")){
                throw new IdInvalidoException("Existem campos vazios !");
            }
            manutencaoServiceDao.updateManutencao(manutencao);
        }catch(SQLException e){
             throw new DBException("Erro ao atualizar Manutencao");
        }
    }
    public void deletarManutencao(Manutencao manutencao) throws DBException, CampoVazioException{
        try{
            if(manutencao.getId() == null){
                throw new CampoVazioException("Existem campos que preciam ser preenchidos");
            }
            if(!manutencaoServiceDao.findById(manutencao.getId()).isPresent()){
                throw new DBException("Manutencao inexistente");
            }
            manutencaoServiceDao.deleteManutencao(manutencao.getId());
        }catch(SQLException e){
            throw new DBException("Erro ao deletar Manutencao");
        }
        
    }
}
