/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fatec.garagemlocalhost.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author chris
 */
public class Database {
    private static final String URL = System.getenv("DB_URL");
    private static final String USER = System.getenv("DB_USER");
    private static final String PASSWORD = System.getenv("DB_PASSWORD");
    
    private Connection conn = null; 
    
    public Database(){
        try{
            conn = DriverManager.getConnection(URL,USER,PASSWORD);
            System.out.println("Conexão bem sucedida!");
        }catch(SQLException e){
            System.out.println("Falha na conexão: " + e.getMessage());
        }
    }

    public Connection getConn() {
        return conn;
    }
    
}
