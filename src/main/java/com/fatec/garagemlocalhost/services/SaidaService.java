/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fatec.garagemlocalhost.services;

import com.fatec.garagemlocalhost.database.DBException;
import com.fatec.garagemlocalhost.database.Database;
import com.fatec.garagemlocalhost.exceptions.CampoVazioException;
import com.fatec.garagemlocalhost.exceptions.IdInvalidoException;
import com.fatec.garagemlocalhost.model.dao.SaidaVeiculoDAO;
import com.fatec.garagemlocalhost.model.entities.SaidaVeiculo;
import com.fatec.garagemlocalhost.utils.Verificar;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author Christian
 */
public class SaidaService {
    
    private SaidaVeiculoDAO saidasDao;
    
    public SaidaService(){
        Database db = new Database();
        saidasDao = new SaidaVeiculoDAO(db);
    }
    
    public List<SaidaVeiculo> buscarTodasAsSaidas()throws DBException{
        try{
            return saidasDao.findAll();
        }catch(SQLException e){
            throw new DBException("Erro ao encontrar as saídas");
        }
    }
    
    public Optional<SaidaVeiculo> buscarSaidaPorId(SaidaVeiculo saida)throws DBException, IdInvalidoException{
           if(saida.getId() <= 0){
               throw new IdInvalidoException("Id inválido!");
           }
           try{
               return saidasDao.findById(saida.getId());
           }catch(SQLException e){
               throw new DBException("Erro ao encontrar as saídas");
           }
    }
    
    public void atualizarSaida(SaidaVeiculo saida)throws DBException, CampoVazioException{
        if(!Verificar.todosAtributosPreenchidos(saida)){
            throw new CampoVazioException("Não podem existir campos vazios");
        }
        try{
            saidasDao.updateSaida(saida);
        }catch(SQLException e){
               throw new DBException("Erro ao atualizar saída!");
           }   
    }
    
    
    
    
}
