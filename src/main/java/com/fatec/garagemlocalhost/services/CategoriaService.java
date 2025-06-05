/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fatec.garagemlocalhost.services;

import com.fatec.garagemlocalhost.database.DBException;
import com.fatec.garagemlocalhost.exceptions.CampoVazioException;
import com.fatec.garagemlocalhost.exceptions.IdInvalidoException;
import com.fatec.garagemlocalhost.model.dao.CategoriaVeiculoDAO;
import com.fatec.garagemlocalhost.model.entities.CategoriaVeiculo;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author gustavo
 */
public class CategoriaService {

    private CategoriaVeiculoDAO categoriaVeiculoDao;

    public List<CategoriaVeiculo> buscarTodasCategorias() throws DBException {
        try {
            return categoriaVeiculoDao.findAll();
        } catch (SQLException e) {
            throw new DBException("Falha ao Encontrar Categorias");
        }
    }
    public Optional<CategoriaVeiculo> buscarPorId(Integer id) throws DBException, IdInvalidoException {
        try {
            if (id <= 0) {
                throw new IdInvalidoException("ID nao pode ser menor que zero");
            }
            return categoriaVeiculoDao.findById(id);
        } catch (SQLException e) {
            throw new DBException("Erro ao encontrar ID da Categoria");
        }
    }
    public void criarCategoriaVeiculo(CategoriaVeiculo c) throws DBException {
        try{        
            //CRIAR A LOGICA DOS CAMPOS, USAR O METODO QUE O CHRISTIAN ESTA FAZENDO
            throw new DBException("Erro a Categoria nao foi criada");
            categoriaVeiculoDao.createCategoria(c);
        }catch(SQLException e) {
            throw new DBException("Erro ao criar Categoria");
        }
    }
    public void atualiarCategoriaVeiculo(CategoriaVeiculo c) throws DBException{
        try{
            throw new CampoVazioException("Preencha todos os campos para prosseguir");
        }catch(SQLException e){
            throw new DBException("Erro ao atualizar CategoriaVeiculo");
        }
    }
    public void deletarCategoriaVeiculoPorId(CategoriaVeiculo  c) throws DBException{
        try{
            
        }catch(SQLException e ){
            throw new DBException("Erro ao deletar CategoriaVeiculo");
        }
    }
    public void deletarCategoriaVeiculoTudo(CategoriaVeiculo c) throws DBException{
        
    }
}
