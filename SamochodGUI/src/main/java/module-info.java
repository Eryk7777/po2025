module com.example.samochodgui {
    requires javafx.controls;
    requires javafx.fxml;
    exports com.example.samochodgui;


    opens com.example.samochodgui to javafx.fxml;

}