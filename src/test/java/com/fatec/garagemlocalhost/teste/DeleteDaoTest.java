/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fatec.garagemlocalhost.teste;


import com.fatec.garagemlocalhost.database.Database;
import java.sql.SQLException;

/**
 *
 * @author gustavo
 */
public class DeleteDaoTest extends DaoFactory{
    //CRIANDO A VARIAVEL DO ID QUE SERA DELETADO
    int teste = 1;
    String placa = "AAA1234";
    
    public DeleteDaoTest (Database database){
        super(database);
    }
    
/* Criando metodos DELETE para depois executalos de uma unica vez*/
    public void deleteCategoriaVeiculo(){
        try{
            categoriaDao.deleteCategoria(teste);
            System.out.println("DELETEI CATEGORIA");
        }catch(SQLException e){
            System.out.println("ERRO categoriaVeiculo"+ e.getMessage());
        }
        
     }
    public void deleteDevolucaoVeiculo(){
        try{
            devolucaoDao.deleteDevolucao(teste);
            System.out.println("DELETEI DEVOLUCAO");
        }catch(SQLException e){
            System.out.println("ERRO debolucaoVeiculo" + e.getMessage());
        }
        
    }
    public void deleteManutencao(){
        try{
            manutencaoDao.deleteManutencao(teste);
            System.out.println("DELETEI MANUTENCAO");
        }catch(SQLException e){
            System.out.println("ERRO manutencao" + e.getMessage());
        }
    }
    public void deleteSaidaVeiculo(){
        try{
            saidaDao.deleteSaida(teste);
            System.out.println("DELETEI SAIDA");
        }catch(SQLException e){
             System.out.println("ERRO saidaVeiculo" + e.getMessage());
        }
       
    }
    public void deleteServico(){
        try{
            servicoDao.deleteServico(teste);
            System.out.println("DELETEI SERVICO");
        }catch(SQLException e){
             System.out.println("ERRO servico" + e.getMessage());
        }
    }
    public void deleteUsuario(){
        try{
            usuarioDao.deleteById(teste);
            System.out.println("DELETEI USUARIO");
        }catch(SQLException e){
             System.out.println("ERRO usuario" + e.getMessage());
        }
    }
    public void deleteVeiculo(){
        try{
            veiculoDao.deleteVeiculo(placa);
            System.out.println("DELETEI VEICULO");
        }catch(SQLException e){
             System.out.println("ERRO veiculo" + e.getMessage());
        }
    }
    public void deleteAll(){
        this.deleteDevolucaoVeiculo();
        this.deleteSaidaVeiculo();
        this.deleteManutencao();
        this.deleteServico();
        this.deleteUsuario();
        this.deleteVeiculo();
        this.deleteCategoriaVeiculo();
    }
}
    

