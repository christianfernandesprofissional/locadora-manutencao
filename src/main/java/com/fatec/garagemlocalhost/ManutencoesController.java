/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fatec.garagemlocalhost;


import com.fatec.garagemlocalhost.database.DBException;
import com.fatec.garagemlocalhost.model.entities.Manutencao;
import com.fatec.garagemlocalhost.services.ManutencaoService;
import com.fatec.garagemlocalhost.services.ManutencaoServicoService;
import com.fatec.garagemlocalhost.services.ServicoService;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;
import java.text.NumberFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * Controller responsável por exibir as manutenções
 * na tela e chamar as respectivas subTelas.
 * 
 * @author chris
 */
public class ManutencoesController implements Initializable {

    @FXML
    private TableView<Manutencao> tabelaManutencoes;

    @FXML
    private TableColumn<Manutencao, Integer> colunaIdManutencao;

    @FXML
    private TableColumn<Manutencao, String> colunaPlaca;

    @FXML
    private TableColumn<Manutencao, String> colunaModelo;

    @FXML
    private TableColumn<Manutencao, String> colunaMotivoManutencao;

    @FXML
    private TableColumn<Manutencao, String> colunaDataChegada;

    @FXML
    private TableColumn<Manutencao, String> colunaDataSaida;

    @FXML
    private TableColumn<Manutencao, BigDecimal> colunaValorTotal;

    private ObservableList<Manutencao> listaManutencoes = FXCollections.observableArrayList();

    private ManutencaoService manutencaoService = new ManutencaoService();

    private ManutencaoServicoService manutencaoServicoService = new ManutencaoServicoService();

    private ServicoService servicoService = new ServicoService();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        preencherTabela();
        preencherServicosDasManutencoes();
        carregarServicosDoubleClick();
    }

    private void preencherTabela() {
        try {
            listaManutencoes.clear();
            listaManutencoes.addAll(manutencaoService.buscarTodasManutencoes());

            colunaIdManutencao.setCellValueFactory(new PropertyValueFactory<>("id"));

            colunaPlaca.setCellValueFactory(cellData -> {
                Manutencao manutencao = cellData.getValue();
                return new SimpleStringProperty(manutencao.getVeiculo().getPlaca());
            });

            colunaModelo.setCellValueFactory(cellData -> {
                Manutencao manutencao = cellData.getValue();
                return new SimpleStringProperty(manutencao.getVeiculo().getModelo());
            });

            colunaMotivoManutencao.setCellValueFactory(cellData -> {
                Manutencao manutencao = cellData.getValue();
                return new SimpleStringProperty(manutencao.getDescricao());
            });

            colunaDataChegada.setCellValueFactory(cellData -> {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
                LocalDateTime instante = cellData.getValue().getInstanteChegada();
                if (instante == null) {
                    return new SimpleStringProperty("");
                }
                return new SimpleStringProperty(instante.format(formatter));
            });

            colunaDataSaida.setCellValueFactory(cellData -> {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
                LocalDateTime instante = cellData.getValue().getInstanteSaida();
                if (instante == null) {
                    return new SimpleStringProperty("");
                }
                return new SimpleStringProperty(instante.format(formatter));
            });
            colunaValorTotal.setCellValueFactory(new PropertyValueFactory<>("valorTotal"));

            //O trecho abaixo formata a colunaPrecoBase para que exiba como um valor monetário
            colunaValorTotal.setCellFactory(coluna -> new TableCell<>() {
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

            tabelaManutencoes.setItems(listaManutencoes);
        } catch (DBException e) {
            System.out.println("Erro ao carregar tabela: " + e.getMessage());
        }

    }

    public void preencherServicosDasManutencoes() {

        listaManutencoes.forEach(manutencao -> {
            try {
                manutencaoServicoService.preencherManutencaoServico(manutencao);
            } catch (DBException e) {
                e.printStackTrace();
            }
        });

    }

    public void carregarServicosDoubleClick() {
        tabelaManutencoes.setRowFactory(t -> {
            TableRow<Manutencao> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && !row.isEmpty()) {
                    Manutencao manutencao = row.getItem();
                    if (manutencao.getIsfinalizado()) {
                        try {
                            FXMLLoader loader = new FXMLLoader(getClass().getResource("ManutencaoFinalizada.fxml"));
                            Parent root = loader.load();

                            ManutencaoFinalizadaController controller = loader.getController();
                           
                            controller.setServicos(manutencao.getServicos());
                            Stage stage = new Stage();
                            stage.setScene(new Scene(root));
                            stage.initStyle(StageStyle.UNDECORATED);
                            stage.initModality(Modality.APPLICATION_MODAL);
                            stage.showAndWait();

                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    } else {
                        try {
                            FXMLLoader loader = new FXMLLoader(getClass().getResource("ListaDeServicos.fxml"));
                            Parent root = loader.load();
                            
                            ListaDeServicosController controller = loader.getController();                           
                            controller.setManutencao(manutencao);
                            Stage stage = new Stage();
                            stage.setScene(new Scene(root));
                            stage.initStyle(StageStyle.UNDECORATED);
                            stage.initModality(Modality.APPLICATION_MODAL);
                            stage.showAndWait();
                            preencherTabela();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                }
            });
            return row;
        });
    }
}
