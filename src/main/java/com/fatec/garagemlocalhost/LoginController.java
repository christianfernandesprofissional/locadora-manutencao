/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fatec.garagemlocalhost;

import com.fatec.garagemlocalhost.database.DBException;
import com.fatec.garagemlocalhost.exceptions.LoginValidacaoException;
import com.fatec.garagemlocalhost.model.entities.Usuario;
import com.fatec.garagemlocalhost.services.UsuarioService;
import com.fatec.garagemlocalhost.utils.UsuarioHolder;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
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
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 *
 * @author Christian
 */
public class LoginController implements Initializable {

    @FXML
    private Button btnLogin;

    @FXML
    private Label lblLoginInvalido;

    @FXML
    private CheckBox cbExibirSenha;

    @FXML
    private PasswordField txtMaskSenha;

    @FXML
    private TextField txtUnmaskSenha;

    @FXML
    private TextField txtUsuario;

    private final UsuarioService usuarioService = new UsuarioService();

    @FXML
    public void logar(ActionEvent event) {

        lblLoginInvalido.setVisible(false);

        try {
            Optional<Usuario> usuarioBuscado = usuarioService.buscarPorNome(txtUsuario.getText());
            if (usuarioBuscado.isPresent() && !usuarioBuscado.get().getAtivo()) {
                lblLoginInvalido.setText("Usuário bloqueado, \n fale com o gerente!");
                lblLoginInvalido.setVisible(true);
                txtUsuario.setText("");
                txtUnmaskSenha.setText("");
                txtUsuario.requestFocus();
            } else {
                validarLogin(usuarioBuscado, event);
            }

        } catch (DBException | LoginValidacaoException | IOException e) {
            Alert alert = new Alert(AlertType.ERROR, e.getMessage(), ButtonType.CLOSE);
            alert.showAndWait();
        }

    }

    private void validarLogin(Optional<Usuario> usuarioBuscado, ActionEvent event) throws IOException {
        if (usuarioBuscado.isPresent()) {
            if (usuarioBuscado.get().getSenha().equals(txtUnmaskSenha.getText())) {
                // Fecha a janela de login
                Stage stageAtual = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stageAtual.close();

                UsuarioHolder.getInstance().setUsuario(usuarioBuscado.get());
                //Carrega a tela home
                FXMLLoader loader = new FXMLLoader(getClass().getResource("home.fxml"));
                Parent root = loader.load();
                Stage novoStage = new Stage();
                novoStage.setTitle("Home");
                novoStage.setScene(new Scene(root));
                novoStage.setFullScreen(true);
                novoStage.show();

            } else {
                lblLoginInvalido.setText("Usuário ou senha inválidos!");
                lblLoginInvalido.setVisible(true);
                txtUsuario.setText("");
                txtUnmaskSenha.setText("");
                txtUsuario.requestFocus();
            }
        } else {
            lblLoginInvalido.setText("Usuário não cadastrado!");
            lblLoginInvalido.setVisible(true);
            txtUsuario.setText("");
            txtUnmaskSenha.setText("");
            txtUsuario.requestFocus();
        }
    }

    /**
     * Este método configura a CheckBox cbExibirSenha
     *
     * @author Christian
     */
    @FXML
    private void setPropertiesCbExibirSenha() {

        //Deixa os textos dos campos sincronizados
        txtUnmaskSenha.textProperty().bindBidirectional(txtMaskSenha.textProperty());
        //Adiciona um Listener o CheckBox e define o que ele deve fazer quando estiver selecionado ou não
        cbExibirSenha.selectedProperty().addListener((obs, valorAnterior, valorNovo) -> {
            if (valorNovo) {
                txtMaskSenha.setVisible(false);
                txtMaskSenha.setManaged(false);
                txtUnmaskSenha.setVisible(true);
                txtUnmaskSenha.setManaged(true);
            } else {
                txtUnmaskSenha.setVisible(false);
                txtUnmaskSenha.setManaged(false);
                txtMaskSenha.setVisible(true);
                txtMaskSenha.setManaged(true);
            }
        });
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        setPropertiesCbExibirSenha();
    }

}
