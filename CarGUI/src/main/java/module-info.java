module com.eryk.cargui {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.eryk.cargui to javafx.fxml;
    exports com.eryk.cargui;
}