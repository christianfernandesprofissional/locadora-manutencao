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
import com.fatec.garagemlocalhost.model.entities.Manutencao;
import com.fatec.garagemlocalhost.model.entities.Servico;
import com.fatec.garagemlocalhost.services.ManutencaoServicoService;
import com.fatec.garagemlocalhost.services.ServicoService;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class ListaDeServicosController implements Initializable {

    @FXML
    private Button btnAdicionarNovoServico;

    @FXML
    private Button btnCancelar;

    @FXML
    private Button btnFinalizarManutencao;

    @FXML
    private TableColumn<Servico, String> colunaDescricaoM;

    @FXML
    private TableColumn<Servico, String> colunaDescricaoT;

    @FXML
    private TableColumn<Servico, Integer> colunaIdServicoM;

    @FXML
    private TableColumn<Servico, Integer> colunaIdServicoT;

    @FXML
    private TableColumn<Servico, BigDecimal> colunaPrecoM;

    @FXML
    private TableColumn<Servico, BigDecimal> colunaPrecoT;

    @FXML
    private Label lblPrecoFinal;

    @FXML
    private TableView<Servico> tabelaServicosManutencao;

    @FXML
    private TableView<Servico> tabelaTodosServicos;

    private ObservableList<Servico> listaTodosServicos = FXCollections.observableArrayList();

    private ObservableList<Servico> listaServicosManutencao = FXCollections.observableArrayList();

    private ServicoService servicoService = new ServicoService();

    private ManutencaoServicoService msService = new ManutencaoServicoService();

    private Manutencao manutencao;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        configurarBotoes();
        preencherTabelaServicosManutencao();
        preencherTabelaTodosServicos();
        tabelaServicosManutencaoDoubleClick();
        tabelaTodosServicosDoubleClick();
    }

    private void preencherTabelaTodosServicos() {
        try {
            listaTodosServicos.clear();
            listaTodosServicos.addAll(servicoService.buscarTodosServicos());
            colunaIdServicoT.setCellValueFactory(new PropertyValueFactory<>("idServico"));
            colunaDescricaoT.setCellValueFactory(cellData -> {
                Servico servico = cellData.getValue();
                return new SimpleStringProperty(servico.getDescricao());
            });

            colunaPrecoT.setCellValueFactory(new PropertyValueFactory<>("preco"));

            //O trecho abaixo formata a colunaPrecoBase para que exiba como um valor monetário
            colunaPrecoT.setCellFactory(coluna -> new TableCell<>() {
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

            tabelaTodosServicos.setItems(listaTodosServicos);
        } catch (DBException e) {
            System.out.println("Erro ao carregar tabela: " + e.getMessage());
        }

    }

    private void preencherTabelaServicosManutencao() {

        listaServicosManutencao.clear();
        colunaIdServicoM.setCellValueFactory(new PropertyValueFactory<>("idServico"));
        colunaDescricaoM.setCellValueFactory(cellData -> {
            Servico servico = cellData.getValue();
            return new SimpleStringProperty(servico.getDescricao());
        });

        colunaPrecoM.setCellValueFactory(new PropertyValueFactory<>("preco"));

        //O trecho abaixo formata a colunaPrecoBase para que exiba como um valor monetário
        colunaPrecoM.setCellFactory(coluna -> new TableCell<>() {
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

        tabelaServicosManutencao.setItems(listaServicosManutencao);

    }

    public void configurarBotoes() {
        btnAdicionarNovoServico.setOnAction(e -> {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("EdicaoServicos.fxml"));
                Parent root = loader.load();
                Stage stage = new Stage();
                stage.setScene(new Scene(root));
                stage.setResizable(false);
                stage.initStyle(StageStyle.UNDECORATED);
                //Bloqueia o acesso a tela de trás
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.showAndWait();
                preencherTabelaTodosServicos();
            } catch (IOException error) {
                error.printStackTrace();
            }
        });

        btnCancelar.setOnAction(event -> {
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.close();
        });

        btnFinalizarManutencao.setOnAction(e -> {
            Alert alert = new Alert(AlertType.CONFIRMATION);

            alert.setTitle("Salvar alterações");
            alert.setHeaderText("Tem certeza que deseja finalizar esta manutenção?");
            ButtonType sim = new ButtonType("Sim");
            alert.getButtonTypes().setAll(sim, new ButtonType("Não"));
            alert.initStyle(StageStyle.UNDECORATED);
            alert.initModality(Modality.APPLICATION_MODAL);
            Optional<ButtonType> resultado = alert.showAndWait();

            if (resultado.get() == sim) {
                try {
                    BigDecimal total = BigDecimal.ZERO;
                    for (Servico servico : listaServicosManutencao) {
                        total = total.add(servico.getPreco());
                    }

                    manutencao.setValorTotal(total);
                    manutencao.setServicos(listaServicosManutencao);
                    msService.insereManutencaoServico(manutencao);
                    btnCancelar.fire();
                } catch (DBException error) {
                    Alert err = new Alert(AlertType.ERROR, "Erro ao finalizar manutenção!");
                    err.showAndWait();
                }
            }

        });
    }

    public void tabelaTodosServicosDoubleClick() {
        tabelaTodosServicos.setRowFactory(t -> {
            TableRow<Servico> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && !row.isEmpty()) {
                    Servico servico = row.getItem();
                    listaServicosManutencao.add(servico);
                    listaTodosServicos.remove(servico);
                    preencherValorTotal(listaServicosManutencao);
                }
            });
            return row;
        });
    }

    public void tabelaServicosManutencaoDoubleClick() {
        tabelaServicosManutencao.setRowFactory(t -> {
            TableRow<Servico> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && !row.isEmpty()) {
                    Servico servico = row.getItem();
                    listaServicosManutencao.remove(servico);
                    listaTodosServicos.add(servico);
                    preencherValorTotal(listaServicosManutencao);
                }
            });
            return row;
        });
    }

    public void preencherValorTotal(ObservableList<Servico> servicos) {
        BigDecimal total = BigDecimal.ZERO;
        for (Servico servico : servicos) {
            total = total.add(servico.getPreco());
        }
        NumberFormat formatoMoeda = NumberFormat.getCurrencyInstance(Locale.forLanguageTag("pt-BR"));

        lblPrecoFinal.setText(formatoMoeda.format(total));

    }

    public void setManutencao(Manutencao manutencao) {
        this.manutencao = manutencao;
    }

}
