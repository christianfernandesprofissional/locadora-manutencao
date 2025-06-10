/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fatec.garagemlocalhost.services;

import com.fatec.garagemlocalhost.database.DBException;
import com.fatec.garagemlocalhost.database.Database;
import com.fatec.garagemlocalhost.exceptions.CampoVazioException;
import com.fatec.garagemlocalhost.model.dao.ServicoDAO;
import com.fatec.garagemlocalhost.model.entities.Servico;
import com.fatec.garagemlocalhost.utils.Verificar;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author Alber
 */
public class ServicoService {
    
    private ServicoDAO servicoDAO;
    
    public ServicoService() {
        Database db = new Database();
        servicoDAO = new ServicoDAO(db);    
    }
    
    public List<Servico> buscarTodosServicos() throws DBException {
        try {
            return servicoDAO.findAll();
        } catch (SQLException e) {
            throw new DBException("Falha ao encontrar serviços!");
        }
    }
    
    public Optional<Servico> encontrarPeloId(Servico servico) throws DBException, CampoVazioException{
        try {
            if(servico.getIdServico() == null){
                throw new CampoVazioException("O ID do serviço não pode ser vazrio");
            }
            return servicoDAO.findById(servico.getIdServico());
        }
        catch (SQLException e) {
            throw new DBException("Falha ao encontrar serviço!");
        }
    }
    
    public void cadastrarServico(Servico servico) throws DBException, CampoVazioException{
        if(servico.getDescricao() == null || servico.getPreco() == null || servico.getIsComum() == null){
            throw new CampoVazioException("Faltam informações para cadastrar serviço!");
        }        
        try{
            servicoDAO.createServico(servico);
        }catch(SQLException e){
            throw new DBException("Erro ao criar serviço");
        }
    }  
    
    public void atualizarServico(Servico servico) throws DBException, CampoVazioException{
        if(!Verificar.todosAtributosPreenchidos(servico, "getManutencao")){
            throw new CampoVazioException("Faltam informações para atualizar serviço!");
        }
        
        try{
            servicoDAO.updateServico(servico);
        }catch(SQLException e){
            throw new DBException("Erro ao atualizar serviço!");
        }
    }
    
    public void deletarServico(Servico servico)throws DBException, CampoVazioException{
         try{
            if(servico.getIdServico()== null){
                throw new CampoVazioException("Sem informações do serviço!");
            }
            if(!servicoDAO.findById(servico.getIdServico()).isPresent()){
                throw new DBException("Serviço inexistente!");
            }
            
            servicoDAO.deleteServico(servico.getIdServico());
            
         }catch(SQLException e){
             throw new DBException("Erro ao deletar serviço!");
         }
    }
}
