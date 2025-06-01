/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fatec.garagemlocalhost;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

/**
 *
 * @author chris
 */
public class ManutencoesController implements Initializable{
    
    @FXML
    private Button btnAtualizar;

    @FXML
    private TableColumn<?, ?> colunaDataSaida;

    @FXML
    private TableColumn<?, ?> colunaDescricao;

    @FXML
    private TableColumn<?, ?> colunaIdManutencao;

    @FXML
    private TableColumn<?, ?> colunaModelo;

    @FXML
    private TableColumn<?, ?> colunaPlaca;

    @FXML
    private TableColumn<?, ?> colunaServicosRealizados;

    @FXML
    private TableColumn<?, ?> colunaValorTotal;

    @FXML
    private RadioButton rbFinalizadas;

    @FXML
    private RadioButton rbPendentes;

    @FXML
    private TableView<?> tabelaDevolucoes;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }
}
