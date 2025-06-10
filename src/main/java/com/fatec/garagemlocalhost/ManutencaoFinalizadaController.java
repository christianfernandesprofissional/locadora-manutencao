/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fatec.garagemlocalhost;

/**
 *
 * @author gustavo
 */
import com.fatec.garagemlocalhost.model.entities.Servico;
import java.math.BigDecimal;
import java.net.URL;
import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class ManutencaoFinalizadaController implements Initializable {

    @FXML
    private TableColumn<Servico, String> colunaDescricao;

    @FXML
    private TableColumn<Servico, Integer> colunaIdServico;

    @FXML
    private TableColumn<Servico, BigDecimal> colunaPreco;

    @FXML
    private Label lblPrecoTotal;

    @FXML
    private Button btnVoltar;

    @FXML
    private TableView<Servico> tabelaListaServicos;

    private ObservableList<Servico> listaDeServicos = FXCollections.observableArrayList();

    private List<Servico> servicos;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        configurarBotoes();
    }

    public void preencherCampos() {

        listaDeServicos.clear();
        listaDeServicos.addAll(servicos);
        colunaIdServico.setCellValueFactory(cellData -> {
            Servico servico = cellData.getValue();
            return new SimpleIntegerProperty(servico.getIdServico()).asObject();
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

        tabelaListaServicos.setItems(listaDeServicos);
    }

    public void configurarBotoes() {
        btnVoltar.setOnAction(event -> {
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.close();
        });
    }

    public void preencherValorTotal() {
        BigDecimal total = BigDecimal.ZERO;
        for (Servico servico : servicos) {
            total = total.add(servico.getPreco());
        }
        NumberFormat formatoMoeda = NumberFormat.getCurrencyInstance(Locale.forLanguageTag("pt-BR"));
 
        lblPrecoTotal.setText(formatoMoeda.format(total));

    }

    public void setServicos(List<Servico> servicos) {
        this.servicos = servicos;
        preencherCampos();
        preencherValorTotal();
    }

}
