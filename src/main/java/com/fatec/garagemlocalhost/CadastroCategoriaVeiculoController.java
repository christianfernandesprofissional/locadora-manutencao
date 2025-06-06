/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fatec.garagemlocalhost;

import com.fatec.garagemlocalhost.database.DBException;
import com.fatec.garagemlocalhost.model.entities.CategoriaVeiculo;
import com.fatec.garagemlocalhost.services.CategoriaService;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 *
 * @author chris
 */
public class CadastroCategoriaVeiculoController implements Initializable{
   
    @FXML
    private Button btnAdicionar;

    @FXML
    private Button btnCancelar;

    @FXML
    private Button btnDeletar;

    @FXML
    private Button btnSalvar;

    @FXML
    private TableColumn<CategoriaVeiculo, String> colunaDescricao;

    @FXML
    private TableView<CategoriaVeiculo> tabelaCategorias;

    @FXML
    private TextField txtDescricao;

    @FXML
    private TextField txtIdCategoria;
    
    private ObservableList<CategoriaVeiculo> listaCategorias = FXCollections.observableArrayList();
    private ArrayList<CategoriaVeiculo> listaCategoriasEdicao = new ArrayList<>();
    private ArrayList<CategoriaVeiculo> listaCategoriasDelecao = new ArrayList<>();
    
    private CategoriaService categoriaService = new CategoriaService();


    @Override
    public void initialize(URL url, ResourceBundle rb) {
       preencherTabela();
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
    
}
