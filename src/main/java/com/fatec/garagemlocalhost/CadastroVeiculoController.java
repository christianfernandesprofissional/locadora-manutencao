/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fatec.garagemlocalhost;

import com.fatec.garagemlocalhost.database.DBException;
import com.fatec.garagemlocalhost.model.entities.CategoriaVeiculo;
import com.fatec.garagemlocalhost.model.entities.Veiculo;
import com.fatec.garagemlocalhost.model.enums.SituacaoVeiculo;
import com.fatec.garagemlocalhost.services.CategoriaService;
import com.fatec.garagemlocalhost.services.VeiculoService;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author Christian
 */
public class CadastroVeiculoController implements Initializable {

    @FXML
    private Label lblErroPlaca;

    @FXML
    private Label lblErroChassi;

    @FXML
    private Label lblErroAno;

    @FXML
    private Label lblErroPreco;

    @FXML
    private Label lblErroKm;

    @FXML
    private Label lblErroCamposVazios;

    @FXML
    private Label lblErroCategoria;

    @FXML
    private Label lblVeiculoSalvo;

    @FXML
    private Button btnLimpar;

    @FXML
    private Button btnSalvar;

    @FXML
    private Button btnEditar;

    @FXML
    private Button btnDeletar;

    @FXML
    private Button btnAdicionarCategoria;

    @FXML
    private ComboBox<CategoriaVeiculo> cmbCategoria;

    @FXML
    private TextField txtAno;

    @FXML
    private TextField txtChassi;

    @FXML
    private TextField txtCor;

    @FXML
    private TextField txtMarca;

    @FXML
    private TextField txtModelo;

    @FXML
    private TextField txtPlaca;

    @FXML
    private TextField txtPreco;

    @FXML
    private TextField txtQuilometragem;

    private CategoriaService categoriaService = new CategoriaService();

    private VeiculoService veiculoService = new VeiculoService();

    private Veiculo veiculo = null;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        if (veiculo == null) {
            habilitarCampos(false);
        } else {
            desabilitarCampos();
        }
        configurarBotoes();
        preencherCmbCategorias();
    }

    public void configurarBotoes() {
        btnEditar.setOnAction(e -> {
            habilitarCampos(true);
            esconderLabels();
        });

        btnLimpar.setOnAction(e -> {
            limparCampos();
            esconderLabels();
        });

        btnAdicionarCategoria.setOnAction(e -> {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("CadastroCategoriaVeiculo.fxml"));
                Parent root = loader.load();

                Stage stage = new Stage();
                stage.setScene(new Scene(root));
                stage.setWidth(800);
                stage.setHeight(551);
                stage.setResizable(false);
                //stage.initStyle(StageStyle.UNDECORATED);
                //Bloqueia o acesso a tela de trás
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.showAndWait();
                cmbCategoria.getItems().clear();
                preencherCmbCategorias();
            } catch (IOException erro) {
                erro.printStackTrace();
            }
        });

        btnSalvar.setOnAction(e -> {
            esconderLabels();
            try {
                if (txtPlaca.isDisabled()) {
                    if (verificarValores()) {
                        veiculo.setCategoria(cmbCategoria.getValue());
                        veiculo.setCor(txtCor.getText());
                        veiculo.setMarca(txtMarca.getText());
                        veiculo.setModelo(txtModelo.getText());
                        //Fazer verificações nos items abaixo
                        veiculo.setAno(Integer.valueOf(txtAno.getText()));
                        veiculo.setPrecoBase(new BigDecimal(txtPreco.getText().trim().replace(",", ".")));
                        veiculo.setQuilometragem(Integer.valueOf(txtQuilometragem.getText()));
                        veiculoService.atualizarVeiculo(veiculo);
                        lblVeiculoSalvo.setVisible(true);
                        desabilitarCampos();
                    }

                } else {
                    if (verificarValores()) {
                        veiculo = new Veiculo();
                        veiculo.setCategoria(cmbCategoria.getValue());
                        veiculo.setCor(txtCor.getText());
                        veiculo.setMarca(txtMarca.getText());
                        veiculo.setModelo(txtModelo.getText());
                        //Fazer verificações nos items abaixo
                        veiculo.setAno(Integer.valueOf(txtAno.getText()));
                        veiculo.setPrecoBase(new BigDecimal(txtPreco.getText().trim().replace(",", ".")));
                        veiculo.setQuilometragem(Integer.valueOf(txtQuilometragem.getText()));
                        veiculo.setSituacao(SituacaoVeiculo.DISPONÍVEL);

                        if (!txtPlaca.getText().replaceAll("\\s+", "").toUpperCase().equals(txtPlaca.getText().trim().toUpperCase())) {
                            lblErroPlaca.setVisible(true);
                        } else if (!txtChassi.getText().replaceAll("\\s+", "").toUpperCase().equals(txtChassi.getText().trim().toUpperCase())) {
                            lblErroChassi.setVisible(true);
                        } else {
                            veiculo.setChassi(txtChassi.getText().replaceAll("\\s+", "").toUpperCase());
                            veiculo.setPlaca(txtPlaca.getText().replaceAll("\\s+", "").toUpperCase());
                        }

                        Optional<Veiculo> v = veiculoService.encontrarPelaPlaca(veiculo);

                        if (v.isPresent()) {
                            Alert alert = new Alert(AlertType.WARNING, "Veículo já cadastrado!");
                            alert.setContentText("O Veículo da placa " + veiculo.getPlaca() + " já está cadastrado!");
                            alert.initStyle(StageStyle.UNDECORATED);
                            alert.initModality(Modality.APPLICATION_MODAL);
                            txtPlaca.setText("");
                            alert.showAndWait();
                        } else {
                            veiculoService.cadastrarVeiculo(veiculo);
                            lblVeiculoSalvo.setVisible(true);
                            desabilitarCampos();
                        }

                    }
                }

            } catch (DBException error) {
                error.printStackTrace();
                Alert alert = new Alert(Alert.AlertType.ERROR, "Erro ao salvar veiculo!");
                alert.showAndWait();
            }

        });
    }

    /**
     * Habilita todos os campos exceto Placa e Chassi. Caso queira habilitar os
     * dois escolha entre: false -> Habilitar true -> Desabilitar
     *
     * @param habilitar
     */
    public void habilitarCampos(Boolean habilitar) {
        txtPlaca.setDisable(habilitar);
        txtAno.setDisable(false);
        txtChassi.setDisable(habilitar);
        txtCor.setDisable(false);
        txtMarca.setDisable(false);
        txtModelo.setDisable(false);
        txtPreco.setDisable(false);
        txtQuilometragem.setDisable(false);
        cmbCategoria.setDisable(false);
    }

    public void desabilitarCampos() {
        txtPlaca.setDisable(true);
        txtAno.setDisable(true);
        txtChassi.setDisable(true);
        txtCor.setDisable(true);
        txtMarca.setDisable(true);
        txtModelo.setDisable(true);
        txtPreco.setDisable(true);
        txtQuilometragem.setDisable(true);
        btnEditar.setDisable(false);
        cmbCategoria.getSelectionModel().select(veiculo.getCategoria());
        cmbCategoria.setDisable(true);
    }

    public void limparCampos() {
        txtAno.setText("");
        txtChassi.setText("");
        txtCor.setText("");
        txtMarca.setText("");
        txtModelo.setText("");
        txtPlaca.setText("");
        txtPreco.setText("");
        txtQuilometragem.setText("");
        habilitarCampos(false);
        btnEditar.setDisable(true);
    }

    /**
     * Preenche os campos com os dados do Veículo. Tenha certeza de que o
     * veículo está instanciado antes de chamar este metodo.
     */
    private void preencherCampos() {
        txtAno.setText(veiculo.getAno().toString());
        txtChassi.setText(veiculo.getChassi());
        txtCor.setText(veiculo.getCor());
        txtMarca.setText(veiculo.getMarca());
        txtModelo.setText(veiculo.getModelo());
        txtPlaca.setText(veiculo.getPlaca());
        txtPreco.setText(veiculo.getPrecoBase().setScale(2, RoundingMode.CEILING).toString());
        txtQuilometragem.setText(veiculo.getQuilometragem().toString());
        if (veiculo.getCategoria() != null) {
            cmbCategoria.setValue(veiculo.getCategoria());
        }
    }

    private void preencherCmbCategorias() {
        try {
            ObservableList<CategoriaVeiculo> listaCategorias = FXCollections.observableArrayList();
            listaCategorias.addAll(categoriaService.buscarTodasCategorias());
            cmbCategoria.setItems(listaCategorias);
        } catch (DBException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Erro ao preencher comboBox de categorias");
            alert.showAndWait();
        }
    }

    /**
     * Verifica se um valor de texto é inteiro
     *
     * @param numero
     * @return
     */
    public Boolean isInteiro(String numero) {
        try {
            Integer.valueOf(numero);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * Verifica se um valor de texto é decimal. Já substitui virgula por ponto.
     *
     * @param numero
     * @return
     */
    public Boolean isDecimal(String numero) {
        if (numero.contains(",")) {
            numero = numero.trim().replace(",", ".");
        }
        try {
            Double.valueOf(numero);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public Boolean verificarValores() {
        Boolean valoresValidos = true;

        if (!isInteiro(txtAno.getText())) {
            lblErroAno.setVisible(true);
            valoresValidos = false;
        }
        if (!isInteiro(txtQuilometragem.getText())) {
            lblErroKm.setVisible(true);
            valoresValidos = false;
        }

        if (!isDecimal(txtPreco.getText())) {
            lblErroPreco.setVisible(true);
            valoresValidos = false;
        }

        if (txtPlaca.getText().length() != 7) {
            lblErroPlaca.setVisible(true);
            valoresValidos = false;
        }

        if (txtChassi.getText().isEmpty()) {
            lblErroCamposVazios.setVisible(true);
            valoresValidos = false;
        }

        if (txtCor.getText().isEmpty()) {
            lblErroCamposVazios.setVisible(true);
            valoresValidos = false;
        }

        if (txtMarca.getText().isEmpty()) {
            lblErroCamposVazios.setVisible(true);
            valoresValidos = false;
        }

        if (txtModelo.getText().isEmpty()) {
            lblErroCamposVazios.setVisible(true);
            valoresValidos = false;
        }

        if (cmbCategoria.getValue() == null) {
            lblErroCategoria.setVisible(true);
            valoresValidos = false;
        }
        return valoresValidos;
    }

    public void esconderLabels() {
        lblVeiculoSalvo.setVisible(false);
        lblErroAno.setVisible(false);
        lblErroKm.setVisible(false);
        lblErroPreco.setVisible(false);
        lblErroPlaca.setVisible(false);
        lblErroCamposVazios.setVisible(false);
        lblErroCategoria.setVisible(false);
    }

    public void setVeiculo(Veiculo veiculo) {
        this.veiculo = veiculo;
        preencherCampos();
        desabilitarCampos();
    }

}
