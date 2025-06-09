/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fatec.garagemlocalhost;

import com.fatec.garagemlocalhost.database.DBException;
import com.fatec.garagemlocalhost.model.entities.CategoriaVeiculo;
import com.fatec.garagemlocalhost.services.CategoriaService;
import java.net.URL;
import java.util.HashSet;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author chris
 */
public class CadastroCategoriaVeiculoController implements Initializable {

    @FXML
    private Button btnAdicionar;

    @FXML
    private Button btnCancelar;

    @FXML
    private Button btnDeletar;

    @FXML
    private Button btnEditar;

    @FXML
    private Button btnSalvar;

    @FXML
    private Label lblErroCategoria;

    @FXML
    private TableColumn<CategoriaVeiculo, String> colunaDescricao;

    @FXML
    private TableView<CategoriaVeiculo> tabelaCategorias;

    @FXML
    private TextField txtIdCategoria;

    @FXML
    private TextField txtDescricao;

    private String descricaoAnterior;

    private ObservableList<CategoriaVeiculo> listaCategorias = FXCollections.observableArrayList();
    private HashSet<CategoriaVeiculo> listaCategoriasEdicao = new HashSet<>();
    private HashSet<CategoriaVeiculo> listaCategoriasDelecao = new HashSet<>();

    private CategoriaService categoriaService = new CategoriaService();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        preencherTabela();
        editarCategoriaDoubleclick();
        configurarBotoes();
        txtDescricao.setDisable(false);
    }

    public void configurarBotoes() {
        btnEditar.setOnAction(e -> {
            if (txtDescricao.isDisabled()) {
                txtDescricao.setDisable(false);
                btnDeletar.setDisable(false);
                btnEditar.setText("Descartar alterações");
            } else {
                txtDescricao.setDisable(true);
                btnDeletar.setDisable(true);
                btnEditar.setText("Editar");
                txtDescricao.setText(descricaoAnterior);
                lblErroCategoria.setVisible(false);
            }
        });

        btnAdicionar.setOnAction(e -> {
            if (txtDescricao.getText().isBlank()) {
                lblErroCategoria.setVisible(true);
            } else {
                CategoriaVeiculo categoria = new CategoriaVeiculo();
                if (txtDescricao.getText().trim().equals(descricaoAnterior)) {
                    categoria.setDescricao(descricaoAnterior);
                    categoria.setId(Integer.valueOf(txtIdCategoria.getText()));
                    listaCategorias.add(categoria);
                } else {
                    categoria.setId(0);
                    if (!txtIdCategoria.getText().isBlank()) {
                        categoria.setId(Integer.valueOf(txtIdCategoria.getText()));
                    }
                    categoria.setDescricao(txtDescricao.getText().trim());
                    if (!listaCategorias.contains(categoria)) {
                        listaCategorias.add(categoria);
                    }
                    listaCategoriasEdicao.add(categoria);
                }
                limparCampos();
            }
        });

        btnDeletar.setOnAction(e -> {
            CategoriaVeiculo categoria = new CategoriaVeiculo();
            if (txtIdCategoria.getText().isBlank()) {
                limparCampos();
            } else {
                categoria.setId(Integer.valueOf(txtIdCategoria.getText()));
                categoria.setDescricao(txtDescricao.getText());
                listaCategoriasDelecao.add(categoria);
                limparCampos();
            }

        });

        btnCancelar.setOnAction(event -> {
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.close();
        });

        btnSalvar.setOnAction(e -> {
            Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("Salvar alterações");
            alert.setHeaderText("Tem certeza que deseja salvar as alterações?");
            alert.setContentText("As alterações e deleções serão salvas!\n");
            ButtonType sim = new ButtonType("Sim");
            alert.getButtonTypes().setAll(sim, new ButtonType("Não"));
            alert.initStyle(StageStyle.UNDECORATED);
            alert.initModality(Modality.APPLICATION_MODAL);
            Optional<ButtonType> resultado = alert.showAndWait();

            if (resultado.get() == sim) {

                listaCategoriasDelecao.forEach(categoria -> {
                    try {
                        System.out.println(categoria);
                        categoriaService.deletarCategoriaVeiculoPorId(categoria);
                    } catch (DBException erro) {
                        Alert alerta = new Alert(AlertType.ERROR, erro.getMessage());
                        alerta.showAndWait();
                    }
                });

                listaCategoriasEdicao.forEach(categoria -> {
                    try {
                        if (categoria.getId() == 0) {
                            System.out.println(categoria);
                            categoriaService.criarCategoriaVeiculo(categoria);
                        } else {
                            System.out.println(categoria);
                            categoriaService.atualizarCategoriaVeiculo(categoria);
                        }
                    } catch (DBException erro) {
                        Alert alerta = new Alert(AlertType.ERROR, erro.getMessage());
                        alerta.showAndWait();
                    }

                    Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
                    stage.close();
                });

            }
        });
    }

    public void editarCategoriaDoubleclick() {
        tabelaCategorias.setRowFactory(t -> {
            TableRow<CategoriaVeiculo> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && !row.isEmpty()) {
                    CategoriaVeiculo categoria = row.getItem();
                    txtIdCategoria.setText(categoria.getId().toString());
                    txtDescricao.setText(categoria.getDescricao());
                    listaCategorias.remove(categoria);
                    btnEditar.setDisable(false);
                    tabelaCategorias.setDisable(true);
                    txtDescricao.setDisable(true);
                    descricaoAnterior = txtDescricao.getText();

                }
            });
            return row;
        });
    }

    private void preencherTabela() {
        try {
            listaCategorias.clear();
            listaCategorias.addAll(categoriaService.buscarTodasCategorias());
            colunaDescricao.setCellValueFactory(new PropertyValueFactory<>("descricao"));
            tabelaCategorias.setItems(listaCategorias);
        } catch (DBException e) {
            System.out.println("Erro ao carregar tabela: " + e.getMessage());
        }
    }

    public void limparCampos() {
        txtDescricao.setText("");
        txtIdCategoria.setText("");
        lblErroCategoria.setVisible(false);
        txtDescricao.setDisable(false);
        tabelaCategorias.setDisable(false);
        btnDeletar.setDisable(true);
        btnEditar.setText("Editar");
        lblErroCategoria.setVisible(false);
    }

}
