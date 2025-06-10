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
import java.util.ArrayList;
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
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

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
    private TableColumn<Servico, Integer> colunaIdServico;

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

    private ArrayList<Servico> servicosEditados = new ArrayList<>();

    private String descricaoAntiga;

    private String valorAntigo;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        preencherCampos();
        editarServicoDoubleclick();
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

        tabelaServicos.setItems(listaDeServicos);
    }

    public void editarServicoDoubleclick() {
        tabelaServicos.setRowFactory(t -> {
            TableRow<Servico> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && !row.isEmpty()) {
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
                    desabilitarCampos();
                    listaDeServicos.remove(servico);
                }
            });
            return row;
        });
    }

    public void configurarBotoes() {
        btnAdicionarServico.setOnAction(e -> {
            Servico servico = new Servico();

            if (!txtId.getText().isBlank()) {
                servico.setIdServico(Integer.valueOf(txtId.getText()));
            }
            
            if(isDecimal(txtValorServico.getText().trim())){
                servico.setPreco(new BigDecimal(txtValorServico.getText().trim()));
            }else{
                lblValorInvalido.setVisible(true);
            }
            
           if(txtDescricaoServico.getText().isBlank()){
               lblErroDescricao.setVisible(true);
           }else{
               servico.setDescricao(txtDescricaoServico.getText().trim());
           }
           
           if(cbIsComum.isSelected()){
               servico.setIsComum(Boolean.TRUE);
           }else{
               servico.setIsComum(Boolean.FALSE);
           }
           listaDeServicos.add(servico);
        });
        
        btnCancelar.setOnAction(event -> {
              Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.close();
        });
    }

    public void desabilitarCampos() {
        txtDescricaoServico.setDisable(true);
        txtValorServico.setDisable(true);
        cbIsComum.setDisable(true);
        tabelaServicos.setDisable(true);
        btnEditar.setDisable(false);
        lblValorInvalido.setVisible(false);
        lblErroDescricao.setVisible(false);
    }

    public void HabilitarCampos() {
        txtDescricaoServico.setDisable(false);
        txtValorServico.setDisable(false);
        cbIsComum.setDisable(false);
        btnEditar.setDisable(true);
        tabelaServicos.setDisable(false);
        lblValorInvalido.setVisible(false);
        lblErroDescricao.setVisible(false);
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
