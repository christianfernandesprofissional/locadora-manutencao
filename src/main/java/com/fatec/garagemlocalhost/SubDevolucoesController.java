/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fatec.garagemlocalhost;

/**
 *
 * @author gustavo
 */
import com.fatec.garagemlocalhost.database.DBException;
import com.fatec.garagemlocalhost.exceptions.CampoVazioException;
import com.fatec.garagemlocalhost.model.entities.DevolucaoVeiculo;
import com.fatec.garagemlocalhost.model.entities.Manutencao;
import com.fatec.garagemlocalhost.model.enums.SituacaoVeiculo;
import com.fatec.garagemlocalhost.services.DevolucaoService;
import com.fatec.garagemlocalhost.services.VeiculoService;
import com.fatec.garagemlocalhost.utils.UsuarioHolder;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
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
    
    private DevolucaoService devolucaoService = new DevolucaoService();
    
    private VeiculoService veiculoService = new VeiculoService();
    
    //private ManutencaoService manutencaoService = new ManutencaoService();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        configurarBotoes();
    }
    
    public void habilitarBotoes(){
        if(devolucao.getInstanteDevolucao() == null){
            cbVaiParaManutencao.setDisable(false);
            txtKmChegada.setEditable(true);
            btnRegistrarDevolucao.setDisable(false);
        }
    }
    
    public void configurarBotoes(){
        btnCancelar.setOnAction(event -> {
             Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
        });
        
         btnRegistrarDevolucao.setOnAction(event -> {
            try {
                if (!isInteiro(txtKmChegada.getText())) {

                     lblErroKm.setText("Valor inválido!");
                    lblErroKm.setVisible(true);
                    
                } else if(Integer.valueOf(txtKmChegada.getText()) <= devolucao.getVeiculo().getQuilometragem()) {
   
                    lblErroKm.setText("Quilometragem deve ser maior que " + devolucao.getVeiculo().getQuilometragem());
                    lblErroKm.setVisible(true);
                    
                }else {
                    
                    devolucao.setKmChegada(Integer.valueOf(txtKmChegada.getText()));
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
                    devolucao.setInstanteDevolucao(LocalDateTime.parse(txtInstanteDevolucao.getText(), formatter));
                    devolucaoService.buscaDevolucaoPorId(2);
                    
                    int novaKm = devolucao.getVeiculo().getQuilometragem() + Integer.valueOf(txtKmChegada.getText());
                    devolucao.getVeiculo().setQuilometragem(novaKm);
                    
                    if(cbVaiParaManutencao.isSelected()){
                        Manutencao manutencao = new Manutencao();
                        manutencao.setDescricao(txtMotivoManutencao.getText());
                        manutencao.setInstanteChegada(LocalDateTime.parse(txtInstanteDevolucao.getText(), formatter));
                        manutencao.setIsfinalizado(Boolean.FALSE);
                        devolucao.getVeiculo().setSituacao(SituacaoVeiculo.EM_MANUTENÇÃO);
                        manutencao.setVeiculo(devolucao.getVeiculo());
                    }
                    
                    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    stage.close();
                }
            } catch (DBException | CampoVazioException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR, e.getMessage());
                alert.showAndWait();
            }
        });
         
         cbVaiParaManutencao.setOnAction(e -> {
             if(txtMotivoManutencao.isDisabled()){
                 txtMotivoManutencao.setDisable(false);
             }else{
                 txtMotivoManutencao.setDisable(true);
             }
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
            txtKmChegada.setEditable(true);
            txtInstanteDevolucao.setText(LocalDateTime.now().format(formatter));
        }else{
            cbVaiParaManutencao.setDisable(true);
            txtInstanteDevolucao.setText(devolucao.getInstanteDevolucao().format(formatter));
        }
    }

     public Boolean isInteiro(String numero) {
        try {
            Integer.valueOf(numero);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}

