/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fatec.garagemlocalhost;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

/**
 *
 * @author Christian
 */
public class LoginController implements Initializable{
    
    @FXML
    private Button btnLogin;

    @FXML
    private CheckBox cbExibirSenha;

    @FXML
    private PasswordField txtMaskSenha;

    @FXML
    private TextField txtUnmaskSenha;

    @FXML
    private TextField txtUsuario;
    
    
    /**
     * Este método configura a CheckBox cbExibirSenha
     * 
     * @author Christian
     */
    @FXML
    private void setPropertiesCbExibirSenha(){

        //Deixa os textos dos campos sincronizados
        txtUnmaskSenha.textProperty().bindBidirectional(txtMaskSenha.textProperty());
        //Adiciona um Listener o CheckBox e define o que ele deve fazer quando estiver selecionado ou não
        cbExibirSenha.selectedProperty().addListener((obs, valorAnterior, valorNovo) -> {
           if(valorNovo){
               txtMaskSenha.setVisible(false);
               txtMaskSenha.setManaged(false);
               txtUnmaskSenha.setVisible(true);
               txtUnmaskSenha.setManaged(true);
           }else {
               txtUnmaskSenha.setVisible(false);
               txtUnmaskSenha.setManaged(false);
               txtMaskSenha.setVisible(true);
               txtMaskSenha.setManaged(true);
           }
       });
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
       setPropertiesCbExibirSenha();
    }

}
