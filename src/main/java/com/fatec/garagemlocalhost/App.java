package com.fatec.garagemlocalhost;

import com.fatec.garagemlocalhost.database.Database;
import com.fatec.garagemlocalhost.model.entities.Usuario;
import com.fatec.garagemlocalhost.model.enums.TipoUsuario;
import com.fatec.garagemlocalhost.utils.Verificar;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {

        scene = new Scene(loadFXML("login"));
        stage.setScene(scene);
        stage.setWidth(354);
        stage.setHeight(534);
        stage.setResizable(false);
        stage.show();
         
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }

}