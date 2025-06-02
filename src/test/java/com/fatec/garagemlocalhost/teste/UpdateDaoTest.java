/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fatec.garagemlocalhost.teste;


import com.fatec.garagemlocalhost.database.Database;
import com.fatec.garagemlocalhost.exceptions.LoginValidacaoException;
import com.fatec.garagemlocalhost.model.entities.CategoriaVeiculo;
import com.fatec.garagemlocalhost.model.entities.DevolucaoVeiculo;
import com.fatec.garagemlocalhost.model.entities.Manutencao;
import com.fatec.garagemlocalhost.model.entities.SaidaVeiculo;
import com.fatec.garagemlocalhost.model.entities.Servico;
import com.fatec.garagemlocalhost.model.entities.Usuario;
import com.fatec.garagemlocalhost.model.entities.Veiculo;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.time.LocalDateTime;

/**
 *
 * @author Gabriel
 */
public class UpdateDaoTest extends DaoFactory {

    public UpdateDaoTest(Database database) {
        super(database);
    }

    public void updateUsuario() {
        try {
            Usuario usuario = usuarioDao.findById(1).get();
            usuario.setNome("Anderson Atualizado");
            usuario.setEmail("anderson_novo@gmail.com");
            usuarioDao.update(usuario);
            System.out.println("Atualizar usuario OK!");
        } catch (SQLException | LoginValidacaoException e) {
            System.out.println("ERRO ao atualizar usuário: " + e.getMessage());
        }
    }

    public void updateCategoriaVeiculo() {
        try {
            CategoriaVeiculo categoria = categoriaDao.findById(1).get();
            categoria.setDescricao("SUV Atualizado");
            categoriaDao.updateCategoria(categoria);
            System.out.println("Atualizar categoria OK!");
        } catch (SQLException e) {
            System.out.println("ERRO ao atualizar categoria: " + e.getMessage());
        }
    }

    public void updateVeiculo() {
        try {
            Veiculo veiculo = veiculoDao.findByPlaca("1426DFG").get();
            veiculo.setModelo("Fusca Atualizado");
            veiculo.setCor("Azul");
            veiculo.setPrecoBase(new BigDecimal("250.00"));
            veiculoDao.updateVeiculo(veiculo);
            System.out.println("Atualizar veiculo OK!");
        } catch (SQLException e) {
            System.out.println("ERRO ao atualizar veiculo: " + e.getMessage());
        }
    }

    public void updateServico() {
        try {
            Servico servico = servicoDao.findById(1).get();
            servico.setDescricao("Limpeza completa");
            servico.setPreco(new BigDecimal("30.00"));
            servicoDao.updateServico(servico);
            System.out.println("Atualizar servico OK!");
        } catch (SQLException e) {
            System.out.println("ERRO ao atualizar servico: " + e.getMessage());
        }
    }

    public void updateSaidaVeiculo() {
        try {
            SaidaVeiculo saida = saidaDao.findById(1).get();
            saida.setKmSaida(101000);
            saida.setInstanteSaida(LocalDateTime.now().minusHours(1));
            saidaDao.updateSaida(saida);
            System.out.println("Atualizar saida veiculo OK!");
        } catch (SQLException e) {
            System.out.println("ERRO ao atualizar saída veiculo: " + e.getMessage());
        }
    }

    public void updateDevolucaoVeiculo() {
        try {
            DevolucaoVeiculo devolucao = devolucaoDao.findById(1).get();
            devolucao.setKmChegada(100500);
            devolucao.setInstanteDevolucao(LocalDateTime.now());
            devolucaoDao.updateDevolucao(devolucao);
            System.out.println("Atualizar devolucao veiculo OK!");
        } catch (SQLException e) {
            System.out.println("ERRO ao atualizar devolução veículo: " + e.getMessage());
        }
    }

    public void updateManutencao() {
        try {
            Manutencao manutencao = manutencaoDao.findById(1).get();
            manutencao.setDescricao("Revisao completa");
            manutencao.setIsfinalizado(true);
            manutencao.setValorTotal(new BigDecimal("450.00"));
            manutencaoDao.updateManutencao(manutencao);
            System.out.println("Atualizar manutencao OK!");
        } catch (SQLException e) {
            System.out.println("ERRO ao atualizar manutenção: " + e.getMessage());
        }
    }

    /**
     * Chama todos os métodos de atualização desta classe.
     */
    public void updateAll() {
        updateUsuario();
        updateCategoriaVeiculo();
        updateVeiculo();
        updateServico();
        updateSaidaVeiculo();
        updateDevolucaoVeiculo();
        updateManutencao();
    }
}