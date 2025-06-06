/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fatec.garagemlocalhost;

import com.fatec.garagemlocalhost.database.DBException;
import com.fatec.garagemlocalhost.model.entities.SaidaVeiculo;
import com.fatec.garagemlocalhost.services.SaidaService;
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
public class SaidasController implements Initializable {

    @FXML
    private TableView<SaidaVeiculo> tabelaSaida;

    @FXML
    private TableColumn<SaidaVeiculo, Integer> colunaIdSaida;

    @FXML
    private TableColumn<SaidaVeiculo, Integer> colunaIdPedido;

    @FXML
    private TableColumn<SaidaVeiculo, String> colunaAuxiliar;

    @FXML
    private TableColumn<SaidaVeiculo, String> colunaModelo;

    @FXML
    private TableColumn<SaidaVeiculo, String> colunaPlaca;

    @FXML
    private TableColumn<SaidaVeiculo, Integer> colunaAno;

    @FXML
    private TableColumn<SaidaVeiculo, Integer> colunaKmSaida;

    @FXML
    private TableColumn<SaidaVeiculo, String> colunaInstante;

    @FXML
    private TableColumn<SaidaVeiculo, String> colunaStatus;

    private SaidaService saidaService = new SaidaService();

    private ObservableList<SaidaVeiculo> listaDeSaidas = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        preencherTabela();
        preencherCamposComClick();
    }

    public void preencherCamposComClick() {
        tabelaSaida.setRowFactory(t -> {
            TableRow<SaidaVeiculo> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && !row.isEmpty()) {
                    SaidaVeiculo saida = row.getItem();
                    try {
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("SubSaidas.fxml"));
                        Parent root = loader.load();
                        
                        SubSaidasController controller = loader.getController();
                        
                        controller.setSaida(saida);   
               
                        Stage stage = new Stage();
                        stage.setScene(new Scene(root));
                        stage.initStyle(StageStyle.UNDECORATED);
                        //Bloqueia o acesso a tela de trás
                        stage.initModality(Modality.APPLICATION_MODAL);
                        stage.showAndWait();
                        preencherTabela();
                    }catch(IOException e){
                        e.printStackTrace();
                    }

                }
            });
            return row;
        });
    }

    private void preencherTabela() {
        try {
            listaDeSaidas.clear();
            listaDeSaidas.addAll(saidaService.buscarTodasAsSaidas());
            colunaIdSaida.setCellValueFactory(new PropertyValueFactory<>("id"));
            colunaIdPedido.setCellValueFactory(new PropertyValueFactory<>("idPedido"));
            colunaAuxiliar.setCellValueFactory(cellData -> {
                SaidaVeiculo saida = cellData.getValue();
                return new SimpleStringProperty(saida.getUsuario().getNome());
            });

            colunaModelo.setCellValueFactory(cellData -> {
                SaidaVeiculo saida = cellData.getValue();
                return new SimpleStringProperty(saida.getVeiculo().getModelo());
            });
            colunaPlaca.setCellValueFactory(cellData -> {
                SaidaVeiculo saida = cellData.getValue();
                return new SimpleStringProperty(saida.getVeiculo().getPlaca());
            });

            colunaAno.setCellValueFactory(cellData -> {
                SaidaVeiculo saida = cellData.getValue();
                return new SimpleIntegerProperty(saida.getVeiculo().getAno()).asObject();
            });

            colunaKmSaida.setCellValueFactory(new PropertyValueFactory<>("kmSaida"));
            colunaInstante.setCellValueFactory(cellData -> {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
                LocalDateTime instante = cellData.getValue().getInstanteSaida();
                return new SimpleStringProperty(instante.format(formatter));
            });
            colunaStatus.setCellValueFactory(cellData -> {
                SaidaVeiculo saida = cellData.getValue();
                return new SimpleStringProperty(saida.getSituacao().toString());
            });

            tabelaSaida.setItems(listaDeSaidas);

        } catch (DBException e) {
            System.out.println("Erro ao carregar tabela: " + e.getMessage());
        }
    }

}
