/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fatec.garagemlocalhost.model.dao;

import com.fatec.garagemlocalhost.database.DBException;
import com.fatec.garagemlocalhost.database.Database;
import com.fatec.garagemlocalhost.model.entities.DevolucaoVeiculo;
import com.fatec.garagemlocalhost.model.entities.Usuario;
import com.fatec.garagemlocalhost.model.entities.Manutencao;
import com.fatec.garagemlocalhost.model.entities.Veiculo;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

/**
 * Classe responsável por fazer o CRUD da classe Devolução
 * 
 * @author Alber
 */
public class DevolucaoVeiculoDAO {
    private final Database database;

    public DevolucaoVeiculoDAO(Database database) {
        this.database = database;
    }
    
    public List<DevolucaoVeiculo> findAll() throws DBException{
        
        VeiculoDAO veiculoDao = new VeiculoDAO(database);
        UsuarioDAO usuarioDao = new UsuarioDAO(database);
        ManutencaoDAO manutencaoDao = new ManutencaoDAO(database);
                
        List<DevolucaoVeiculo> devolucoes = new LinkedList();
        
        try {
            String sql = "SELECT * FROM devolucoes_veiculos;" ;
            PreparedStatement ps = database.getConnnection().prepareStatement(sql);
            
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()){
                DevolucaoVeiculo devolucao = new DevolucaoVeiculo();
                
                devolucao.setIdDevolucao(rs.getInt("id_devolucao"));
                devolucao.setIdPedido(rs.getInt("id_pedido"));
                Usuario usuario = usuarioDao.findById(rs.getInt("id_assistente")).get();
                devolucao.setAssistente(usuario);
                
                //devolucao.setInstanteDevolucao(rs.getTimestamp("instante_devolucao"));
                devolucao.setKmChegada(rs.getInt("km_chegada"));
                Manutencao manutencao = manutencaoDao.findById(rs.getInt("id_manutencao")).get();
                devolucao.setManutencao(manutencao);
                
                Veiculo veiculo = veiculoDao.findByPlaca(rs.getString("placa")).get();
                devolucao.setVeiculo(veiculo);
                devolucoes.add(devolucao);
            }
            ps.close();
            rs.close();
        }
        catch (SQLException e) {
            throw new DBException("Erro ao buscar devoluções: " + e.getMessage());
        }
        
        return devolucoes;
    }
    
   public Optional<DevolucaoVeiculo> findById(int id) throws DBException {
        VeiculoDAO veiculoDao = new VeiculoDAO(database);
        UsuarioDAO usuarioDao = new UsuarioDAO(database);
        ManutencaoDAO manutencaoDao = new ManutencaoDAO(database);

        try {
            String sql = "SELECT * FROM devolucoes_veiculos WHERE id_devolucao = ?;";
            PreparedStatement ps = database.getConnnection().prepareStatement(sql);
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();
            
            DevolucaoVeiculo devolucao = null;
            if (rs.next()) {
                devolucao = new DevolucaoVeiculo();
                devolucao.setIdDevolucao(rs.getInt("id_devolucao"));
                devolucao.setIdPedido(rs.getInt("id_pedido"));
                devolucao.setAssistente(usuarioDao.findById(rs.getInt("id_assistente")).get());
                devolucao.setInstanteDevolucao(rs.getTimestamp("instante_devolucao").toLocalDateTime());
                devolucao.setKmChegada(rs.getInt("km_chegada"));
                devolucao.setManutencao(manutencaoDao.findById(rs.getInt("id_manutencao")).get());
                devolucao.setVeiculo(veiculoDao.findByPlaca(rs.getString("placa")).get());
            }

            rs.close();
            ps.close();
            return Optional.ofNullable(devolucao);
            
        } catch (SQLException e) {
            throw new DBException("Erro ao buscar devolução por ID: " + e.getMessage());
        }
    }

    public void createDevolucao(DevolucaoVeiculo devolucao) throws DBException {
        try {
            String sql = "INSERT INTO devolucoes_veiculos "
                    + "(id_pedido, placa) "
                    + "VALUES (?, ?);";
            
            PreparedStatement ps = database.getConnnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            ps.setInt(1, devolucao.getIdPedido());
            ps.setString(2, devolucao.getVeiculo().getPlaca());

            int linhas = ps.executeUpdate();
            
            if(linhas > 0){
                System.out.println("Linhas afetadas: " + linhas);
                ResultSet rs = ps.getGeneratedKeys();
                if(rs.next()){
                    devolucao.setIdDevolucao(rs.getInt(1));
                }
                rs.close();
            }
            
            ps.close();
        } catch (SQLException e) {
            throw new DBException("Erro ao inserir devolução: " + e.getMessage());
        }
    }

    public void updateDevolucao(DevolucaoVeiculo devolucao) throws DBException {
        
        VeiculoDAO veiculoDao = new VeiculoDAO(database);
        
        try {
            String sql = "UPDATE devolucoes_veiculos SET "
                    + "id_pedido = ?, "
                    + "instante_devolucao = ?, "
                    + "km_chegada = ?, "
                    + "id_assistente = ?, "
                    + "placa = ?, "
                    + "id_manutencao = ? "
                    + "WHERE id_devolucao = ?;";
            
            PreparedStatement ps = database.getConnnection().prepareStatement(sql);

            ps.setInt(1, devolucao.getIdPedido());
            ps.setTimestamp(2, Timestamp.valueOf(devolucao.getInstanteDevolucao()));
            ps.setInt(3, devolucao.getKmChegada());
            ps.setInt(4, devolucao.getAssistente().getId());
            ps.setString(5, devolucao.getVeiculo().getPlaca());
            ps.setInt(6, devolucao.getManutencao().getId());
            ps.setInt(7, devolucao.getIdDevolucao());
            
            veiculoDao.updateVeiculo(devolucao.getVeiculo());

            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            throw new DBException("Erro ao atualizar devolução: " + e.getMessage());
        }
    }

    public void deleteDevolucao(int idDevolucao) throws DBException {
        try {
            String sql = "DELETE FROM devolucoes_veiculos WHERE id_devolucao = ?;";
            PreparedStatement ps = database.getConnnection().prepareStatement(sql);
            ps.setInt(1, idDevolucao);

            int linhas = ps.executeUpdate();
            if (linhas>0){
                System.out.println("Linah afetadas: " + linhas);
            }
            ps.close();
        } catch (SQLException e) {
            throw new DBException("Erro ao deletar devolução: " + e.getMessage());
        }
    }   
    
}
