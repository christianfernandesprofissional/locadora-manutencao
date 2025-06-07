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
import com.fatec.garagemlocalhost.model.entities.SaidaVeiculo;
import com.fatec.garagemlocalhost.services.SaidaService;
import com.fatec.garagemlocalhost.utils.UsuarioHolder;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class SubSaidasController implements Initializable {

    @FXML
    private Button btnEntregarVeiculo;

    @FXML
    private Button btnCancelar;

    @FXML
    private TextField txtAssistenteSaida;

    @FXML
    private TextField txtInstanteSaida;

    @FXML
    private TextField txtKmSaida;

    @FXML
    private TextField txtPlacaSaida;

    @FXML
    private Label lblErro;

    private SaidaVeiculo saida;

    private SaidaService saidaService = new SaidaService();

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    public void cancelar(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }

    public void salvar(ActionEvent event) {
        try {
            if (isInteiro(txtKmSaida.getText())) {
                saida.setKmSaida(Integer.valueOf(txtKmSaida.getText()));
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
                saida.setInstanteSaida(LocalDateTime.parse(txtInstanteSaida.getText(), formatter));
                
                saidaService.atualizarSaida(saida);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.close();
            } else {
                lblErro.setVisible(true);
            }
        } catch (DBException | CampoVazioException e) {
            Alert alert = new Alert(AlertType.ERROR, e.getMessage());
            alert.showAndWait();
        }

    }

    public SaidaVeiculo getSaida() {
        return saida;
    }

    public void setSaida(SaidaVeiculo saida) {
        this.saida = saida;
        
        txtPlacaSaida.setText(saida.getVeiculo().getPlaca());
        if (saida.getUsuario() != null) {
            txtAssistenteSaida.setText(saida.getUsuario().getNome());
        } else {
            saida.setUsuario(UsuarioHolder.getInstance().getUsuario());
            txtAssistenteSaida.setText(UsuarioHolder.getInstance().getUsuario().getNome());
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        if (saida.getInstanteSaida() == null) {
            txtInstanteSaida.setText(LocalDateTime.now().format(formatter));
        } else {
            txtInstanteSaida.setText(saida.getInstanteSaida().format(formatter));
        }
        if (saida.getKmSaida() != null) {
            txtKmSaida.setText(saida.getKmSaida().toString());
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
