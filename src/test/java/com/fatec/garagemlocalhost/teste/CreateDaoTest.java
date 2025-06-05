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
import com.fatec.garagemlocalhost.model.enums.SituacaoVeiculo;
import com.fatec.garagemlocalhost.model.enums.TipoUsuario;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.time.LocalDateTime;

/**
 * Classe de teste referente aos metodos Create
 * de todas classes DAO.
 * 
 * @author Christian
 */
public class CreateDaoTest extends DaoFactory{    
    
    
    public CreateDaoTest(Database database){
        super(database);
    }

    public void createUsuario() {
        try{
            Usuario usuario = new Usuario("Anderson", "anderson@gmail.com", "12345678", TipoUsuario.AUXILIAR, true);
            usuarioDao.create(usuario);
            System.out.println("Criar usuário OK!");
        }catch(SQLException | LoginValidacaoException e){
            System.out.println("ERRO ao criar usuario: " + e.getMessage());
        }
    }
    
    public void createCategoriaVeiculo(){
        try{
            CategoriaVeiculo categoria = new CategoriaVeiculo(null, "Hatch Conversível");
            categoriaDao.createCategoria(categoria);
            System.out.println("Criar categoria OK!");
        }catch(SQLException e){
            System.out.println("ERRO ao criar categoria: " + e.getMessage());
        }
    }
    
     public void createVeiculo(){
        try{
            CategoriaVeiculo cat = categoriaDao.findById(1).get();
            Veiculo veiculo = new Veiculo("1426DFG", "Volkswagen", "Branco", 1950,"358654fsdv831","Fusca",532165,cat, new BigDecimal("204.89"), SituacaoVeiculo.DISPONÍVEL);
            veiculoDao.createVeiculo(veiculo);
            System.out.println("Criar veiculo OK!");
        }catch(SQLException e){
            System.out.println("ERRO ao criar veiculo: " + e.getMessage());
        }
    }
     
     public void createServico(){
        try{
            Servico servico = new Servico(null, "Limpar vidro", new BigDecimal("20.85"), true);
            servicoDao.createServico(servico);
            System.out.println("Criar servico OK!");
        }catch(SQLException e){
            System.out.println("ERRO ao criar servico: " + e.getMessage());
        }
    }
     
    public void createSaidaVeiculo(){
        try{
            Veiculo veiculo = veiculoDao.findByPlaca("AAA1234").get();
            Usuario usuario = usuarioDao.findById(1).get();
            
            //Integer id, Integer idPedido, Usuario usuario, Veiculo veiculo, LocalDateTime instanteSaida, Integer kmSaida
            SaidaVeiculo saida = new SaidaVeiculo(null, 1, usuario, veiculo, LocalDateTime.now(), 100123);
            saidaDao.createSaida(saida);
            System.out.println("Criar saida veiculo OK!");
        }catch(SQLException e){
            System.out.println("ERRO ao criar saidaVeiculo: " + e.getMessage());
        }
    }
    
    public void createDevolucaoVeiculo(){
        try{
             Veiculo veiculo = veiculoDao.findByPlaca("AAA1234").get();
             Usuario usuario = usuarioDao.findById(1).get();
            //Integer idDevolucao, Integer idPedido, LocalDateTime instanteDevolucao, Integer kmChegada, Usuario assistente, Veiculo veiculo, Manutencao manutencao
            DevolucaoVeiculo dv = new DevolucaoVeiculo(null,1,LocalDateTime.now(), 100321, usuario, veiculo, null);
            devolucaoDao.createDevolucao(dv);
            System.out.println("Criar devolucao veiculo OK!");
        }catch(SQLException e){
            System.out.println("ERRO ao criar DevolucaoVeiculo: " + e.getMessage());
        }
    }
    
    public void createManutencao(){
        try{
            Veiculo veiculo = veiculoDao.findByPlaca("AAA1234").get();
            //Integer id, Veiculo veiculo, String descricao, Boolean isfinalizado, LocalDateTime instanteChegada, LocalDateTime instanteSaida, BigDecimal valorTotal
            Manutencao manutencao = new Manutencao(null, veiculo, "pedidoTeste", false, LocalDateTime.now(), LocalDateTime.now(), new BigDecimal("300.00"));
            manutencaoDao.createManutencao(manutencao);
            System.out.println("Criar manutencao OK!");
        }catch(SQLException e){
            System.out.println("ERRO ao criar DevolucaoVeiculo: " + e.getMessage());
        }
    }
    
    /**
     * Chama todas os metodos de teste desta classe,
     * caso um novo seja criado, adicionar aqui.
     * 
     * @author Christian
     */
    public void createAll(){
        createCategoriaVeiculo();
        createVeiculo();
        createUsuario();
        createServico();
        createSaidaVeiculo();
        createDevolucaoVeiculo();
        createManutencao();
    }
}
