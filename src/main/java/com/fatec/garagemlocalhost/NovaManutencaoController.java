/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fatec.garagemlocalhost;

import com.fatec.garagemlocalhost.database.DBException;
import com.fatec.garagemlocalhost.model.entities.Manutencao;
import com.fatec.garagemlocalhost.model.entities.Veiculo;
import com.fatec.garagemlocalhost.model.enums.SituacaoVeiculo;
import com.fatec.garagemlocalhost.services.ManutencaoService;
import com.fatec.garagemlocalhost.services.VeiculoService;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author chris
 */
public class NovaManutencaoController implements Initializable {

    @FXML
    private TextArea txtMotivo;

    @FXML
    private Label lblErroMotivo;

    @FXML
    private TextField txtPlaca;

    @FXML
    private Label lblErroPlaca;

    @FXML
    private Button btnCancelar;

    @FXML
    private Button btnCriarManutencao;

    private VeiculoService veiculoService = new VeiculoService();

    private ManutencaoService manutencaoService = new ManutencaoService();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        limparCampos();
        configurarBotoes();
    }

    public void configurarBotoes() {

        btnCriarManutencao.setOnAction(e -> {
            if (verificarCampos()) {
                try {
                    Optional<Veiculo> busca = veiculoService.encontrarPelaPlaca(txtPlaca.getText().toUpperCase());

                    if (busca.isPresent()) {
                        Veiculo veiculo = busca.get();

                        if (veiculo.getSituacao().equals(SituacaoVeiculo.DISPONÍVEL)) {

                            Manutencao manutencao = new Manutencao();
                            manutencao.setDescricao(txtMotivo.getText());
                            manutencao.setInstanteChegada(LocalDateTime.now());
                            manutencao.setIsfinalizado(Boolean.FALSE);
                            veiculo.setSituacao(SituacaoVeiculo.EM_MANUTENÇÃO);
                            manutencao.setVeiculo(veiculo);

                            veiculoService.atualizarVeiculo(veiculo);
                            manutencaoService.cadastrarManutencao(manutencao);

                            Alert alert = new Alert(AlertType.CONFIRMATION, "Manutenção criada!");
                            alert.initStyle(StageStyle.UNDECORATED);
                            alert.initModality(Modality.APPLICATION_MODAL);
                            alert.showAndWait();
                            limparCampos();

                        } else if (veiculo.getSituacao().equals(SituacaoVeiculo.EM_MANUTENÇÃO)) {
                            Alert alert = new Alert(AlertType.WARNING, "Veículo já está em manutenção!");
                            alert.initStyle(StageStyle.UNDECORATED);
                            alert.initModality(Modality.APPLICATION_MODAL);
                            alert.showAndWait();
                            limparCampos();
                        } else {
                            Alert alert = new Alert(AlertType.WARNING, "Veículo está alugado!");
                            alert.initStyle(StageStyle.UNDECORATED);
                            alert.initModality(Modality.APPLICATION_MODAL);
                            alert.showAndWait();
                            limparCampos();
                        }

                    } else {
                        Alert alert = new Alert(AlertType.WARNING, "Veículo não encontrado");
                        alert.initStyle(StageStyle.UNDECORATED);
                        alert.initModality(Modality.APPLICATION_MODAL);
                        alert.showAndWait();
                    }

                } catch (DBException error) {
                    Alert alert = new Alert(AlertType.WARNING, "Falha ao criar manutenção!");
                    alert.initStyle(StageStyle.UNDECORATED);
                    alert.initModality(Modality.APPLICATION_MODAL);
                    alert.showAndWait();
                }
            }
        });

        btnCancelar.setOnAction(event -> {
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.close();
        });
    }

    public void limparCampos() {
        txtMotivo.setText("");
        txtPlaca.setText("");
        lblErroMotivo.setVisible(false);
        lblErroPlaca.setVisible(false);
    }

    public Boolean verificarCampos() {
        Boolean ok = true;
        if (txtMotivo.getText().isBlank()) {
            ok = false;
            lblErroMotivo.setVisible(true);
        }else{
            lblErroMotivo.setVisible(false);
        }

        if (txtPlaca.getText().isBlank()) {
            ok = false;
            lblErroPlaca.setVisible(true);
        }else{
            lblErroPlaca.setVisible(false);
        }

        return ok;
    }
}
