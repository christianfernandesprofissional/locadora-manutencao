/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fatec.garagemlocalhost;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author Christian
 */
public class HomeController implements Initializable {

    @FXML
    private BorderPane borderPane;

    @FXML
    private Button bntInicio;

    @FXML
    private Button btnDevolucoes;

    @FXML
    private Button btnManutencoes;

    @FXML
    private Button btnSaidas;

    @FXML
    private Button btnUsuarios;

    @FXML
    private Button btnSair;

    @FXML
    private MenuItem btnListarVeiculos;

    @FXML
    private MenuItem btnCadastrarVeiculo;

    public void menuInicio() {
        VBox v = loadFXML("TelaInicial");
        borderPane.setCenter(v);
    }

    public void menuSaidas() {
        VBox v = loadFXML("Saidas");
        borderPane.setCenter(v);
    }

    public void menuDevolucoes() {
        VBox v = loadFXML("Devolucoes");
        borderPane.setCenter(v);
    }

    public void menuManutencoes() {
        VBox v = loadFXML("Manutencoes");
        borderPane.setCenter(v);
    }

    public void menuListarVeiculos() {
        VBox v = loadFXML("Veiculos");
        borderPane.setCenter(v);
    }
    
    public void menuUsuarios() {
        VBox v = loadFXML("Usuarios");
        borderPane.setCenter(v);
    }

    public void menuCadastrarVeiculo() {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("CadastroVeiculo.fxml"));
        try {
            GridPane v = fxmlLoader.load();
            borderPane.setCenter(v);
        } catch (IOException ex) {
            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    public void sair() {
        Alert alert = new Alert(AlertType.NONE, "DESEJA MESMO SAIR?", ButtonType.YES, ButtonType.NO);
        alert.setTitle("Atenção!!!");
      
        alert.showAndWait().filter(response -> response == ButtonType.YES).ifPresent(response -> {
            Stage stage = (Stage) borderPane.getScene().getWindow();
            stage.close();
        });
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        VBox v = loadFXML("TelaInicial");
        borderPane.setCenter(v);

    }

    private VBox loadFXML(String fxml) {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        try {
            return fxmlLoader.load();
        } catch (IOException ex) {
            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
