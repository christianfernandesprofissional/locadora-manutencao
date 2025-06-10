/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fatec.garagemlocalhost.services;

import com.fatec.garagemlocalhost.database.DBException;
import com.fatec.garagemlocalhost.database.Database;
import com.fatec.garagemlocalhost.exceptions.IdInvalidoException;
import com.fatec.garagemlocalhost.model.dao.DevolucaoVeiculoDAO;
import com.fatec.garagemlocalhost.model.entities.DevolucaoVeiculo;
import com.fatec.garagemlocalhost.utils.Verificar;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author gustavo
 */
public class DevolucaoService {

    private DevolucaoVeiculoDAO devolucaoService;

    public DevolucaoService() {
        Database db = new Database();
        devolucaoService = new DevolucaoVeiculoDAO(db);
    }

    public List<DevolucaoVeiculo> buscarTodasDevolucao() throws DBException {
        //Como se trata de Select simples, nao ha parametros nem motivo para dar erro
        try {
            return devolucaoService.findAll();
        } catch (SQLException e) {
            throw new DBException("Erro ao buscar Devolucoes");
        }
    }

    public Optional<DevolucaoVeiculo> buscaDevolucaoPorId(DevolucaoVeiculo devolucao) throws DBException, IdInvalidoException {
        // A unica verificacao necessaria, e se o ID e negativo ou igual a zero
        try {
            if (devolucao.getIdDevolucao() <= 0) {
                throw new IdInvalidoException("Id nao pode ser menor ou igual a 0 (zero)");
            }
            return devolucaoService.findById(devolucao.getIdDevolucao());
        } catch (SQLException e) {
            throw new DBException("Erro ao buscar Devolucoes por ID");
        }
    }

    public void criarDevolucao(DevolucaoVeiculo devolucao) throws DBException {
        //Ele precisa verificar se o Objeto esta todo preenchido
        try {
            if (!Verificar.todosAtributosPreenchidos(devolucao, "getId")) {
                throw new DBException("Ha algum dado nao preenchido no Objeto");
            }
            devolucaoService.createDevolucao(devolucao);
        }catch(SQLException e){
            throw new DBException("Erro ao criar Devolucao");
        }
    }
    
    public void atualizarDevolucao(DevolucaoVeiculo devolucao) throws DBException{
        //aqui ele precisa ver se o objeto esta todo preenchido
        try{
            if(!Verificar.todosAtributosPreenchidos(devolucao, "getManutencao")){
                throw new DBException("Ha campos vazios ao atualizar");
            }
            devolucaoService.updateDevolucao(devolucao);
            devolucaoService.atualizarPedido(devolucao.getIdDevolucao());
        }catch(SQLException e){
            throw new DBException("Erro ao atualizar Devolucao");
        }       
    }
    
    public void deletarDevolucaoPorId(DevolucaoVeiculo devolucao) throws DBException, IdInvalidoException{
        //Aqui ele precisa verificar se o ID e valido antes de deletar
        try{
            if(devolucao.getIdDevolucao() <= 0){
                throw new IdInvalidoException("Id nao pode ser menor ou igual a 0 (zero)");
            }
            if(!devolucaoService.findById(devolucao.getIdDevolucao()).isPresent()){
                throw new DBException("Devolucao Inexistente");
            }
            devolucaoService.deleteDevolucao(devolucao.getIdDevolucao());
        }catch(SQLException e){
            throw new DBException("Erro ao deletar Devolucao");
        }
    }
    
    public void deletarDevolucaoTudo() throws DBException{
        
    }
}
