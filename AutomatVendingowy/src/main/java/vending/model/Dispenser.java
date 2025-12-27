package vending.model;

public class Dispenser {
    public synchronized void releaseProduct(String productName) {
        new Thread(() -> {
            try {
                System.out.println("[MECHANIZM] Praca... (wątek start)");
                Thread.sleep(2000);
                System.out.println("[MECHANIZM] Produkt " + productName + " został wydany.");
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }).start();
    }
}