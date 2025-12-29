package vending.model;

public class Dispenser {
    public void releaseProduct(String productName) {
        // Synchronized zapobiega konfliktom przy wielowątkowości
        synchronized (this) {
            Thread t = new Thread(() -> {
                try {
                    System.out.println("\n[MECHANIZM] Rozpoczęto wydawanie: " + productName);
                    Thread.sleep(2000); // Symulacja pracy
                    System.out.println("[MECHANIZM] Produkt " + productName + " wypadł do podajnika.");
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            });
            t.start();
        }
    }
}