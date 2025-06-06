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
import java.net.URL;
import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

/**
 *
 * @author chris
 */
public class VeiculosController implements Initializable {
   
    @FXML
    private Button btnLimparFiltros;

    @FXML
    private TableColumn<Veiculo, String> colunaPlaca;

    @FXML
    private TableColumn<Veiculo, String> colunaMarca;

    @FXML
    private TableColumn<Veiculo, String> colunaCor;

    @FXML
    private TableColumn<Veiculo, Integer> colunaAno;

    @FXML
    private TableColumn<Veiculo, String> colunaChassi;

    @FXML
    private TableColumn<Veiculo, String> colunaModelo;

    @FXML
    private TableColumn<Veiculo, Integer> colunaQuilometragem;

    @FXML
    private TableColumn<Veiculo, CategoriaVeiculo> colunaCategoria;

    @FXML
    private TableColumn<Veiculo, BigDecimal> colunaPrecoBase;

    @FXML
    private TableColumn<Veiculo, SituacaoVeiculo> colunaSituacao;

    @FXML
    private TableView<Veiculo> tabelaVeiculos;

    @FXML
    private ComboBox<Integer> cbAno;

    @FXML
    private ComboBox<CategoriaVeiculo> cbCategoria;

    @FXML
    private RadioButton rbCor;

    @FXML
    private RadioButton rbMarca;

    @FXML
    private RadioButton rbModelo;

    @FXML
    private RadioButton rbPlaca;
    
    @FXML
    private RadioButton rbDisponivel;

    @FXML
    private RadioButton rbAlugado;

    @FXML
    private RadioButton rbEmManutencao;


    @FXML
    private TextField txtBusca;

    private VeiculoService veiculoService = new VeiculoService();

    private CategoriaService categoriaService = new CategoriaService();

    private ObservableList<Veiculo> listaDeVeiculos = FXCollections.observableArrayList();

    private FilteredList<Veiculo> listaFiltrada = new FilteredList<>(listaDeVeiculos, v -> true);

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        rbModelo.setSelected(true);
        preencherTabela();
        preencherTodasComboBox();
        configurarListenersDeBusca();
        aplicarFiltroNaListaDeVeiculos(listaFiltrada);
        editarVeiculoDoubleclick();

    }

    public void preencherTodasComboBox() {
        List<Integer> anos = listaDeVeiculos.stream().map(v -> v.getAno()).distinct().collect(Collectors.toList());
        ObservableList<Integer> listaAnos = FXCollections.observableArrayList();
        listaAnos.addAll(anos);
        cbAno.setItems(listaAnos);

        try {
            ObservableList<CategoriaVeiculo> listaCategorias = FXCollections.observableArrayList();
            listaCategorias.addAll(categoriaService.buscarTodasCategorias());
            cbCategoria.setItems(listaCategorias);
        } catch (DBException e) {
            Alert alert = new Alert(AlertType.ERROR, "Erro ao preencher comboBox de categorias");
            alert.showAndWait();
        }
    }

    /**
     * Adiciona Listeners a todos os elementos
     * relacionados a busca de veículos.
     * 
     * @author Christian
     */
    public void configurarListenersDeBusca() {
        tabelaVeiculos.setItems(listaFiltrada);
        txtBusca.textProperty().addListener((obs, oldVal, newVal)
                -> aplicarFiltroNaListaDeVeiculos(listaFiltrada));

        cbAno.valueProperty().addListener((obs, oldVal, newVal)
                -> aplicarFiltroNaListaDeVeiculos(listaFiltrada));

        cbCategoria.valueProperty().addListener((obs, oldVal, newVal)
                -> aplicarFiltroNaListaDeVeiculos(listaFiltrada));

        rbPlaca.selectedProperty().addListener((obs, oldVal, newVal)
                -> aplicarFiltroNaListaDeVeiculos(listaFiltrada));

        rbCor.selectedProperty().addListener((obs, oldVal, newVal)
                -> aplicarFiltroNaListaDeVeiculos(listaFiltrada));

        rbMarca.selectedProperty().addListener((obs, oldVal, newVal)
                -> aplicarFiltroNaListaDeVeiculos(listaFiltrada));

        rbModelo.selectedProperty().addListener((obs, oldVal, newVal)
                -> aplicarFiltroNaListaDeVeiculos(listaFiltrada));
        
        rbDisponivel.selectedProperty().addListener((obs, oldVal, newVal)
                -> aplicarFiltroNaListaDeVeiculos(listaFiltrada));

        rbAlugado.selectedProperty().addListener((obs, oldVal, newVal)
                -> aplicarFiltroNaListaDeVeiculos(listaFiltrada));

        rbEmManutencao.selectedProperty().addListener((obs, oldVal, newVal)
                -> aplicarFiltroNaListaDeVeiculos(listaFiltrada));

        btnLimparFiltros.setOnAction(e -> {
            limparCampos();
            aplicarFiltroNaListaDeVeiculos(listaFiltrada);         
        });
    }

    public void limparCampos() {
        txtBusca.setText("");
        rbModelo.setSelected(true);
        rbDisponivel.setSelected(false);
        rbAlugado.setSelected(false);
        rbEmManutencao.setSelected(false);
        cbAno.setValue(null);
        cbCategoria.setValue(null);
    }

    /**
     * Filtra a lista de veículos to vez 
     * que é chamado.
     * 
     * @author Christian
     * @param listaFiltrada 
     */
    public void aplicarFiltroNaListaDeVeiculos(FilteredList<Veiculo> listaFiltrada) {
        listaFiltrada.setPredicate(veiculo -> {

            String textoBusca = txtBusca.getText().toLowerCase();
            Integer ano = cbAno.getValue();
            CategoriaVeiculo categoria = cbCategoria.getValue();

            Boolean textoCorresponde = true;
            if (rbPlaca.isSelected() && textoBusca != null && !textoBusca.isEmpty()) {
                textoCorresponde = veiculo.getPlaca().toLowerCase().contains(textoBusca);
            } else if (rbCor.isSelected() && textoBusca != null && !textoBusca.isEmpty()) {
                textoCorresponde = veiculo.getCor().toLowerCase().contains(textoBusca);
            } else if (rbMarca.isSelected() && textoBusca != null && !textoBusca.isEmpty()) {
                textoCorresponde = veiculo.getMarca().toLowerCase().contains(textoBusca);
            } else if (rbModelo.isSelected() && textoBusca != null && !textoBusca.isEmpty()) {
                textoCorresponde = veiculo.getModelo().toLowerCase().contains(textoBusca);
            }

            Boolean categoriaCorresponde = true;
            if (categoria != null) {
                categoriaCorresponde = veiculo.getCategoria().equals(categoria);
            }

            Boolean anoCorresponde = true;
            if (ano != null) {
                anoCorresponde = veiculo.getAno().equals(ano);
            }
            
            Boolean situacao = true;
            if(rbDisponivel.isSelected()){
                situacao = veiculo.getSituacao() == SituacaoVeiculo.DISPONÍVEL;
            }else if(rbAlugado.isSelected()){
                situacao = veiculo.getSituacao() == SituacaoVeiculo.ALUGADO;
            }else if(rbEmManutencao.isSelected()){
                situacao = veiculo.getSituacao() == SituacaoVeiculo.EM_MANUTENÇÃO;
            }

            return textoCorresponde && categoriaCorresponde && anoCorresponde && situacao;
        });
    }

    private void preencherTabela() {
        try {
            listaDeVeiculos.clear();
            listaDeVeiculos.addAll(veiculoService.buscarTodosVeiculos());
            colunaPlaca.setCellValueFactory(new PropertyValueFactory<>("placa"));
            colunaMarca.setCellValueFactory(new PropertyValueFactory<>("marca"));
            colunaCor.setCellValueFactory(new PropertyValueFactory<>("cor"));
            colunaAno.setCellValueFactory(new PropertyValueFactory<>("ano"));
            colunaChassi.setCellValueFactory(new PropertyValueFactory<>("chassi"));
            colunaModelo.setCellValueFactory(new PropertyValueFactory<>("modelo"));
            colunaQuilometragem.setCellValueFactory(new PropertyValueFactory<>("quilometragem"));
            colunaCategoria.setCellValueFactory(new PropertyValueFactory<>("categoria"));
            colunaPrecoBase.setCellValueFactory(new PropertyValueFactory<>("precoBase"));
            colunaSituacao.setCellValueFactory(new PropertyValueFactory<>("situacao"));

            //O trecho abaixo formata a colunaPrecoBase para que exiba como um valor monetário
            colunaPrecoBase.setCellFactory(coluna -> new TableCell<>() {
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
        } catch (DBException e) {
            System.out.println("Erro ao carregar tabela: " + e.getMessage());
        }
    }
    
    public void editarVeiculoDoubleclick() {
        tabelaVeiculos.setRowFactory(t -> {
            TableRow<Veiculo> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && !row.isEmpty()) {
                    Veiculo veiculo = row.getItem();
                    try {
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("CadastroVeiculo.fxml"));
                        Parent root = loader.load();
                        
                        CadastroVeiculoController controller = loader.getController();
                        
                        controller.setVeiculo(veiculo);
               
                        BorderPane bp = (BorderPane)tabelaVeiculos.getParent().getParent();
                        bp.setCenter(root);
                    }catch(IOException e){
                        e.printStackTrace();
                    }

                }
            });
            return row;
        });
    }
    
}
