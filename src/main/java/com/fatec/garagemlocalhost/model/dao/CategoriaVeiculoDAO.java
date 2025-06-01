/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fatec.garagemlocalhost.model.dao;

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
    
    public List<CategoriaVeiculo> findAll() throws  SQLException{
        List<CategoriaVeiculo> categorias = null;
   
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
            
        return categorias;
    }
    
    public Optional<CategoriaVeiculo> findById(Integer id) throws SQLException{
         
        String sql = "SELECT * FROM categorias_veiculos WHERE id_categoria = ?;";
        PreparedStatement ps = database.getConnnection().prepareStatement(sql);

        ps.setInt(1, id);

        ResultSet rs = ps.executeQuery();
        CategoriaVeiculo categoria = null;
        if(rs.next()){
            categoria = new CategoriaVeiculo(rs.getInt("id_categoria"), rs.getString("descricao"));
        }     

        return Optional.ofNullable(categoria);
            
    }
    
    public void createCategoria(CategoriaVeiculo categoria)throws SQLException{
        
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
    }
    public void createWithId(CategoriaVeiculo categoria)throws SQLException{
        
        String sql = "INSERT INTO categorias_veiculos(id_categoria,descricao) VALUES (?,?);";
        PreparedStatement ps = database.getConnnection().prepareStatement(sql);
        ps.setInt(1,categoria.getId());
        ps.setString(2,categoria.getDescricao());
        int  linhas = ps.executeUpdate();

        if(linhas > 0){
            System.out.println("Linhas afetadas: " + linhas);
        }
       
    }
    
    public void updateCategoria(CategoriaVeiculo categoria)throws SQLException{
      
        String sql = "UPDATE FROM categorias_veiculos SET descricao = ? WHERE id_categoria = ?;";
        PreparedStatement ps = database.getConnnection().prepareStatement(sql);

        ps.setString(1, categoria.getDescricao());
        ps.setInt(2, categoria.getId());

        int linhas = ps.executeUpdate();

        if(linhas > 0){
            System.out.println("Linhas afetadas: " + linhas);
        }
    }
    
    public void deleteCategoria(Integer id)throws SQLException{

        String sql = "DELETE FROM categorias_veiculos WHERE id_categoria = ?;";
        PreparedStatement ps = database.getConnnection().prepareStatement(sql);

        ps.setInt(1, id);

        int linhas = ps.executeUpdate();

        if(linhas > 0){
            System.out.println("Linhas afetadas: " + linhas);
        }
    }
    
    public void deleteAll()throws SQLException{

        String sql = "DELETE FROM categorias_veiculos;";
        PreparedStatement ps = database.getConnnection().prepareStatement(sql);

        int linhas = ps.executeUpdate();

        if(linhas > 0){
            System.out.println("Linhas afetadas: " + linhas);
        }
    }
    
    
}
