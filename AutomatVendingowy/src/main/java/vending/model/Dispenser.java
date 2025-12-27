package vending.model;

public class Dispenser {
    // Synchronized zapobiega próbie wydania dwóch produktów jednocześnie przez dwa wątki
    public synchronized void releaseProduct(String productName) {
        new Thread(() -> {
            try {
                System.out.println("Wydawanie: " + productName);
                Thread.sleep(3000); // Symulacja 3 sekund pracy silnika
                System.out.println("Odbierz: " + productName);
            } catch (InterruptedException e) {
                System.err.println("Błąd!");
            }
        }).start();
    }
}