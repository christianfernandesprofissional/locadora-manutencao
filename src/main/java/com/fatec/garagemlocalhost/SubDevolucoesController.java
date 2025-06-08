/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fatec.garagemlocalhost;

/**
 *
 * @author gustavo
 */
import com.fatec.garagemlocalhost.model.entities.DevolucaoVeiculo;
import com.fatec.garagemlocalhost.utils.UsuarioHolder;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class SubDevolucoesController implements Initializable{

    @FXML
    private Button btnRegistrarDevolucao;

    @FXML
    private Button btnCancelar;

    @FXML
    private CheckBox cbVaiParaManutencao;

    @FXML
    private TextField txtAuxiliar;

    @FXML
    private TextField txtKmChegada;

    @FXML
    private TextField txtPlaca;
    
    @FXML
    private TextField txtChassi;
    
    @FXML
    private TextField txtInstanteDevolucao;

    @FXML
    private TextArea txtMotivoManutencao;
    
    @FXML
    private Label lblErroKm;
    
    private DevolucaoVeiculo devolucao;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        configurarBotoes();
    }
    
    public void configurarBotoes(){
        btnCancelar.setOnAction(event -> {
             Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
        });
    }
    
    
      public void setDevolucao(DevolucaoVeiculo devolucao) {
        this.devolucao = devolucao;
        
        txtPlaca.setText(devolucao.getVeiculo().getPlaca());
        if (devolucao.getAssistente() != null) {
            txtAuxiliar.setText(devolucao.getAssistente().getNome());
        } else {
            devolucao.setAssistente(UsuarioHolder.getInstance().getUsuario());
            txtAuxiliar.setText(UsuarioHolder.getInstance().getUsuario().getNome());
        }
        if (devolucao.getKmChegada() != null) {
            txtKmChegada.setEditable(false);
            txtKmChegada.setText(devolucao.getKmChegada().toString());
        } 
        txtChassi.setText(devolucao.getVeiculo().getChassi());
        txtPlaca.setText(devolucao.getVeiculo().getPlaca());
        
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        if(devolucao.getInstanteDevolucao() == null){
            cbVaiParaManutencao.setDisable(false);
            btnRegistrarDevolucao.setDisable(false);
            txtInstanteDevolucao.setText(LocalDateTime.now().format(formatter));
        }else{
             cbVaiParaManutencao.setDisable(true);
            txtInstanteDevolucao.setText(devolucao.getInstanteDevolucao().format(formatter));
        }
    }

    
}

