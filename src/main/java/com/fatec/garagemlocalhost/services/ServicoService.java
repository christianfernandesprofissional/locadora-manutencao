/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fatec.garagemlocalhost.services;

import com.fatec.garagemlocalhost.database.Database;
import com.fatec.garagemlocalhost.model.dao.CategoriaVeiculoDAO;
import com.fatec.garagemlocalhost.model.dao.ServicoDAO;

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
    
}
