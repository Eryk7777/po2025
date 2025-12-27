package vending.model;

public class Dispenser {
    public synchronized void dispense(String productName) {
        new Thread(() -> {
            try {
                System.out.println("[WĄTEK] Mechanizm uruchomiony...");
                Thread.sleep(2000);
                System.out.println("[WĄTEK] Wydano: " + productName);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }
}