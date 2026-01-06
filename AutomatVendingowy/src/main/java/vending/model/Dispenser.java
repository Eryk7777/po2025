package vending.model;

public class Dispenser {
    public void releaseProduct(String productName) {
        // Synchronized zapobiega konfliktom przy wielowątkowości
        synchronized (this) {
            Thread t = new Thread(() -> {
                try {
                    System.out.println("\nRozpoczęto wydawanie: " + productName);
                    Thread.sleep(2000); // Symulacja pracy
                    System.out.println("Produkt " + productName + " wypadł do podajnika.");
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            });
            t.start();
        }
    }
}