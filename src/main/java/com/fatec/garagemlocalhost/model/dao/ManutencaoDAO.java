/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fatec.garagemlocalhost.model.dao;

import com.fatec.garagemlocalhost.database.DBException;
import com.fatec.garagemlocalhost.database.Database;
import com.fatec.garagemlocalhost.model.entities.Manutencao;
import com.fatec.garagemlocalhost.model.entities.Veiculo;
import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Classe responsável por fazer o CRUD da classe Manutenção
 * 
 * @author Christian
 */
public class ManutencaoDAO {
    private Database database;
    
    public ManutencaoDAO(Database database){
        this.database = database;
    }
    
    public List<Manutencao> findAll() throws DBException{
        List<Manutencao> manutencoes = new ArrayList<>();
        try{
            String sql = "SELECT * FROM manutencoes;";
            PreparedStatement ps = database.getConnnection().prepareStatement(sql);
            
            ResultSet rs = ps.executeQuery();
            
            VeiculoDAO veiculoDao = new VeiculoDAO(database);
            while(rs.next()){
                Manutencao manutencao = new Manutencao();
                manutencao.setId(rs.getInt("id_manutencao"));
                Veiculo veiculo = veiculoDao.findByPlaca(rs.getString("placa")).get();
                manutencao.setVeiculo(veiculo);
                manutencao.setDescricao(rs.getString("descricao"));
                manutencao.setIsfinalizado(rs.getBoolean("finalizado"));
                manutencao.setInstanteChegada(LocalDateTime.parse(rs.getString("instante_chegada")));
                manutencao.setInstanteSaida(LocalDateTime.parse(rs.getString("instante_saida")));
                BigDecimal total = new BigDecimal(rs.getDouble("total"));
                manutencao.setValorTotal(total);
            }
            
        }catch(SQLException e){
            throw new DBException("Erro ao buscar manutenções: " + e.getMessage());
        }
        return manutencoes;
    }
    
    public Optional<Manutencao> FindById(Integer id)throws DBException{
        try{
            String sql = "SELECT * FROM manutencoes WHERE id_manutencao = ?;";
            PreparedStatement ps = database.getConnnection().prepareStatement(sql);
            ps.setInt(1, id);
            
            ResultSet rs = ps.executeQuery();
            
            VeiculoDAO veiculoDao = new VeiculoDAO(database);
            Manutencao manutencao = null;
            if(rs.next()){
                manutencao = new Manutencao();
                manutencao.setId(rs.getInt("id_manutencao"));
                Veiculo veiculo = veiculoDao.findByPlaca(rs.getString("placa")).get();
                manutencao.setVeiculo(veiculo);
                manutencao.setDescricao(rs.getString("descricao"));
                manutencao.setIsfinalizado(rs.getBoolean("finalizado"));
                manutencao.setInstanteChegada(LocalDateTime.parse(rs.getString("instante_chegada")));
                manutencao.setInstanteSaida(LocalDateTime.parse(rs.getString("instante_saida")));
                BigDecimal total = new BigDecimal(rs.getDouble("total"));
                manutencao.setValorTotal(total);
            }
            return Optional.ofNullable(manutencao);
        }catch(SQLException e){
            throw new DBException("Erro ao encontrar manutenção: " + e.getMessage());
        }
    }
    
    public void createManutencao(Manutencao manutencao)throws DBException{
        if(manutencao == null) throw new DBException("A manutenção não pode ser nula!");
        try{
            String sql = "INSERT INTO manutencoes(placa, descricao, finalizado, instante_chegada, instante_saida, total) VALUES(?,?,?,?,?,?);";
            PreparedStatement ps = database.getConnnection().prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
            
            ps.setString(1, manutencao.getVeiculo().getPlaca());
            ps.setString(2, manutencao.getDescricao());
            ps.setBoolean(3, manutencao.getIsfinalizado());
            ps.setTimestamp(4, Timestamp.valueOf(manutencao.getInstanteChegada()));
            ps.setTimestamp(5, Timestamp.valueOf(manutencao.getInstanteSaida()));
            ps.setDouble(6, manutencao.getValorTotal().doubleValue());
            
            int linhas = ps.executeUpdate();
            
            if(linhas > 0){
                System.out.println("Linhas afetadas: " + linhas);
                ResultSet rs = ps.getGeneratedKeys();
                if(rs.next()){
                    manutencao.setId(rs.getInt(1));
                }
            }
            
        }catch(SQLException e){
            throw new DBException("Erro ao cadastrar manutenção! " + e.getMessage());
        }
    }
    
    public void updateManutencao(Manutencao manutencao)throws DBException{
        try{
            String sql = "UPDATE manutencoes SET "
                    + "placa = ?, "
                    + "descricao = ?, "
                    + "finalizado = ?, "
                    + "instante_chegada = ?,"
                    + "instante_saida = ?,"
                    + "total = ? WHERE id_manutencao = ?;";
            
            PreparedStatement ps = database.getConnnection().prepareStatement(sql);
            
            ps.setString(1, manutencao.getVeiculo().getPlaca());
            ps.setString(2, manutencao.getDescricao());
            ps.setBoolean(3, manutencao.getIsfinalizado());
            ps.setTimestamp(4, Timestamp.valueOf(manutencao.getInstanteChegada()));
            ps.setTimestamp(5, Timestamp.valueOf(manutencao.getInstanteSaida()));
            ps.setDouble(6, manutencao.getValorTotal().doubleValue());
            ps.setInt(7, manutencao.getId());
            
            int linhas = ps.executeUpdate();
            
            if(linhas > 0){
                System.out.println("Linhas afetadas: " + linhas);
            }
        }catch(SQLException  e){
            throw new DBException("Erro ao atualizar manutenção: " + e.getMessage());
        }
        
    }
    
    public void deleteManutencao(Integer id) throws DBException {
        try{
            String sql = "DELETE FROM manutencoes WHERE id_manutencao = ?;";
            PreparedStatement ps = database.getConnnection().prepareStatement(sql);
            
            ps.setInt(1, id);
            
            int linhas = ps.executeUpdate();
             if(linhas > 0){
                System.out.println("Linhas afetadas: " + linhas);
            }
        }catch(SQLException e){
            throw new DBException("Erro ao deletar manutenção: " + e.getMessage());
        }
    }
    
}
