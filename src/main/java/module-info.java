module com.mycompany.locadoramanutencao {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.fatec.garagemlocalhost to javafx.fxml;
    exports com.fatec.garagemlocalhost;
}
