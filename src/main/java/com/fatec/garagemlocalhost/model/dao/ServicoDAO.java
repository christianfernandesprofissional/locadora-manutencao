/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fatec.garagemlocalhost.model.dao;

import com.fatec.garagemlocalhost.database.Database;
import com.fatec.garagemlocalhost.model.entities.Servico;
import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Classe responsável por fazer o CRUD relacionado
 * a tabela de serviços.
 * 
 * @author Christian
 */
public class ServicoDAO {
    
    private Database database;
    
    public ServicoDAO(Database database){
        this.database = database;
    }
    
    public List<Servico> findAll()throws SQLException{
        List<Servico> servicos = new ArrayList<>();

        String sql = "SELECT * FROM servicos;";
        PreparedStatement ps = database.getConnnection().prepareStatement(sql);

        ResultSet rs = ps.executeQuery();

        while(rs.next()){
            Servico servico = new Servico(
                    rs.getInt("id_servico"),
                    rs.getString("descricao"),
                    BigDecimal.valueOf(rs.getDouble("preco")), 
                    rs.getBoolean("isComum"));
            servicos.add(servico);
        }

        return servicos;
    }
    
    public Optional<Servico> findById(Integer id)throws SQLException{

        String sql = "SELECT * FROM servicos WHERE id_servico = ?;";
        PreparedStatement ps = database.getConnnection().prepareStatement(sql);
        ps.setInt(1, id);

        ResultSet rs = ps.executeQuery();

        Servico servico = null;
        if(rs.next()){
            servico = new Servico(
                    rs.getInt("id_servico"),
                    rs.getString("descricao"),
                    BigDecimal.valueOf(rs.getDouble("preco")), 
                    rs.getBoolean("isComum"));
        }

        return Optional.ofNullable(servico);
    }
    
    public void createServico(Servico servico)throws SQLException{

        String sql = "INSERT INTO servicos(descricao, preco, isComum) values (?,?,?);";
        PreparedStatement ps = database.getConnnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        ps.setString(1, servico.getDescricao());
        ps.setDouble(2, servico.getPreco().doubleValue());
        ps.setBoolean(3, servico.getIsComum());

        int linhas = ps.executeUpdate();

        if(linhas > 0){
            System.out.println("Linhas afetadas: " + linhas);
            ResultSet rs = ps.getGeneratedKeys();
            if(rs.next()){
                servico.setIdServico(rs.getInt(1));
            }
        }
    }
    
    public void updateServico(Servico servico)throws SQLException{

        String sql = "UPDATE servicos SET descricao = ?, preco = ?, isComum = ? WHERE id_servico = ?;";
        PreparedStatement ps = database.getConnnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        ps.setString(1, servico.getDescricao());
        ps.setDouble(2, servico.getPreco().doubleValue());
        ps.setBoolean(3, servico.getIsComum());
        ps.setInt(4, servico.getIdServico());

        int linhas = ps.executeUpdate();

        if(linhas > 0){
            System.out.println("Linhas afetadas: " + linhas);
        }
    }
    
    public void deleteServico(Integer id)throws SQLException{

        String sql = "DELETE FROM servicos WHERE id_servico = ?;";
        PreparedStatement ps = database.getConnnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        ps.setInt(1, id);

        int linhas = ps.executeUpdate();

        if(linhas > 0){
            System.out.println("Linhas afetadas: " + linhas);
        }
    }
}
