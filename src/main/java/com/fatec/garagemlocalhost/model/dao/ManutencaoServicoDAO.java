/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fatec.garagemlocalhost.model.dao;

import com.fatec.garagemlocalhost.database.Database;
import com.fatec.garagemlocalhost.model.entities.Manutencao;
import com.fatec.garagemlocalhost.model.entities.Servico;
import com.fatec.garagemlocalhost.model.enums.SituacaoVeiculo;
import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Alber
 */
public class ManutencaoServicoDAO {
    private final Database database;

    public ManutencaoServicoDAO(Database database) {
        this.database = database;
    }
    
    public void preencherServicos(Manutencao manutencao) throws SQLException{
        
        List<Servico> servicos = new ArrayList<>();
        
        String sql = "SELECT s.* " +
                     "FROM manutencoes_servicos ms " +
                     "JOIN servicos s ON s.id_servico = ms.id_servico " +
                     "WHERE ms.id_manutencao = ?;";
        PreparedStatement ps = database.getConnnection().prepareStatement(sql);
        ps.setInt(1, manutencao.getId());
        
        ResultSet rs = ps.executeQuery();
        
        while(rs.next()){
            
            Servico servico = new Servico();
            
            int id = rs.getInt("id_servico");
            if (rs.wasNull()){
                servico.setIdServico(null);
            }
            else{
                servico.setIdServico(rs.getInt("id_servico"));
            }
            
            servico.setDescricao(rs.getString("descricao"));
            
            BigDecimal preco = new BigDecimal(rs.getDouble("preco"));
            if (rs.wasNull()){
                servico.setPreco(null);
            }
            else{
                servico.setPreco(preco);
            }
            
            servico.setIsComum(rs.getBoolean("isComum"));
            
            servicos.add(servico);
        }
        
        manutencao.setServicos(servicos);
        
    }
    
    public void insereServicos(Manutencao manutencao) throws SQLException{
        // Toda lógica aqui
        
        BigDecimal TotalServicos = new BigDecimal(0.0);
        
        String sql = "INSERT INTO " +
                     "manutencoes_servicos(id_manutencao, id_servico, valor_item) " +
                     "VALUES (?, ?, ?)";
        PreparedStatement ps = database.getConnnection().prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
        
        for (Servico s : manutencao.getServicos()){
            ps.setInt(1, manutencao.getId());
            ps.setInt(2, s.getIdServico());
            
            ps.setBigDecimal(3, s.getPreco());
            TotalServicos = TotalServicos.add(s.getPreco()); 
            
            ps.executeUpdate();
        }

        // Atualiza os dados das outras tabelas
        manutencao.setValorTotal(TotalServicos);
        manutencao.setInstanteSaida(LocalDateTime.now());
        manutencao.setIsfinalizado(true);
        manutencao.getVeiculo().setSituacao(SituacaoVeiculo.DISPONÍVEL);

        ManutencaoDAO mDAO = new ManutencaoDAO(database);
        VeiculoDAO vDAO = new VeiculoDAO(database);

        mDAO.updateManutencao(manutencao);
        vDAO.updateVeiculo(manutencao.getVeiculo());
    }
    
}
