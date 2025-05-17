/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fatec.garagemlocalhost.teste;

import com.fatec.garagemlocalhost.database.Database;
import com.fatec.garagemlocalhost.model.dao.UsuarioDAO;
import com.fatec.garagemlocalhost.model.entities.Usuario;
import com.fatec.garagemlocalhost.model.enums.TipoUsuario;
import java.util.Scanner;



/**
 *
 * @author chris
 */
public class Teste1 {
   public static void main(String[] args) {
        Database db = new Database(true);
        
        try{
           
            CreateDaoTest create = new CreateDaoTest(db);
            ReadDaoTest read = new ReadDaoTest(db);
           
            //create.createAll();
            //System.out.println("==============================================");
            read.readAll();
           
            
            System.out.println("Teste 1");
        }catch(Exception e){
            System.out.println("ERRO: " + e.getMessage());
        }
       

//        try (Connection conn = db.getConnection()) {
//            Statement stmt = conn.createStatement();
//
//            // Criação da tabela
//            stmt.execute("CREATE TABLE usuarios (id INT PRIMARY KEY, nome VARCHAR(100))");
//            System.out.println("Tabela 'usuarios' criada com sucesso.");
//
//            // Inserção de dados
//            stmt.execute("INSERT INTO usuarios (id, nome) VALUES (1, 'João'), (2, 'Maria')");
//            System.out.println("Dados inseridos com sucesso.");
//
//            // Consulta
//            PreparedStatement ps = conn.prepareStatement("SELECT nome FROM usuarios WHERE id = ?");
//            ps.setInt(1, 1);
//            ResultSet rs = ps.executeQuery();
//
//            if (rs.next()) {
//                String nome = rs.getString("nome");
//                System.out.println("Usuário com ID 1: " + nome);
//
//                if ("João".equals(nome)) {
//                    System.out.println("✅ Teste passou: nome correto.");
//                } else {
//                    System.out.println("❌ Teste falhou: nome incorreto.");
//                }
//            } else {
//                System.out.println("❌ Teste falhou: usuário com ID 1 não encontrado.");
//            }
//
//            // Teste para ID inexistente
//            ps.setInt(1, 99);
//            rs = ps.executeQuery();
//            if (!rs.next()) {
//                System.out.println("✅ Teste passou: usuário com ID 99 não encontrado (esperado).");
//            } else {
//                System.out.println("❌ Teste falhou: usuário com ID 99 deveria não existir.");
//            }
//
//            rs.close();
//            ps.close();
//            stmt.close();
//
//        } catch (Exception e) {
//            e.printStackTrace();
//            System.out.println("❌ Erro durante o teste: " + e.getMessage());
//        }
    }
}
