package symulator;

public class Sprzęgło extends Komponent{
    private boolean stanSprzęgła;

    public Sprzęgło(String nazwa, double waga, double cena) {
        super(nazwa, waga, cena);
        this.stanSprzęgła = false;
    }

    public void wcisnij() {
        stanSprzęgła = true;
    }

    public void zwolnij() {
        stanSprzęgła = false;
    }
}
