/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fatec.garagemlocalhost;

/**
 *
 * @author gustavo
 */



import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class ManutencaoFinalizadaController{

    @FXML
    private TableColumn<?, ?> colunaIdDescricao;

    @FXML
    private TableColumn<?, ?> colunaIdServico;

    @FXML
    private TableColumn<?, ?> colunaPreco;

    @FXML
    private TableColumn<?, ?> colunaStatus;

    @FXML
    private Label lblPrecoTotal;

    @FXML
    private TableView<?> tabelaListaServicos;

}


