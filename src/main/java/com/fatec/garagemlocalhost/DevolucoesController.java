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
 * @author Christian
 */
public class DevolucoesController implements Initializable{
    @FXML
    private TableColumn<?, ?> colunaAno;

    @FXML
    private TableColumn<?, ?> colunaAtualizar;

    @FXML
    private TableColumn<?, ?> colunaCor;

    @FXML
    private TableColumn<?, ?> colunaID;

    @FXML
    private TableColumn<?, ?> colunaInstante;

    @FXML
    private TableColumn<?, ?> colunaKmChegada;

    @FXML
    private TableColumn<?, ?> colunaModelo;

    @FXML
    private TableColumn<?, ?> colunaPlaca;

    @FXML
    private TableColumn<?, ?> colunaStatus;

    @FXML
    private TableView<?> tabelaDevolucoes;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }
}
