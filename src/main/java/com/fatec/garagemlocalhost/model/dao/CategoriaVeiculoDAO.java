/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fatec.garagemlocalhost.model.dao;

import com.fatec.garagemlocalhost.database.DBException;
import com.fatec.garagemlocalhost.database.Database;
import com.fatec.garagemlocalhost.model.entities.CategoriaVeiculo;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author Christian
 */
public class CategoriaVeiculoDAO {
    private final Database database;
    
    public CategoriaVeiculoDAO(Database database){
        this.database = database;
    }
    
    public List<CategoriaVeiculo> findAll() throws  DBException{
        List<CategoriaVeiculo> categorias = null;
        try{
            String sql = "SELECT * FROM categorias_veiculos;";
            PreparedStatement ps = database.getConnnection().prepareStatement(sql);
            
            ResultSet rs   = ps.executeQuery();
            categorias = new ArrayList<>();
            while(rs.next()){
                CategoriaVeiculo categoria = new CategoriaVeiculo(rs.getInt("id_categoria"), rs.getString("descricao"));
                categorias.add(categoria);
            }
            
            ps.close();
            rs.close();
            
        }catch(SQLException e){
            throw new DBException("Erro ao encontrar categorias: " + e.getMessage());
        }
        
        return categorias;
    }
    
    public Optional<CategoriaVeiculo> findById(Integer id) throws DBException{
         
        try{
            String sql = "SELECT * FROM categorias_veiculos WHERE id_categoria = ?;";
            PreparedStatement ps = database.getConnnection().prepareStatement(sql);
            
            ps.setInt(1, id);
            
            ResultSet rs = ps.executeQuery();
            CategoriaVeiculo categoria = null;
            if(rs.next()){
                categoria = new CategoriaVeiculo(rs.getInt("id_categoria"), rs.getString("descricao"));
            }     
            
            return Optional.ofNullable(categoria);
            
        }catch(SQLException e){
            throw new DBException("Erro ao encontrar categorias: " + e.getMessage());
        }
    }
    
    public void createCategoria(CategoriaVeiculo categoria)throws DBException{
        if(categoria == null) throw new DBException("Categoria não pode ser nula");
        try{
            String sql = "INSERT INTO categorias_veiculos(descricao) VALUES (?);";
            PreparedStatement ps = database.getConnnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, categoria.getDescricao());
            
            int  linhas = ps.executeUpdate();
            
            if(linhas > 0){
                ResultSet rs = ps.getGeneratedKeys();
                if(rs.next()){
                    categoria.setId(rs.getInt(1));
                }
            }
             
        }catch(SQLException e){
            throw new DBException("Erro ao cadastrar categoria: " + e.getMessage());
        }
    }
    public void createWithId(CategoriaVeiculo categoria)throws DBException{
        if(categoria == null) throw new DBException("Categoria não pode ser nula");
        try{
            String sql = "INSERT INTO categorias_veiculos(id_categoria,descricao) VALUES (?,?);";
            PreparedStatement ps = database.getConnnection().prepareStatement(sql);
            ps.setInt(1,categoria.getId());
            ps.setString(2,categoria.getDescricao());
            int  linhas = ps.executeUpdate();
            
            if(linhas > 0){
                System.out.println("Linhas afetadas: " + linhas);
            }
            
        }catch(SQLException e){
            throw new DBException("Erro ao cadastrar categoria: " + e.getMessage());
        }
    }
    
    public void updateCategoria(CategoriaVeiculo categoria)throws DBException{
        if(categoria == null) throw new DBException("A categoria não pode ser nula!");
        if(categoria.getId() == null) throw new DBException("O id da categoria não pode ser nulo");
        
        try{
            String sql = "UPDATE FROM categorias_veiculos SET descricao = ? WHERE id_categoria = ?;";
            PreparedStatement ps = database.getConnnection().prepareStatement(sql);
            
            ps.setString(1, categoria.getDescricao());
            ps.setInt(2, categoria.getId());
            
            int linhas = ps.executeUpdate();
            
            if(linhas > 0){
                System.out.println("Linhas afetadas: " + linhas);
            }
            
        }catch(SQLException e){
            throw new DBException("Erro ao atualizar categoria: " + e.getMessage());
        }
    }
    
    public void deleteCategoria(Integer id)throws DBException{
        try{
            String sql = "DELETE FROM categorias_veiculos WHERE id_categoria = ?;";
            PreparedStatement ps = database.getConnnection().prepareStatement(sql);
            
            ps.setInt(1, id);
            
            int linhas = ps.executeUpdate();
            
            if(linhas > 0){
                System.out.println("Linhas afetadas: " + linhas);
            }
        }catch(SQLException e){
            throw new DBException("Erro ao deletar categoria: " + e.getMessage());
        }
    }
    public void deleteAll()throws DBException{
        try{
            String sql = "DELETE FROM categorias_veiculos;";
            PreparedStatement ps = database.getConnnection().prepareStatement(sql);
            
            int linhas = ps.executeUpdate();
            
            if(linhas > 0){
                System.out.println("Linhas afetadas: " + linhas);
            }
        }catch(SQLException e){
            throw new DBException("Erro ao deletar categoria: " + e.getMessage());
        }
    }
    
    
}
