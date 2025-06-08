/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fatec.garagemlocalhost.model.dao;

import com.fatec.garagemlocalhost.database.Database;
import com.fatec.garagemlocalhost.model.entities.DevolucaoVeiculo;
import com.fatec.garagemlocalhost.model.entities.Usuario;
import com.fatec.garagemlocalhost.model.entities.Manutencao;
import com.fatec.garagemlocalhost.model.entities.Veiculo;
import com.fatec.garagemlocalhost.model.enums.SituacaoDevolucao;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.sql.Types;
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

    public List<DevolucaoVeiculo> findAll() throws SQLException {

        VeiculoDAO veiculoDao = new VeiculoDAO(database);
        UsuarioDAO usuarioDao = new UsuarioDAO(database);
        ManutencaoDAO manutencaoDao = new ManutencaoDAO(database);

        List<DevolucaoVeiculo> devolucoes = new LinkedList();

        String sql = "SELECT * FROM devolucoes_veiculos;";
        PreparedStatement ps = database.getConnnection().prepareStatement(sql);

        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            DevolucaoVeiculo devolucao = new DevolucaoVeiculo();

            devolucao.setIdDevolucao(rs.getInt("id_devolucao"));
            devolucao.setIdPedido(rs.getInt("id_pedido"));
            Usuario usuario = usuarioDao.findById(rs.getInt("id_assistente")).orElse(null);
            devolucao.setAssistente(usuario);

            if (rs.getTimestamp("instante_devolucao") == null) {
                devolucao.setInstanteDevolucao(null);
            } else {
                devolucao.setInstanteDevolucao(rs.getTimestamp("instante_devolucao").toLocalDateTime());
            }

            int km = rs.getInt("km_chegada");
            if (rs.wasNull()) {
                devolucao.setKmChegada(null);
            } else {
                devolucao.setKmChegada(km);
            }

            Manutencao manutencao = manutencaoDao.findById(rs.getInt("id_manutencao")).orElse(null);
            devolucao.setManutencao(manutencao);

            Veiculo veiculo = veiculoDao.findByPlaca(rs.getString("placa")).orElse(null);
            devolucao.setVeiculo(veiculo);

            if (devolucao.getInstanteDevolucao() == null) {
                devolucao.setSituacao(SituacaoDevolucao.AGUARDANDO_DEVOLUCAO);
            } else {
                devolucao.setSituacao(SituacaoDevolucao.FINALIZADO);
            }

            devolucoes.add(devolucao);
        }
        ps.close();
        rs.close();

        return devolucoes;
    }

    public Optional<DevolucaoVeiculo> findById(int id) throws SQLException {
        VeiculoDAO veiculoDao = new VeiculoDAO(database);
        UsuarioDAO usuarioDao = new UsuarioDAO(database);
        ManutencaoDAO manutencaoDao = new ManutencaoDAO(database);

        String sql = "SELECT * FROM devolucoes_veiculos WHERE id_devolucao = ?;";
        PreparedStatement ps = database.getConnnection().prepareStatement(sql);
        ps.setInt(1, id);

        ResultSet rs = ps.executeQuery();

        DevolucaoVeiculo devolucao = null;
        if (rs.next()) {
            devolucao = new DevolucaoVeiculo();
            devolucao.setIdDevolucao(rs.getInt("id_devolucao"));
            devolucao.setIdPedido(rs.getInt("id_pedido"));
            devolucao.setAssistente(usuarioDao.findById(rs.getInt("id_assistente")).orElse(null));
            devolucao.setInstanteDevolucao(rs.getTimestamp("instante_devolucao").toLocalDateTime());
            devolucao.setKmChegada(rs.getInt("km_chegada"));
            devolucao.setManutencao(manutencaoDao.findById(rs.getInt("id_manutencao")).orElse(null));
            devolucao.setVeiculo(veiculoDao.findByPlaca(rs.getString("placa")).orElse(null));

            if (devolucao.getInstanteDevolucao() == null) {
                devolucao.setSituacao(SituacaoDevolucao.AGUARDANDO_DEVOLUCAO);
            } else {
                devolucao.setSituacao(SituacaoDevolucao.FINALIZADO);
            }

        }

        rs.close();
        ps.close();
        return Optional.ofNullable(devolucao);
    }

    public void createDevolucao(DevolucaoVeiculo devolucao) throws SQLException {

        String sql = "INSERT INTO devolucoes_veiculos "
                + "(id_pedido, placa) "
                + "VALUES (?, ?);";

        PreparedStatement ps = database.getConnnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

        ps.setInt(1, devolucao.getIdPedido());
        ps.setString(2, devolucao.getVeiculo().getPlaca());

        int linhas = ps.executeUpdate();

        if (linhas > 0) {
            System.out.println("Linhas afetadas: " + linhas);
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                devolucao.setIdDevolucao(rs.getInt(1));
            }
            rs.close();
        }

        ps.close();
    }

    public void updateDevolucao(DevolucaoVeiculo devolucao) throws SQLException {

        VeiculoDAO veiculoDao = new VeiculoDAO(database);

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
        if (devolucao.getManutencao() == null) {
            ps.setNull(6, Types.INTEGER);
        } else {
            ps.setInt(6, devolucao.getManutencao().getId());
        }
        ps.setInt(7, devolucao.getIdDevolucao());

        veiculoDao.updateVeiculo(devolucao.getVeiculo());

        ps.executeUpdate();
        ps.close();
    }

    public void deleteDevolucao(int idDevolucao) throws SQLException {

        String sql = "DELETE FROM devolucoes_veiculos WHERE id_devolucao = ?;";
        PreparedStatement ps = database.getConnnection().prepareStatement(sql);
        ps.setInt(1, idDevolucao);

        int linhas = ps.executeUpdate();
        if (linhas > 0) {
            System.out.println("Linah afetadas: " + linhas);
        }
        ps.close();
    }

}
