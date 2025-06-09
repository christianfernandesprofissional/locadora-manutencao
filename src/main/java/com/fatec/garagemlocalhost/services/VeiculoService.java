/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fatec.garagemlocalhost.services;

import com.fatec.garagemlocalhost.database.DBException;
import com.fatec.garagemlocalhost.database.Database;
import com.fatec.garagemlocalhost.exceptions.CampoVazioException;
import com.fatec.garagemlocalhost.model.dao.VeiculoDAO;
import com.fatec.garagemlocalhost.model.entities.Veiculo;
import com.fatec.garagemlocalhost.utils.Verificar;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author chris
 */
public class VeiculoService {
    
    private VeiculoDAO veiculoDao;
    
    public VeiculoService(){
        Database db = new Database();
        veiculoDao = new VeiculoDAO(db);
    }
    
    public List<Veiculo> buscarTodosVeiculos()throws DBException{
        try {
            return veiculoDao.findAll();
        } catch (SQLException e) {
            throw new DBException("Falha ao encontrar veículos!");
        }
    }
    
    public Optional<Veiculo> encontrarPelaPlaca(Veiculo veiculo) throws DBException, CampoVazioException{
        try{
            if(veiculo.getPlaca() == null || veiculo.getPlaca().isEmpty()){
                throw new CampoVazioException("Sem informações da placa do veículo!");
            }
            
            return veiculoDao.findByPlaca(veiculo.getPlaca());
        }catch(SQLException e){
            throw new DBException("Falha ao encontrar veículo!");
        }
    }
    
    public void cadastrarVeiculo(Veiculo veiculo) throws DBException, CampoVazioException{
        if(!Verificar.todosAtributosPreenchidos(veiculo)){
            throw new CampoVazioException("Faltam informações para cadastrar veículo!");
        }
        
        try{
            veiculoDao.createVeiculo(veiculo);
        }catch(SQLException e){
            throw new DBException("Erro ao criar veículo");
        }
    }
    
    public void atualizarVeiculo(Veiculo veiculo) throws DBException, CampoVazioException{
        if(!Verificar.todosAtributosPreenchidos(veiculo)){
            throw new CampoVazioException("Faltam informações para cadastrar veículo!");
        }
        
        try{
            veiculoDao.updateVeiculo(veiculo);
        }catch(SQLException e){
            throw new DBException("Erro ao atualizar veículo");
        }
    }
    
    public void deletarVeiculo(Veiculo veiculo)throws DBException, CampoVazioException{
         try{
            if(veiculo.getPlaca() == null || veiculo.getPlaca().isEmpty()){
                throw new CampoVazioException("Sem informações da placa do veículo!");
            }
            if(!veiculoDao.findByPlaca(veiculo.getPlaca()).isPresent()){
                throw new DBException("Veículo inexistente");
            }
            
            veiculoDao.deleteVeiculo(veiculo.getPlaca());
            
         }catch(SQLException e){
             throw new DBException("Erro ao deletar veículo");
         }
    }
    
}
