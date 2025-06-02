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
import javafx.scene.control.ToggleGroup;

/**
 *
 * @author chris
 */
public class UsuariosController implements Initializable{
     
    @FXML
    private Button btnSalvar;

    @FXML
    private TableColumn<?, ?> colunaEmail;

    @FXML
    private TableColumn<?, ?> colunaFuncao;

    @FXML
    private TableColumn<?, ?> colunaNome;

    @FXML
    private TableColumn<?, ?> colunaNomeUsuario;

    @FXML
    private TableColumn<?, ?> colunaSituacao;

    @FXML
    private RadioButton rbAuxiliar;

    @FXML
    private RadioButton rbGerente;

    @FXML
    private TableView<?> tabelaUsuarios;

    @FXML
    private ToggleGroup tipoUsuario;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }
}
