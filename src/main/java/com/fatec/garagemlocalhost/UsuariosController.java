/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fatec.garagemlocalhost;

import com.fatec.garagemlocalhost.database.DBException;
import com.fatec.garagemlocalhost.model.entities.Usuario;
import com.fatec.garagemlocalhost.model.enums.TipoUsuario;
import com.fatec.garagemlocalhost.services.UsuarioService;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 *
 * @author chris
 */
public class UsuariosController implements Initializable {

    @FXML
    private Button btnSalvar;

    @FXML
    private Button btnLimpar;

    @FXML
    private ComboBox<TipoUsuario> cbTipoUsuario;

    @FXML
    private TableColumn<Usuario, String> colunaEmail;

    @FXML
    private TableColumn<Usuario, TipoUsuario> colunaFuncao;

    @FXML
    private TableColumn<Usuario, String> colunaNome;

    @FXML
    private TableColumn<Usuario, String> colunaSituacao;

    @FXML
    private RadioButton rbInativo;

    @FXML
    private RadioButton rbAtivo;

    @FXML
    private TableView<Usuario> tabelaUsuarios;

    @FXML
    private ToggleGroup statusUsuario;

    @FXML
    private TextField txtID;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtNomeUsuario;

    @FXML
    private PasswordField txtSenha;

    private ObservableList<Usuario> listaDeUsuarios = FXCollections.observableArrayList();

    private final UsuarioService usuarioService = new UsuarioService();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        preencherTabela();
        preencherCmb();
        preencherCampos();
    }

    public void preencherCampos() {
        tabelaUsuarios.setRowFactory(t -> {
            TableRow<Usuario> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && !row.isEmpty()) {
                    Usuario usuario = row.getItem();
                    txtID.setText(String.valueOf(usuario.getId()));
                    txtSenha.setText(String.valueOf(usuario.getSenha()));
                    txtNomeUsuario.setText(usuario.getNome());
                    txtEmail.setText(usuario.getEmail());
                    cbTipoUsuario.getSelectionModel().select(usuario.getTipoUsuario());
                    if (usuario.getAtivo()) {
                        rbAtivo.setSelected(true);
                    } else {
                        rbInativo.setSelected(true);
                    }
                }
            });
            return row;
        });
    }

    private void preencherTabela() {
        try {
            listaDeUsuarios.clear();
            listaDeUsuarios.addAll(usuarioService.buscarTodosUsuarios());
            colunaNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
            colunaEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
            colunaFuncao.setCellValueFactory(new PropertyValueFactory<>("tipoUsuario"));
            colunaSituacao.setCellValueFactory(cellData -> {
                boolean ativo = cellData.getValue().getAtivo();
                return new SimpleStringProperty(ativo ? "ATIVO" : "INATIVO");
            });

            tabelaUsuarios.setItems(listaDeUsuarios);
        } catch (DBException e) {
            System.out.println("Erro ao carregar tabela: " + e.getMessage());
        }
    }

    public void preencherCmb() {
        ObservableList<TipoUsuario> lista = FXCollections.observableArrayList();;
        lista.add(TipoUsuario.GERENTE);
        lista.add(TipoUsuario.AUXILIAR);
        cbTipoUsuario.setItems(lista);
    }

    @FXML
    public void limparCampos() {

        txtID.setText(null);
        txtNomeUsuario.setText(null);
        txtEmail.setText(null);
        txtSenha.setText(null);
        rbAtivo.setSelected(false);
        rbInativo.setSelected(false);

    }

    /**
     * Cria ou atualiza um usuário
     * 
     * @author Christian
     */
    @FXML
    public void salvarUsuario() {
        if (txtID.getText().isEmpty()) {
            Usuario usuario = new Usuario();
            usuario.setNome(txtNomeUsuario.getText());
            usuario.setEmail(txtEmail.getText());
            usuario.setSenha(txtSenha.getText());
            usuario.setTipoUsuario(cbTipoUsuario.getValue());
            if (statusUsuario.getSelectedToggle() != null) {
                RadioButton selecionado = (RadioButton) statusUsuario.getSelectedToggle();
                String texto = selecionado.getText();
                usuario.setAtivo(texto.equals("ATIVO"));
            }
            try {
                usuarioService.criarUsuario(usuario);
            } catch (DBException e) {
                Alert alert = new Alert(AlertType.ERROR, e.getMessage());
                alert.showAndWait();
            }
            preencherTabela();
            limparCampos();
        } else {
            Usuario usuario = new Usuario();
            usuario.setId(Integer.valueOf(txtID.getText()));
            usuario.setNome(txtNomeUsuario.getText());
            usuario.setEmail(txtEmail.getText());
            usuario.setSenha(txtSenha.getText());
            usuario.setTipoUsuario(cbTipoUsuario.getValue());
            if (statusUsuario.getSelectedToggle() != null) {
                RadioButton selecionado = (RadioButton) statusUsuario.getSelectedToggle();
                String texto = selecionado.getText();
                usuario.setAtivo(texto.equals("ATIVO"));
            }
            try {
                usuarioService.atualizarUsuario(usuario);
                preencherTabela();
                limparCampos();
            } catch (DBException e) {
                Alert alert = new Alert(AlertType.ERROR, e.getMessage());
                alert.showAndWait();
            }
        }
    }
}
