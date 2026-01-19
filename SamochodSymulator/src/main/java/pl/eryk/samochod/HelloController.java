package pl.eryk.samochod;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import pl.eryk.samochod.symulator.*;
import javafx.scene.image.ImageView;
import java.io.IOException;

public class HelloController {

    private Samochod mojSamochod;
    public AnchorPane mapPane;
    private ObservableList<Samochod> listaSamochodow = FXCollections.observableArrayList();


    public void odswiezWidok(){
        if (mojSamochod == null) {
            carmodelTextField.setText("");
            carregistrationTextField.setText("");
            carweightTextField.setText("");

            carspeedTextField.setText("");

            enginenameTextField.setText("");
            enginerpmTextField.setText("");
            engineweightTextField.setText("");
            enginepriceTextField.setText("");
            gearboxgearTextField.setText("");
            gearboxweightTextField.setText("");
            gearboxpriceTextField.setText("");
            gearboxnameTextField.setText("");
            clutchweightTextField.setText("");
            clutchpriceTextField.setText("");
            clutchnameTextField.setText("");
            clutchstateTextField.setText("");

            return;
        }
        //wypelnianie menu samochód
        carmodelTextField.setText(mojSamochod.getModel());
        carregistrationTextField.setText(mojSamochod.getNrRejestracyjny());
        carweightTextField.setText(String.valueOf(mojSamochod.getWaga()));
        carspeedTextField.setText(String.format("%.1f km/h", mojSamochod.getAktualnaPredkosc()));

        //wypelnianie menu silnik
        enginenameTextField.setText(mojSamochod.getSilnik().getNazwa());
        enginerpmTextField.setText(String.valueOf(mojSamochod.getSilnik().getObroty()));
        enginepriceTextField.setText(String.valueOf(mojSamochod.getSilnik().getCena()));
        engineweightTextField.setText(String.valueOf(mojSamochod.getSilnik().getWaga()));

        //wypelnianie menu skrzynia biegow
        gearboxgearTextField.setText(String.valueOf(mojSamochod.getSkrzynia().getAktualnyBieg()));
        gearboxnameTextField.setText(mojSamochod.getSkrzynia().getNazwa());
        gearboxpriceTextField.setText(String.valueOf(mojSamochod.getSkrzynia().getCena()));
        gearboxweightTextField.setText(String.valueOf(mojSamochod.getSkrzynia().getWaga()));

        //wypelnianie menu sprzeglo
        clutchnameTextField.setText(mojSamochod.getSkrzynia().getSprzeglo().getNazwa());
        clutchpriceTextField.setText(String.valueOf(mojSamochod.getSkrzynia().getSprzeglo().getCena()));
        clutchstateTextField.setText(String.valueOf(mojSamochod.getSkrzynia().getSprzeglo().getStanSprzegla()));
        clutchweightTextField.setText(String.valueOf(mojSamochod.getSkrzynia().getSprzeglo().getWaga()));

        boolean czyWlaczony = mojSamochod.getSilnik().getObroty() > 0;
        accelerateButton.setDisable(!czyWlaczony);
        deaccelerateButton.setDisable(!czyWlaczony);
        startButton.setDisable(czyWlaczony);
        stopButton.setDisable(!czyWlaczony);

        boolean czySprzeglo = mojSamochod.getSkrzynia().getSprzeglo().getStanSprzegla();

        accelerateButton.setDisable(!czyWlaczony || czySprzeglo);
        deaccelerateButton.setDisable(!czyWlaczony || czySprzeglo);
        shiftupButton.setDisable(!czyWlaczony || !czySprzeglo);
        shiftdownButton.setDisable(!czyWlaczony || !czySprzeglo);

    }

    // W HelloController.java:

    private void stworzIkonkeDlaAuta(Samochod auto) {
        // 1. Tworzenie ikonki (bez zmian)
        javafx.scene.image.Image obrazek = new javafx.scene.image.Image(getClass().getResourceAsStream("/car.png"));
        ImageView ikonka = new ImageView(obrazek);
        ikonka.setFitWidth(50);
        ikonka.setPreserveRatio(true);

        Label podpis = new Label(auto.getModel() + "\n" + auto.getNrRejestracyjny());
        podpis.setStyle("-fx-font-weight: bold; -fx-text-fill: black; -fx-background-color: rgba(255,255,255,0.7); -fx-padding: 2;");
        podpis.setTextAlignment(javafx.scene.text.TextAlignment.CENTER);

        VBox kontener = new VBox(ikonka, podpis);
        kontener.setAlignment(javafx.geometry.Pos.CENTER); // Wyśrodkowanie
        kontener.setLayoutX(0); // Pozycja startowa
        kontener.setLayoutY(0);


        auto.setIkonka(kontener);
        mapPane.getChildren().add(kontener);

        auto.addListener(new Listener() {
            @Override
            public void update() {
                Platform.runLater(() -> {
                    Pozycja p = auto.getPozycja();
                    // Przesuwamy cały VBox
                    kontener.setLayoutX(p.getX() - 25);
                    kontener.setLayoutY(p.getY() - 25);

                    // OBRÓT: Obracamy TYLKO obrazek, a napis zostaje poziomo!
                    // To jest super ficzer - napis się nie kręci do góry nogami.
                    Pozycja cel = auto.getCel();
                    double dx = cel.getX() - p.getX();
                    double dy = cel.getY() - p.getY();
                    if (Math.abs(dx) > 0.1 || Math.abs(dy) > 0.1) {
                        double angle = Math.toDegrees(Math.atan2(dy, dx));
                        ikonka.setRotate(angle); // Kręcimy tylko ImageView w środku
                    }

                    if (auto == mojSamochod) {
                        double predkosc = auto.getAktualnaPredkosc();
                        // Ustawiamy tekst w polu carspeedTextField (zrób formatowanie do 1 miejsca po przecinku)
                        carspeedTextField.setText(String.format("%.1f km/h", predkosc));
                    }
                });
            }
        });

        auto.start();
    }


    public void initialize(){
        carComboBox.setItems(listaSamochodow);

        //Samochod testowy = new Samochod("Ford", "LZ 12345");
        //listaSamochodow.add(testowy);
        //carComboBox.getSelectionModel().select(testowy);
        //onCarComboBoxButton();
        odswiezWidok();
    }


    public Button startButton;
    public void onStartButton(){
        if (mojSamochod != null){
            mojSamochod.wlacz();
            odswiezWidok();
        }
    }

    public Button stopButton;
    public void onStopButton(){
        if  (mojSamochod != null){
            mojSamochod.wylacz();
            odswiezWidok();
        }
    }
    public TextField carmodelTextField;
    public TextField carregistrationTextField;
    public TextField carweightTextField;
    public TextField carspeedTextField;
    public TextField gearboxnameTextField;
    public TextField gearboxpriceTextField;
    public TextField gearboxweightTextField;
    public TextField gearboxgearTextField;
    public Button shiftupButton;
    public void onShiftupButton(){
        if (mojSamochod != null){
            mojSamochod.zwiekszBieg();
            odswiezWidok();
        }
    }
    public Button shiftdownButton;
    public void onShiftdownButton(){
        if (mojSamochod != null){
            mojSamochod.zmniejszBieg();
            odswiezWidok();
        }
    }
    public TextField enginenameTextField;
    public TextField enginepriceTextField;
    public TextField engineweightTextField;
    public TextField enginerpmTextField;
    public Button accelerateButton;
    public void onAccelerateButton(){
        if (mojSamochod != null){
            mojSamochod.getSilnik().zwiekszObroty();
            odswiezWidok();
        }
    }
    public Button deaccelerateButton;
    public void onDeaccelerateButton(){
        if (mojSamochod != null){
            mojSamochod.getSilnik().zmniejszObroty();
            odswiezWidok();
        }
    }
    public TextField clutchnameTextField;
    public TextField clutchpriceTextField;
    public TextField clutchweightTextField;
    public TextField clutchstateTextField;
    public Button clutchengButton;
    public void  onClutchEngButton(){
        if (mojSamochod != null) {
            mojSamochod.getSkrzynia().getSprzeglo().setStanSprzegla(true);
            odswiezWidok();
        }
    }
    public Button clutchdngButton;
    public void onClutchDngButton(){
        if (mojSamochod != null) {
            mojSamochod.getSkrzynia().getSprzeglo().setStanSprzegla(false);
            odswiezWidok();
        }
    }
    public ComboBox carComboBox;
    public void onCarComboBoxButton(){
        Samochod wybrane = (Samochod) carComboBox.getSelectionModel().getSelectedItem();
        if (wybrane != null){
            mojSamochod = wybrane;
            odswiezWidok();
        }
        //odswiezWidok();
    }
    public Button addcarButton;
    public void onAddCarButton(){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/add-car-view.fxml"));
            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setTitle("Dodawanie pojazdu");
            stage.setScene(new Scene(root));

            stage.initModality(Modality.APPLICATION_MODAL); //blokuje glowne gui
            stage.showAndWait();

            AddCarController controller = loader.getController();
            Samochod auto = controller.getNoweAuto();

            if (auto != null){
                stworzIkonkeDlaAuta(auto);
                listaSamochodow.add(auto);
                carComboBox.getSelectionModel().select(auto);
                onCarComboBoxButton();
                System.out.println("Dodano: " + auto.getModel());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public Button rmcarButton;
    public void onRmCarButton() {
        if (mojSamochod != null) {
            // 1. Sprzątanie po usuwanym aucie
            // Zatrzymujemy wątek (ważne, żeby nie aktualizował GUI po śmierci)
            mojSamochod.interrupt(); // Lub ustaw flagę isRunning = false jeśli masz setter

            // Usuwamy z mapy (jeśli ma ikonkę)
            if (mojSamochod.getIkonka() != null) {
                mapPane.getChildren().remove(mojSamochod.getIkonka());
            }

            // Usuwamy z listy danych
            listaSamochodow.remove(mojSamochod);

            // 2. Wybór nowego auta (Automatyczny)
            if (!listaSamochodow.isEmpty()) {
                // Wybieramy pierwsze z listy (index 0)
                Samochod noweWybrane = listaSamochodow.get(0);

                // Ustawiamy w ComboBoxie i zmiennej
                carComboBox.getSelectionModel().select(noweWybrane);
                mojSamochod = noweWybrane;

                System.out.println("Usunięto. Wybrano automatycznie: " + mojSamochod.getModel());
            } else {
                // Lista pusta - czyścimy
                mojSamochod = null;
                carComboBox.getSelectionModel().clearSelection();
                System.out.println("Usunięto ostatnie auto.");
            }

            // 3. Odświeżamy GUI
            odswiezWidok();
        }
    }


    public void onMapClicked(javafx.scene.input.MouseEvent event){
        if (mojSamochod != null){
            double x =  event.getX();
            double y = event.getY();
            System.out.println("x: " + x + " y: " + y);
            mojSamochod.jedzDo(x, y);
        }
    }

}