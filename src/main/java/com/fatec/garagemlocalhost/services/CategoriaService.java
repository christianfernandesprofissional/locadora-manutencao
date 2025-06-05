/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fatec.garagemlocalhost.services;

import com.fatec.garagemlocalhost.database.DBException;
import com.fatec.garagemlocalhost.database.Database;
import com.fatec.garagemlocalhost.exceptions.CampoVazioException;
import com.fatec.garagemlocalhost.exceptions.IdInvalidoException;
import com.fatec.garagemlocalhost.model.dao.CategoriaVeiculoDAO;
import com.fatec.garagemlocalhost.model.entities.CategoriaVeiculo;
import com.fatec.garagemlocalhost.utils.Verificar;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author gustavo
 */
public class CategoriaService {

    private CategoriaVeiculoDAO categoriaVeiculoDao;

    public CategoriaService(){
        Database db = new Database();
        categoriaVeiculoDao = new CategoriaVeiculoDAO(db);
    }

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
        try {
            if(!Verificar.todosAtributosPreenchidos(c, "getId") ){
                throw new DBException("Erro a Categoria nao foi criada, pois existem campos vazios");
            }
                categoriaVeiculoDao.createCategoria(c);     
        } catch (SQLException e) {
            throw new DBException("Erro ao criar Categoria");
        }
    }

    public void atualizarCategoriaVeiculo(CategoriaVeiculo c) throws DBException {
        try {
            //Verificar Retorna TRUE caso todos os valores estejam preenchidos
            if(!Verificar.todosAtributosPreenchidos(c)){
                throw new CampoVazioException("Erro a Categoria nao foi atualizada, pois existem campos vazios");
            }
                categoriaVeiculoDao.updateCategoria(c); 
        } catch (SQLException e) {
            throw new DBException("Erro ao atualizar CategoriaVeiculo");
        }
    }

    public void deletarCategoriaVeiculoPorId(CategoriaVeiculo c, Integer id) throws DBException {
        try {
            if(id < 0){
                throw new DBException("Erro o ID nao pode ser menor que 0 (zero)");
            }
            if(!categoriaVeiculoDao.findById(id).isPresent()){
               throw new DBException("Categoria nao encontrada");
            }
                categoriaVeiculoDao.deleteCategoria(id);      
        } catch (SQLException e) {
            throw new DBException("Erro ao deletar CategoriaVeiculo");
        }
    }

    public void deletarCategoriaVeiculoTudo(CategoriaVeiculo c) throws DBException {
    }
}
