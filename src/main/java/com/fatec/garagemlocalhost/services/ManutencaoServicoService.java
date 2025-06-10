/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fatec.garagemlocalhost.services;

import com.fatec.garagemlocalhost.database.DBException;
import com.fatec.garagemlocalhost.database.Database;
import com.fatec.garagemlocalhost.exceptions.CampoVazioException;
import com.fatec.garagemlocalhost.model.dao.ManutencaoServicoDAO;
import com.fatec.garagemlocalhost.model.entities.Manutencao;
import com.fatec.garagemlocalhost.utils.Verificar;
import java.sql.SQLException;


/**
 *
 * @author gustavo
 */
public class ManutencaoServicoService {
    private ManutencaoServicoDAO manutencaoServicoDao;
    
    public ManutencaoServicoService(){
    Database db = new Database();
    manutencaoServicoDao = new ManutencaoServicoDAO(db);
}
    public void preencherManutencaoServico(Manutencao manutencao) throws DBException, CampoVazioException {
        try{
            if(manutencao.getId() == null || manutencao.getDescricao() == null || manutencao.getInstanteChegada() == null || manutencao.getVeiculo() == null){
                throw new CampoVazioException("Existem Campos vazios no objeto MANUTENCAO, ou ela nao existe");
            }
            manutencaoServicoDao.preencherServicos(manutencao);
        }catch(SQLException e){
            throw new DBException("Erro ao preencher ManutencaoServico");
        }      
    }
    public void insereManutencaoServico(Manutencao manutencao) throws DBException{
        try{
            manutencaoServicoDao.insereServicos(manutencao);
        }catch(SQLException e){
            throw new DBException("Erro ao Inserir Manutencao");
        }
    }
}
