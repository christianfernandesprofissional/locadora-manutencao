/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fatec.garagemlocalhost.model.dao;

import com.fatec.garagemlocalhost.database.Database;
import com.fatec.garagemlocalhost.model.entities.CategoriaVeiculo;
import com.fatec.garagemlocalhost.model.entities.Veiculo;
import com.fatec.garagemlocalhost.model.enums.SituacaoVeiculo;
import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Classe contendo o CRUD relacionado a tabela veiculos
 *
 * @author Christian
 */
public class VeiculoDAO {

    private final Database database;

    public VeiculoDAO(Database database) {
        this.database = database;
    }

    public List<Veiculo> findAll() throws SQLException {
        List<Veiculo> veiculos = new ArrayList<>();

        String sql = "SELECT * FROM veiculos INNER JOIN categorias_veiculos ON veiculos.id_categoria = categorias_veiculos.id_categoria;";
        PreparedStatement ps = database.getConnnection().prepareStatement(sql);

        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            Veiculo veiculo = new Veiculo();
            veiculo.setPlaca(rs.getString("placa"));
            veiculo.setMarca(rs.getString("marca"));
            veiculo.setCor(rs.getString("cor"));
            veiculo.setAno(rs.getInt("ano"));
            veiculo.setChassi(rs.getString("chassi"));
            veiculo.setModelo(rs.getString("modelo"));
            veiculo.setQuilometragem(rs.getInt("quilometragem"));
            CategoriaVeiculo categoria = new CategoriaVeiculo(rs.getInt("id_categoria"), rs.getString("descricao"));
            veiculo.setCategoria(categoria);
            BigDecimal precoBase = new BigDecimal(rs.getDouble("preco_base"));
            veiculo.setPrecoBase(precoBase);
            veiculo.setSituacao(SituacaoVeiculo.setInteiro(rs.getInt("situacao")));
            veiculos.add(veiculo);
        }

        return veiculos;
    }

    public Optional<Veiculo> findByPlaca(String placa) throws SQLException {

        String sql = "SELECT * FROM veiculos INNER JOIN categorias_veiculos ON veiculos.id_categoria = categorias_veiculos.id_categoria WHERE placa = ?;";
        PreparedStatement ps = database.getConnnection().prepareStatement(sql);
        ps.setString(1, placa);

        ResultSet rs = ps.executeQuery();

        Veiculo veiculo = null;
        if (rs.next()) {
            veiculo = new Veiculo();
            veiculo.setPlaca(rs.getString("placa"));
            veiculo.setMarca(rs.getString("marca"));
            veiculo.setCor(rs.getString("cor"));
            veiculo.setAno(rs.getInt("ano"));
            veiculo.setChassi(rs.getString("chassi"));
            veiculo.setModelo(rs.getString("modelo"));
            veiculo.setQuilometragem(rs.getInt("quilometragem"));
            CategoriaVeiculo categoria = new CategoriaVeiculo(rs.getInt("id_categoria"), rs.getString("descricao"));
            veiculo.setCategoria(categoria);
            BigDecimal precoBase = new BigDecimal(rs.getDouble("preco_base"));
            veiculo.setPrecoBase(precoBase);
            veiculo.setSituacao(SituacaoVeiculo.setInteiro(rs.getInt("situacao")));
        }

        return Optional.ofNullable(veiculo);
    }

    public void createVeiculo(Veiculo veiculo) throws SQLException {

        String sql = "INSERT INTO veiculos(placa,marca,cor,ano,chassi,modelo,quilometragem,id_categoria, preco_base) VALUES (?,?,?,?,?,?,?,?,?);";
        PreparedStatement ps = database.getConnnection().prepareStatement(sql);

        ps.setString(1, veiculo.getPlaca());
        ps.setString(2, veiculo.getMarca());
        ps.setString(3, veiculo.getCor());
        ps.setInt(4, veiculo.getAno());
        ps.setString(5, veiculo.getChassi());
        ps.setString(6, veiculo.getModelo());
        ps.setInt(7, veiculo.getQuilometragem());
        ps.setInt(8, veiculo.getCategoria().getId());
        ps.setDouble(9, veiculo.getPrecoBase().doubleValue());

        int linhas = ps.executeUpdate();

        if (linhas > 0) {
            System.out.println("Linhas afetadas: " + linhas);
        }
    }

    public void updateVeiculo(Veiculo veiculo) throws SQLException {

        String sql = "UPDATE veiculos SET marca = ?, cor = ?, ano = ?, chassi =?,modelo = ?,quilometragem = ?,id_categoria = ?, preco_base = ?, situacao = ?  WHERE placa = ?;";
        PreparedStatement ps = database.getConnnection().prepareStatement(sql);

        ps.setString(1, veiculo.getMarca());
        ps.setString(2, veiculo.getCor());
        ps.setInt(3, veiculo.getAno());
        ps.setString(4, veiculo.getChassi());
        ps.setString(5, veiculo.getModelo());
        ps.setInt(6, veiculo.getQuilometragem());
        ps.setInt(7, veiculo.getCategoria().getId());
        ps.setDouble(8, veiculo.getPrecoBase().doubleValue());
        ps.setInt(9, veiculo.getSituacao().getNumero());
        ps.setString(10, veiculo.getPlaca());

        int linhas = ps.executeUpdate();

        if (linhas > 0) {
            System.out.println("Linhas afetadas: " + linhas);
        }
    }

    public void deleteVeiculo(String placa) throws SQLException {

        String sql = "DELETE FROM veiculos WHERE placa = ?;";
        PreparedStatement ps = database.getConnnection().prepareStatement(sql);

        ps.setString(1, placa);

        int linhas = ps.executeUpdate();

        if (linhas > 0) {
            System.out.println("Linhas afetadas: " + linhas);
        }
    }

    public Boolean existsByPlaca(String placa) throws SQLException {

        String sql = "SELECT placa FROM veiculos WHERE placa = ?;";
        PreparedStatement ps = database.getConnnection().prepareStatement(sql);
        ps.setString(1, placa);

        ResultSet rs = ps.executeQuery();
        return rs.next();
    }

}
