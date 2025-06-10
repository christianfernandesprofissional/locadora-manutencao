/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fatec.garagemlocalhost;

import com.fatec.garagemlocalhost.database.DBException;
import com.fatec.garagemlocalhost.model.entities.SaidaVeiculo;
import com.fatec.garagemlocalhost.model.entities.Veiculo;
import com.fatec.garagemlocalhost.model.enums.SituacaoSaida;
import com.fatec.garagemlocalhost.services.SaidaService;
import com.fatec.garagemlocalhost.services.VeiculoService;
import com.fatec.garagemlocalhost.utils.UsuarioHolder;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author chris
 */
public class TelaInicialController implements Initializable {

    @FXML
    private Button btnNovamanutencao;

    @FXML
    private TableColumn<SaidaVeiculo, Integer> colunaIdPedido;

    @FXML
    private TableColumn<SaidaVeiculo, Integer> colunaIdSaida;

    @FXML
    private TableColumn<SaidaVeiculo, String> colunaVeiculoSolicitado;

    @FXML
    private TableColumn<SaidaVeiculo, String> colunaNomeDoCliente;

    @FXML
    private Label lblTotalFrota;

    @FXML
    private Label lblVeiculosAlugados;

    @FXML
    private Label lblVeiculosDisponiveis;

    @FXML
    private Label lblUsuarioLogado;

    @FXML
    private Label lblVeiculosEmManutencao;

    @FXML
    private TableView<SaidaVeiculo> tabelaAguardandoSaida;

    private SaidaService saidaService = new SaidaService();

    private VeiculoService veiculoService = new VeiculoService();

    private ObservableList<SaidaVeiculo> listaDeSaidas = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        preencherTabela();
        preencherLabels();
        lblUsuarioLogado.setText(UsuarioHolder.getInstance().getUsuario().getNome());

        btnNovamanutencao.setOnAction(e -> {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("NovaManutencao.fxml"));
                Parent root = loader.load();
                Stage stage = new Stage();
                stage.setScene(new Scene(root));
                stage.setWidth(544);
                stage.setHeight(330);
                stage.setResizable(false);
                stage.initStyle(StageStyle.UNDECORATED);
                //Bloqueia o acesso a tela de trás
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.showAndWait();
            } catch (IOException erro) {
                erro.printStackTrace();
            }
        });
    }

    private void preencherTabela() {
        try {
            listaDeSaidas.clear();
            List<SaidaVeiculo> todasSaidas = saidaService.buscarTodasAsSaidas();

            listaDeSaidas.addAll(todasSaidas.stream().filter(veiculo -> veiculo.getSituacao().equals(SituacaoSaida.AGUARDANDO_ENTREGA)).collect(Collectors.toList()));
            colunaIdSaida.setCellValueFactory(new PropertyValueFactory<>("id"));
            colunaIdPedido.setCellValueFactory(new PropertyValueFactory<>("idPedido"));

            colunaVeiculoSolicitado.setCellValueFactory(cellData -> {
                SaidaVeiculo saida = cellData.getValue();
                return new SimpleStringProperty(saida.getVeiculo().toString());
            });

            colunaNomeDoCliente.setCellValueFactory(cellData -> {
                String nome = "";
                try {
                    nome = saidaService.buscarNomeDoCliente(cellData.getValue());
                } catch (DBException ex) {
                    ex.printStackTrace();
                }
                return new SimpleStringProperty(nome);

            });

            tabelaAguardandoSaida.setItems(listaDeSaidas);

        } catch (DBException e) {
            System.out.println("Erro ao carregar tabela: " + e.getMessage());
        }
    }

    public void preencherLabels() {
        try {
            List<Veiculo> veiculos = veiculoService.buscarTodosVeiculos();
            lblTotalFrota.setText(String.valueOf(veiculos.size()));

            int alugados = 0;
            int manutencao = 0;
            int disponivel = 0;

            for (Veiculo v : veiculos) {
                switch (v.getSituacao()) {
                    case ALUGADO:
                        alugados += 1;
                        break;
                    case EM_MANUTENÇÃO:
                        manutencao += 1;
                        break;
                    default:
                        disponivel += 1;
                        break;
                }
            }

            lblVeiculosAlugados.setText(String.valueOf(alugados));
            lblVeiculosDisponiveis.setText(String.valueOf(disponivel));
            lblVeiculosEmManutencao.setText(String.valueOf(manutencao));

        } catch (DBException e) {

        }
    }

}
