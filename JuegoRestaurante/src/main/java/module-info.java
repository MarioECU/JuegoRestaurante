module Restaurante {
    requires javafx.controls;
    requires javafx.fxml;

    opens Restaurante to javafx.fxml;
    exports Restaurante;
}