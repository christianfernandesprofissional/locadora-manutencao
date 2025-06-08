/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fatec.garagemlocalhost;

import com.fatec.garagemlocalhost.database.DBException;
import com.fatec.garagemlocalhost.model.entities.DevolucaoVeiculo;
import com.fatec.garagemlocalhost.model.entities.SaidaVeiculo;
import com.fatec.garagemlocalhost.services.DevolucaoService;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author Christian
 */
public class DevolucoesController implements Initializable {

    @FXML
    private TableColumn<DevolucaoVeiculo, Integer> colunaAno;

    @FXML
    private TableColumn<DevolucaoVeiculo, String> colunaCor;

    @FXML
    private TableColumn<DevolucaoVeiculo, Integer> colunaIdPedido;
    
       @FXML
    private TableColumn<DevolucaoVeiculo, String> colunaAuxiliar;

    @FXML
    private TableColumn<DevolucaoVeiculo, Integer> colunaIdDevolucao;

    @FXML
    private TableColumn<DevolucaoVeiculo, String> colunaInstante;

    @FXML
    private TableColumn<DevolucaoVeiculo, String> colunaKmChegada;

    @FXML
    private TableColumn<DevolucaoVeiculo, String> colunaModelo;

    @FXML
    private TableColumn<DevolucaoVeiculo, String> colunaPlaca;

    @FXML
    private TableColumn<DevolucaoVeiculo, String> colunaStatus;

    @FXML
    private TableView<DevolucaoVeiculo> tabelaDevolucoes;

    private DevolucaoService devolucaoService = new DevolucaoService();

    private ObservableList<DevolucaoVeiculo> listaDeDevolucoes = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        preencherTabela();
        preencherCamposComClick();
    }
    
    
    
     public void preencherCamposComClick() {
        tabelaDevolucoes.setRowFactory(t -> {
            TableRow<DevolucaoVeiculo> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && !row.isEmpty()) {
                    DevolucaoVeiculo devolucao = row.getItem();
                    try {
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("SubDevolucoes.fxml"));
                        Parent root = loader.load();

                        SubDevolucoesController controller = loader.getController();

                        controller.setDevolucao(devolucao);

                        Stage stage = new Stage();
                        stage.setScene(new Scene(root));
                        stage.initStyle(StageStyle.UNDECORATED);
                        //Bloqueia o acesso a tela de trás
                        stage.initModality(Modality.APPLICATION_MODAL);
                        stage.showAndWait();
                        preencherTabela();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }
            });
            return row;
        });
    }

    private void preencherTabela() {
        try {
            listaDeDevolucoes.clear();
            listaDeDevolucoes.addAll(devolucaoService.buscarTodasDevolucao());
            colunaIdDevolucao.setCellValueFactory(new PropertyValueFactory<>("idDevolucao"));
            colunaIdPedido.setCellValueFactory(new PropertyValueFactory<>("idPedido"));
            colunaAuxiliar.setCellValueFactory(cellData -> {
                DevolucaoVeiculo devolucao = cellData.getValue();
                if(devolucao.getAssistente() == null){
                    return new SimpleStringProperty("");
                }
                return new SimpleStringProperty(devolucao.getAssistente().getNome());
            });

            colunaModelo.setCellValueFactory(cellData -> {
               DevolucaoVeiculo devolucao = cellData.getValue();
                return new SimpleStringProperty(devolucao.getVeiculo().getModelo());
            });
            colunaPlaca.setCellValueFactory(cellData -> {
                DevolucaoVeiculo devolucao = cellData.getValue();
                return new SimpleStringProperty(devolucao.getVeiculo().getPlaca());
            });

            colunaAno.setCellValueFactory(cellData -> {
                DevolucaoVeiculo devolucao = cellData.getValue();
                return new SimpleIntegerProperty(devolucao.getVeiculo().getAno()).asObject();
            });

            colunaKmChegada.setCellValueFactory(cellData -> {
                Integer km = cellData.getValue().getKmChegada();
                if (km == null) {
                    return new SimpleStringProperty("");
                }
                return new SimpleStringProperty(String.valueOf(km));
            });
            
            
            colunaInstante.setCellValueFactory(cellData -> {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
                LocalDateTime instante = cellData.getValue().getInstanteDevolucao();
                if(instante == null){
                    return new SimpleStringProperty("");
                }
                return new SimpleStringProperty(instante.format(formatter));
            });
            colunaStatus.setCellValueFactory(cellData -> {
                DevolucaoVeiculo devolucao = cellData.getValue();
                return new SimpleStringProperty(devolucao.getSituacao().toString());
            });

            tabelaDevolucoes.setItems(listaDeDevolucoes);

        } catch (DBException e) {
            System.out.println("Erro ao carregar tabela: " + e.getMessage());
        }
    }
}
