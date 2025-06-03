/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fatec.garagemlocalhost.teste;


import com.fatec.garagemlocalhost.database.Database;
import com.fatec.garagemlocalhost.model.entities.CategoriaVeiculo;
import com.fatec.garagemlocalhost.model.entities.Manutencao;
import com.fatec.garagemlocalhost.model.entities.SaidaVeiculo;
import com.fatec.garagemlocalhost.model.entities.Servico;
import com.fatec.garagemlocalhost.model.entities.Usuario;
import com.fatec.garagemlocalhost.model.entities.Veiculo;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author silvol
 */
public class ReadDaoTest extends DaoFactory{ 
    
    
    public ReadDaoTest(Database database) {
        super(database);
    }
    
    private void listarRetorno(List retornos){
        for (var registro : retornos){
            System.out.println(registro);
        }
    }
    
    public void readTodosUsuarios(){
        try{
            listarRetorno(usuarioDao.findAll());
            System.out.println("Ler usuários OK!");
        }
        catch (SQLException e){
            System.out.println("ERRO ao listar todos os usuários: " + e.getMessage());            
        }
    }
    
    public void readUsuarioPorId(int id){
        try{
            Usuario u = usuarioDao.findById(id).get();
            System.out.println(u);
            System.out.println(u.getNome() + "Lido pelo Id com sucesso!");
        }
        catch (SQLException e){
            System.out.println("ERRO ao ler usuário pelo ID: " + e.getMessage());                 
        }
    }
    
    public void readUsuarioPorNome(String nome){
        try{
            listarRetorno(usuarioDao.findAllByNome(nome));
            System.out.println("Lido pelo Nome com sucesso!");
        }
        catch (SQLException e){
            System.out.println("ERRO ao ler usuário pelo Nome: " + e.getMessage());                 
        }
    }
    
    public void readTodasCategorias(){
        try{
            listarRetorno(categoriaDao.findAll());
            System.out.println("Ler categorias OK!");
        }
        catch (SQLException e){
            System.out.println("ERRO ao listar todas as categorias: " + e.getMessage());
        }
    }
    
    public void readCategoriaPorId(int id){
        try {
            CategoriaVeiculo c = categoriaDao.findById(id).get();
            System.out.println(c);
            System.out.println(c.getDescricao() + "Lido pelo Id com sucesso!");
        }
        catch (SQLException e){
            System.out.println("ERRO ao ler categoria pelo ID: " + e.getMessage());                 
        }
    }
    
    public void readTodasDevolucoes(){
        try {
            listarRetorno(devolucaoDao.findAll());
            System.out.println("Ler devoluções OK!");
        } 
        catch (SQLException e) {
            System.out.println("ERRO ao listar devoluções: " + e.getMessage());
        }
    }
    
    public void readDevolucaoPorId(int id){
        try {
            CategoriaVeiculo c = categoriaDao.findById(id).get();
            System.out.println(c);
            System.out.println(c.getDescricao() + "Lido pelo Id com sucesso!");
        }
        catch (SQLException e){
            System.out.println("ERRO ao ler devolução pelo ID: " + e.getMessage());                 
        }
    }
    
    public void readTodasManutencoes(){
        try {
            listarRetorno(manutencaoDao.findAll());
            System.out.println("Ler manutenções OK!");
        } 
        catch (SQLException e) {
            System.out.println("ERRO ao listar manutenções: " + e.getMessage());
        }
    }
    
    public void readManutencaoPorId(int id){
        try {
            Manutencao m = manutencaoDao.findById(id).get();
            System.out.println(m);
            System.out.println(m.getDescricao() + "Lido pelo Id com sucesso!");
        }
        catch (SQLException e){
            System.out.println("ERRO ao ler manutenção pelo ID: " + e.getMessage());                 
        }
    }
    
    public void readTodasSaidas(){
        try {
            listarRetorno(saidaDao.findAll());
            System.out.println("Ler manutenções OK!");
        } 
        catch (SQLException e) {
            System.out.println("ERRO ao listar saídas: " + e.getMessage());
        }
    }
    
    public void readSaidaPorId(int id){
        try {
            SaidaVeiculo s = saidaDao.findById(id).get();
            System.out.println(s);
            System.out.println(s.getIdPedido()+ "Lido pelo Id com sucesso!");
        }
        catch (SQLException e){
            System.out.println("ERRO ao ler saída pelo ID: " + e.getMessage());                 
        }
    }
    
    public void readTodosServicos(){
        try {
            listarRetorno(servicoDao.findAll());
            System.out.println("Ler serviços OK!");
        } 
        catch (SQLException e) {
            System.out.println("ERRO ao listar serviços: " + e.getMessage());
        }
    }
    
    public void readServicoPorId(int id){
        try {
            Servico s = servicoDao.findById(id).get();
            System.out.println(s);
            System.out.println(s.getDescricao()+ "Lido pelo Id com sucesso!");
        }
        catch (SQLException e){
            System.out.println("ERRO ao ler serviço pelo ID: " + e.getMessage());                 
        }
    }
    
    
    public void readTodosVeiculos(){
        try {
            listarRetorno(veiculoDao.findAll());
            System.out.println("Ler veículos OK!");
        } 
        catch (SQLException e) {
            System.out.println("ERRO ao listar veículos: " + e.getMessage());
        }
    }
    
    public void readVeiculoPorPlaca(String placa){
        try {
            Veiculo v = veiculoDao.findByPlaca(placa).get();
            System.out.println(v);
            System.out.println(v.getModelo()+ "Lido pelo Id com sucesso!");
        }
        catch (SQLException e){
            System.out.println("ERRO ao ler veículo pelo ID: " + e.getMessage());                 
        }
    }
    
    public void readAll(){
        
        readTodasCategorias();
        readTodasDevolucoes();
        readTodasManutencoes();
        readTodasSaidas();
        readTodosServicos();
        readTodosUsuarios();
        readTodosVeiculos();
    }
}
