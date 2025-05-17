/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fatec.garagemlocalhost.teste;

import com.fatec.garagemlocalhost.database.Database;
import com.fatec.garagemlocalhost.model.dao.CategoriaVeiculoDAO;
import com.fatec.garagemlocalhost.model.dao.DevolucaoVeiculoDAO;
import com.fatec.garagemlocalhost.model.dao.ManutencaoDAO;
import com.fatec.garagemlocalhost.model.dao.SaidaVeiculoDAO;
import com.fatec.garagemlocalhost.model.dao.ServicoDAO;
import com.fatec.garagemlocalhost.model.dao.UsuarioDAO;
import com.fatec.garagemlocalhost.model.dao.VeiculoDAO;

/**
 *
 * @author silvol
 */
public abstract class DaoFactory {
    
    protected UsuarioDAO usuarioDao; 
    protected CategoriaVeiculoDAO categoriaDao;
    protected VeiculoDAO veiculoDao;
    protected ServicoDAO servicoDao; 
    protected SaidaVeiculoDAO saidaDao; 
    protected DevolucaoVeiculoDAO devolucaoDao; 
    protected ManutencaoDAO manutencaoDao; 
    
    public DaoFactory(Database database){
        usuarioDao = new UsuarioDAO(database);
        veiculoDao = new VeiculoDAO(database);
        servicoDao = new ServicoDAO(database);
        saidaDao = new SaidaVeiculoDAO(database);
        devolucaoDao = new DevolucaoVeiculoDAO(database);
        categoriaDao = new CategoriaVeiculoDAO(database);
        manutencaoDao = new ManutencaoDAO(database);
    }
    
}
