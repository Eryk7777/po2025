package vending.state;

import vending.model.*;

public class PaymentState implements VendingState {
    private final VendingMachine machine;

    public PaymentState(VendingMachine machine) {
        this.machine = machine;
    }

    @Override
    public void insertMoney(double amount) {
        machine.setBalance(machine.getBalance() + amount);
        System.out.println("Dodano: " + amount + " zł. Suma: " + machine.getBalance() + " zł.");
    }

    @Override
    public void selectProduct(int id) {
        Product p = machine.getInventory().getProduct(id);
        if (p == null) {
            System.out.println("Produkt z takim ID nie istnieje.");
            return;
        }

        if (machine.getBalance() >= p.getPrice()) {
            System.out.println("Kupiono: " + p.getName());
            double change = machine.getBalance() - p.getPrice();
            if (change > 0) {
                System.out.println("Wydaję resztę: " + Math.round(change * 100.0) / 100.0 + " zł.");
            }
            machine.setBalance(0);
            // Tutaj można dodać wątek wydawania produktu (Krok 5)
            machine.setState(new IdleState(machine));
        } else {
            System.out.println("Za mało pieniążków! Brakuje: " + (p.getPrice() - machine.getBalance()) + " zł.");
        }
    }

    @Override
    public void refund() {
        System.out.println("Zwracam: " + machine.getBalance() + " zł.");
        machine.setBalance(0);
        machine.setState(new IdleState(machine));
    }
}