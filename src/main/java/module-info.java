module com.mycompany.locadoramanutencao {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.mycompany.locadoramanutencao to javafx.fxml;
    exports com.mycompany.locadoramanutencao;
}
