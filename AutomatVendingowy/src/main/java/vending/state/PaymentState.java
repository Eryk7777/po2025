package vending.state;

import vending.model.*;

public class PaymentState implements VendingState {
    private static final long serialVersionUID = 1L;
    private final VendingMachine machine;

    public PaymentState(VendingMachine machine) {
        this.machine = machine;
    }

    @Override
    public void insertMoney(double amount) {
        machine.setBalance(machine.getBalance() + amount);
        System.out.println("Dodano: " + amount + " zł. Saldo: " + machine.getBalance() + " zł");
    }

    @Override
    public void selectProduct(int id) {
        InventoryManager inv = machine.getInventory();
        Product p = inv.getProduct(id);

        if (p == null) {
            System.out.println("Nie ma produktu o takim ID.");
            return;
        }

        if (!inv.hasItem(id)) {
            System.out.println("Produkt " + p.getName() + " wyprzedany!");
            return;
        }

        if (machine.getBalance() >= p.getPrice()) {
            double change = machine.getBalance() - p.getPrice();

            // WALIDACJA RESZTY - Dodany warunek
            if (!machine.getCashRegister().canGiveChange(change)) {
                System.out.println("Automat nie może wydać reszty (" + change + " zł). ");
                System.out.println("Wrzuć odliczoną kwotę lub wybierz inny produkt.");
                return;
            }

            // Realizacja zakupu
            inv.decrement(id);
            machine.getDispenser().releaseProduct(p.getName());
            machine.getCashRegister().processChange(change);

            // Powrót do stanu IDLE
            machine.setBalance(0.0);
            machine.setState(new IdleState(machine));
        } else {
            System.out.printf("Brakuje %.2f zł na %s\n", (p.getPrice() - machine.getBalance()), p.getName());
        }
    }

    @Override
    public void refund() {
        System.out.println("Zwrot środków: " + machine.getBalance() + " zł");
        machine.getCashRegister().processChange(machine.getBalance());
        machine.setBalance(0.0);
        machine.setState(new IdleState(machine));
    }
}