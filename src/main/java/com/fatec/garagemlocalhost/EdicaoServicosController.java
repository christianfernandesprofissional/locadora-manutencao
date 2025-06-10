/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fatec.garagemlocalhost;

import com.fatec.garagemlocalhost.database.DBException;
import com.fatec.garagemlocalhost.model.entities.Servico;
import com.fatec.garagemlocalhost.services.ServicoService;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.URL;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author chris
 */
public class EdicaoServicosController implements Initializable {

    @FXML
    private Button btnAdicionarServico;

    @FXML
    private Button btnCancelar;

    @FXML
    private Button btnEditar;

    @FXML
    private Button btnSalvar;

    @FXML
    private TableColumn<Servico, String> colunaDescricao;

    @FXML
    private TableColumn<Servico, String> colunaIdServico;

    @FXML
    private TableColumn<Servico, BigDecimal> colunaPreco;

    @FXML
    private TableView<Servico> tabelaServicos;

    @FXML
    private TextArea txtDescricaoServico;

    @FXML
    private TextField txtId;

    @FXML
    private CheckBox cbIsComum;

    @FXML
    private TextField txtValorServico;

    @FXML
    private Label lblValorInvalido;

    @FXML
    private Label lblErroDescricao;

    private ObservableList<Servico> listaDeServicos = FXCollections.observableArrayList();

    private ServicoService servicoService = new ServicoService();

    private String descricaoAntiga;

    private String valorAntigo;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        preencherCampos();
        editarServicoDoubleclick();
        configurarBotoes();
    }

    public void preencherCampos() {
        try {
            listaDeServicos.clear();
            listaDeServicos.addAll(servicoService.buscarTodosServicos());
        } catch (DBException e) {
            e.printStackTrace();
        }
        colunaIdServico.setCellValueFactory(cellData -> {
            Servico servico = cellData.getValue();
            if (servico.getIdServico() == null) {
                return new SimpleStringProperty("");
            } else {
                return new SimpleStringProperty(servico.getIdServico().toString());
            }

        });

        colunaDescricao.setCellValueFactory(cellData -> {
            Servico servico = cellData.getValue();
            return new SimpleStringProperty(servico.getDescricao());
        });

        colunaPreco.setCellValueFactory(new PropertyValueFactory<>("preco"));

        //O trecho abaixo formata a colunaPrecoBase para que exiba como um valor monetário
        colunaPreco.setCellFactory(coluna -> new TableCell<>() {
            private final NumberFormat formatoMoeda = NumberFormat.getCurrencyInstance(Locale.forLanguageTag("pt-BR"));

            @Override
            protected void updateItem(BigDecimal item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                } else {
                    setText(formatoMoeda.format(item));
                }
            }
        });

        tabelaServicos.setItems(listaDeServicos);
    }

    public void editarServicoDoubleclick() {
        tabelaServicos.setRowFactory(t -> {
            TableRow<Servico> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && !row.isEmpty()) {
                    lblErroDescricao.setVisible(false);
                    lblValorInvalido.setVisible(false);
                    Servico servico = row.getItem();

                    if (servico.getIdServico() == null || servico.getIdServico() == 0) {
                        txtId.setText("");
                    } else {
                        txtId.setText(String.valueOf(servico.getIdServico()));
                    }

                    txtValorServico.setText(servico.getPreco().setScale(2, RoundingMode.CEILING).toString());
                    if (servico.getIsComum()) {
                        cbIsComum.setSelected(true);
                    } else {
                        cbIsComum.setSelected(false);
                    }

                    txtDescricaoServico.setText(servico.getDescricao());

                    desabilitarCampos();
                    listaDeServicos.remove(servico);
                }
            });
            return row;
        });

        btnSalvar.setOnAction(e -> {
            Alert alert = new Alert(AlertType.CONFIRMATION);

            alert.setTitle("Salvar alterações");
            alert.setHeaderText("Tem certeza que deseja salvar as alterações?");
            alert.setContentText("As alterações e criações serão salvas!\n");
            ButtonType sim = new ButtonType("Sim");
            alert.getButtonTypes().setAll(sim, new ButtonType("Não"));
            alert.initStyle(StageStyle.UNDECORATED);
            alert.initModality(Modality.APPLICATION_MODAL);
            Optional<ButtonType> resultado = alert.showAndWait();

            if (resultado.get() == sim) {
                listaDeServicos.forEach(servico -> {

                    try {
                        if (servico.getIdServico() == null || servico.getIdServico() == 0) {
                            servicoService.cadastrarServico(servico);
                        } else {
                            servicoService.atualizarServico(servico);
                        }
                    } catch (DBException error) {
                        Alert errorMessage = new Alert(AlertType.ERROR, "Erro ao salvar serviços!");
                        errorMessage.showAndWait();
                    }
                    btnCancelar.fire();
                });

            }

        });
    }

    public void configurarBotoes() {
        btnAdicionarServico.setOnAction(e -> {
            Servico servico = new Servico();

            if (!txtId.getText().isBlank()) {
                servico.setIdServico(Integer.valueOf(txtId.getText()));
            }

            if (isDecimal(txtValorServico.getText())) {
                if (txtValorServico.getText().contains(",")) {
                    servico.setPreco(new BigDecimal(txtValorServico.getText().replace(",", ".")));
                } else {
                    servico.setPreco(new BigDecimal(txtValorServico.getText()));
                }
                lblValorInvalido.setVisible(false);
            } else {
                lblValorInvalido.setVisible(true);
            }

            if (txtDescricaoServico.getText().isBlank()) {
                lblErroDescricao.setVisible(true);
            } else {
                servico.setDescricao(txtDescricaoServico.getText().trim());
                lblErroDescricao.setVisible(false);
            }

            if (cbIsComum.isSelected()) {
                servico.setIsComum(Boolean.TRUE);
            } else {
                servico.setIsComum(Boolean.FALSE);
            }

            System.out.println(servico);
            if (!lblErroDescricao.isVisible() && !lblValorInvalido.isVisible()) {
                listaDeServicos.add(servico);
                btnEditar.setText("Editar");
                btnEditar.setDisable(true);
                HabilitarCampos();
                limparCampos();
            }
        });

        btnCancelar.setOnAction(event -> {
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.close();
        });

        btnEditar.setOnAction(e -> {
            if (txtDescricaoServico.isDisabled()) {
                txtDescricaoServico.setDisable(false);
                txtValorServico.setDisable(false);
                cbIsComum.setDisable(false);
                btnEditar.setText("Cancelar edição");
                descricaoAntiga = txtDescricaoServico.getText();
                valorAntigo = txtValorServico.getText();
            } else {
                txtDescricaoServico.setDisable(true);
                txtValorServico.setDisable(true);
                cbIsComum.setDisable(true);
                btnEditar.setText("Editar");
                txtDescricaoServico.setText(descricaoAntiga);
                txtValorServico.setText(valorAntigo);
                lblErroDescricao.setVisible(false);
                lblValorInvalido.setVisible(false);
            }
        });
    }

    public void desabilitarCampos() {
        txtDescricaoServico.setDisable(true);
        txtValorServico.setDisable(true);
        cbIsComum.setDisable(true);
        tabelaServicos.setDisable(true);
        btnEditar.setDisable(false);
    }

    public void HabilitarCampos() {
        txtDescricaoServico.setDisable(false);
        txtValorServico.setDisable(false);
        cbIsComum.setDisable(false);
        tabelaServicos.setDisable(false);
    }

    public void limparCampos() {
        txtId.setText("");
        txtDescricaoServico.setText("");
        txtValorServico.setText("");
    }

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
}
