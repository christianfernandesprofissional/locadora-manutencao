/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fatec.garagemlocalhost.model.dao;

import com.fatec.garagemlocalhost.database.Database;
import com.fatec.garagemlocalhost.model.entities.SaidaVeiculo;
import com.fatec.garagemlocalhost.model.entities.Usuario;
import com.fatec.garagemlocalhost.model.entities.Veiculo;
import com.fatec.garagemlocalhost.model.enums.SituacaoSaida;
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
    
    public List<SaidaVeiculo> findAll()throws SQLException{
        List<SaidaVeiculo> saidas = new ArrayList<>();
  
        String sql = "SELECT * FROM saidas_veiculos;";
        PreparedStatement ps = database.getConnnection().prepareStatement(sql);

        ResultSet rs = ps.executeQuery();

        VeiculoDAO vDAO = new VeiculoDAO(database);
        UsuarioDAO uDAO = new UsuarioDAO(database);
        while(rs.next()){
            SaidaVeiculo saida = new SaidaVeiculo();
            saida.setId(rs.getInt("id_saida"));
            saida.setIdPedido(rs.getInt("id_pedido"));
            Usuario usuario = uDAO.findById(rs.getInt("id_assistente")).orElse(null);
            saida.setUsuario(usuario);
            Veiculo veiculo = vDAO.findByPlaca(rs.getString("placa")).orElse(null);
            saida.setVeiculo(veiculo);
            saida.setInstanteSaida(rs.getTimestamp("instante_saida").toLocalDateTime());
            saida.setKmSaida(rs.getInt("km_saida"));
            if(saida.getInstanteSaida()== null){
                saida.setSituacao(SituacaoSaida.AGUARDANDO_ENTREGA);
            }else{
                saida.setSituacao(SituacaoSaida.ENTREGUE_AO_CLIENTE);
            }
            saidas.add(saida);
        }
       
        return saidas;
    }
    
    public Optional<SaidaVeiculo> findById(Integer id)throws SQLException{

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
            Usuario usuario = uDAO.findById(rs.getInt("id_assistente")).orElse(null);
            saida.setUsuario(usuario);
            Veiculo veiculo = vDAO.findByPlaca(rs.getString("placa")).orElse(null);
            saida.setVeiculo(veiculo);
            saida.setKmSaida(rs.getInt("km_saida"));
            saida.setInstanteSaida(rs.getTimestamp("instante_saida").toLocalDateTime());
            if(saida.getInstanteSaida() == null){
                saida.setSituacao(SituacaoSaida.AGUARDANDO_ENTREGA);
            }else{
                saida.setSituacao(SituacaoSaida.ENTREGUE_AO_CLIENTE);
            }
        }

        return Optional.ofNullable(saida);
    }
    
  
    public void createSaida(SaidaVeiculo saida)throws SQLException{

        String sql = "INSERT INTO saidas_veiculos(id_pedido, id_assistente, placa, instante_saida, km_saida) VALUES( ?,?,?,?,?);";
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
    }
    
    public void updateSaida(SaidaVeiculo saida)throws SQLException{

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
    }
    
    public void deleteSaida(Integer id)throws SQLException{

        String sql = "DELETE FROM saidas_veiculos WHERE id_saida = ?;";
        PreparedStatement ps = database.getConnnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        ps.setInt(1, id);

        int linhas = ps.executeUpdate();

        if(linhas > 0){
            System.out.println("Linhas afetadas: " + linhas);
        } 
    }
    
    private String getModeloSolicitado(Integer idPedido)throws SQLException{
   
        String sql = "SELECT modeloSolicitado FROM pedidos_locacao WHERE id_pedido = ?;";
        PreparedStatement ps = database.getConnnection().prepareStatement(sql);
        ps.setInt(1, idPedido);

        ResultSet rs = ps.executeQuery();
        
        if(rs.next()){
            return rs.getString("modeloSolicitado");
        }
        
        return null;
    }
    
}
