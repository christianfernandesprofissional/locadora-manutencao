/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fatec.garagemlocalhost.model.dao;

import com.fatec.garagemlocalhost.database.DBException;
import com.fatec.garagemlocalhost.database.Database;
import com.fatec.garagemlocalhost.model.entities.SaidaVeiculo;
import com.fatec.garagemlocalhost.model.entities.Usuario;
import com.fatec.garagemlocalhost.model.entities.Veiculo;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Classe responsável pelo CRUD da tabela saidas_veiculos
 * 
 * @author Christian
 */
public class SaidaVeiculoDAO {
    
    private Database database;

    public SaidaVeiculoDAO(Database database) {
        this.database = database;
    }
    
    public List<SaidaVeiculo> findAll()throws DBException{
        List<SaidaVeiculo> saidas = new ArrayList<>();
        try{
            String sql = "SELECT * FROM saidas_veiculos;";
            PreparedStatement ps = database.getConnnection().prepareStatement(sql);
            
            ResultSet rs = ps.executeQuery();
            
            VeiculoDAO vDAO = new VeiculoDAO(database);
            UsuarioDAO uDAO = new UsuarioDAO(database);
            while(rs.next()){
                SaidaVeiculo saida = new SaidaVeiculo();
                saida.setId(rs.getInt("id_saida"));
                saida.setIdPedido(rs.getInt("id_pedido"));
                Usuario usuario = uDAO.findById(rs.getInt("id_assistente")).get();
                saida.setUsuario(usuario);
                Veiculo veiculo = vDAO.findByPlaca(rs.getString("placa")).get();
                saida.setVeiculo(veiculo);
                saida.setKmSaida(rs.getInt("km_saida"));
                saidas.add(saida);
            }
        }catch(SQLException e){
            throw new DBException("Erro ao buscar saídas: " + e.getMessage());
        }
        return saidas;
    }
    
    public Optional<SaidaVeiculo> findById(Integer id)throws DBException{
        try{
            String sql = "SELECT * FROM saidas_veiculos WHERE id_saida = ?;";
            PreparedStatement ps = database.getConnnection().prepareStatement(sql);
            ps.setInt(1, id);
            
            ResultSet rs = ps.executeQuery();
            
            VeiculoDAO vDAO = new VeiculoDAO(database);
            UsuarioDAO uDAO = new UsuarioDAO(database);
            SaidaVeiculo saida = null;
            if(rs.next()){
                saida = new SaidaVeiculo();
                saida.setId(rs.getInt("id_saida"));
                saida.setIdPedido(rs.getInt("id_pedido"));
                Usuario usuario = uDAO.findById(rs.getInt("id_assistente")).get();
                saida.setUsuario(usuario);
                Veiculo veiculo = vDAO.findByPlaca(rs.getString("placa")).get();
                saida.setVeiculo(veiculo);
                saida.setKmSaida(rs.getInt("km_saida"));
            }
            
            return Optional.ofNullable(saida);
            
        }catch(SQLException e){
            throw new DBException("Erro ao buscar saída: " + e.getMessage());
        }
    }
    
    public void createSaida(SaidaVeiculo saida)throws DBException{
        try{
            String sql = "INSERT INTO saidas_veiculos VALUES(null, ?,?,?,?,?);";
            PreparedStatement ps = database.getConnnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, saida.getIdPedido());
            ps.setInt(2, saida.getUsuario().getId());
            ps.setString(3, saida.getVeiculo().getPlaca());
            ps.setTimestamp(4, Timestamp.valueOf(saida.getInstanteSaida()));
            ps.setInt(5, saida.getKmSaida());
            
            int linhas = ps.executeUpdate();
            
            if(linhas > 0){
                System.out.println("Linhas afetadas: " + linhas);
                ResultSet rs = ps.getGeneratedKeys();
                if(rs.next()){
                    saida.setId(rs.getInt(1));
                }
            }
        }catch(SQLException e){
            throw new DBException("Erro ao criar saída: " + e.getMessage());
        }
            
    }
    
    public void updateSaida(SaidaVeiculo saida)throws DBException{
        try{
            String sql = "UPDATE saidas_veiculos SET "
                    + "id_pedido = ?, "
                    + "id_assistente = ?, "
                    + "placa = ?, "
                    + "instante_saida = ?, "
                    + "km_saida = ? "
                    + "WHERE id_saida = ?;";
            PreparedStatement ps = database.getConnnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, saida.getIdPedido());
            ps.setInt(2, saida.getUsuario().getId());
            ps.setString(3, saida.getVeiculo().getPlaca());
            ps.setTimestamp(4, Timestamp.valueOf(saida.getInstanteSaida()));
            ps.setInt(5, saida.getKmSaida());
            ps.setInt(6, saida.getId());
            
            int linhas = ps.executeUpdate();
            
            if(linhas > 0){
                System.out.println("Linhas afetadas: " + linhas);
            }
        }catch(SQLException e){
            throw new DBException("Erro ao atualizar saída: " + e.getMessage());
        }
    }
    
    public void deleteSaida(Integer id)throws DBException{
         try{
            String sql = "DELETE FROM saidas_veiculos WHERE id_saida = ?;";
            PreparedStatement ps = database.getConnnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, id);
            
            int linhas = ps.executeUpdate();
            
            if(linhas > 0){
                System.out.println("Linhas afetadas: " + linhas);
            }
        }catch(SQLException e){
            throw new DBException("Erro ao deletar saída: " + e.getMessage());
        }
    }
}
