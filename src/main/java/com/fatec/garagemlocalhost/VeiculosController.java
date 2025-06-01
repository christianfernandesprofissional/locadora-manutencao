/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fatec.garagemlocalhost;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

/**
 *
 * @author chris
 */
public class VeiculosController implements Initializable{
    
    @FXML
    private TableColumn<?, ?> colunaAno;

    @FXML
    private TableColumn<?, ?> colunaCategoria;

    @FXML
    private TableColumn<?, ?> colunaChassi;

    @FXML
    private TableColumn<?, ?> colunaCor;

    @FXML
    private TableColumn<?, ?> colunaMarca;

    @FXML
    private TableColumn<?, ?> colunaModelo;

    @FXML
    private TableColumn<?, ?> colunaPlaca;

    @FXML
    private TableColumn<?, ?> colunaPrecoBase;

    @FXML
    private TableColumn<?, ?> colunaQuilometragem;

    @FXML
    private TableView<?> tabelaVeiculos;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }
}
