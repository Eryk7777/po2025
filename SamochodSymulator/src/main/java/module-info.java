module pl.eryk.samochod {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens pl.eryk.samochod to javafx.fxml;
    exports pl.eryk.samochod;
}