package vending.model;

public class Dispenser {
    // Synchronizacja zapewnia, że tylko jeden wątek na raz korzysta z tej metody
    public synchronized void releaseProduct(String productName) {
        new Thread(() -> {
            try {
                System.out.println("Przygotowuję: " + productName);
                Thread.sleep(3000); // Symulacja pracy silniczka (3 sekundy)
                System.out.println("Produkt: " + productName + ".");
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }).start();
    }
}