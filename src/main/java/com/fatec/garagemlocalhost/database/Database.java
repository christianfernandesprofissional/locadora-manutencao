/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fatec.garagemlocalhost.database;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 *
 * @author chris
 */
public class Database {
    private static final String URL = System.getenv("DB_URL");
    private static final String USER = System.getenv("DB_USER");
    private static final String PASSWORD = System.getenv("DB_PASSWORD");
    
    protected Connection conn = null; 
    
    public Database(){
        try{
            conn = DriverManager.getConnection(URL,USER,PASSWORD);
            System.out.println("Conexão bem sucedida!");
        }catch(SQLException e){
            System.out.println("Falha na conexão: " + e.getMessage());
        }
    }
    
    public Database(Boolean teste){
        Properties props = new Properties();
        try (InputStream input = getClass().getClassLoader().getResourceAsStream("application-teste.properties")) {
            if (input == null) {
                throw new IOException("Arquivo application.properties não encontrado em resources.");
            }

            props.load(input);

            String url = props.getProperty("jdbc.url");
            String username = props.getProperty("jdbc.username");
            String password = props.getProperty("jdbc.password");

            conn = DriverManager.getConnection(url, username, password);

        } catch (IOException | SQLException e) {
            throw new RuntimeException("Erro ao conectar ao banco H2", e);
        }
    }

    public Connection getConnnection() {
        return conn;
    }

  
    
    
}
