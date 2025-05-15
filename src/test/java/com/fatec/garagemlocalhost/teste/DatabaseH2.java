/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fatec.garagemlocalhost.teste;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import org.h2.engine.Database;
/**
 *
 * @author chris
 */
public class DatabaseH2 {

private Connection connection;

    public DatabaseH2(){
        Properties props = new Properties();
        try (InputStream input = getClass().getClassLoader().getResourceAsStream("application-teste.properties")) {
            if (input == null) {
                throw new IOException("Arquivo application.properties não encontrado em resources.");
            }

            props.load(input);

            String url = props.getProperty("jdbc.url");
            String username = props.getProperty("jdbc.username");
            String password = props.getProperty("jdbc.password");

            this.connection = DriverManager.getConnection(url, username, password);

        } catch (IOException | SQLException e) {
            throw new RuntimeException("Erro ao conectar ao banco H2", e);
        }
    }

    public Connection getConnection() {
        return connection;
    }
}
