package  vending.state;

import vending.model.VendingMachine;

public class IdleState implements VendingState {
    private final VendingMachine machine;

    public IdleState(VendingMachine machine) {
        this.machine = machine;
    }

    @Override
    public void insertMoney(double amount) {
        machine.setBalance(machine.getBalance() + amount);
        System.out.println("Wrzucono: " + amount + " zł. Suma: " + machine.getBalance() + " zł.");
        machine.setState(new PaymentState(machine));
    }

    @Override
    public void selectProduct(int id) {
        System.out.println("Błąd: Najpierw wrzuć pieniądze!");
    }

    @Override
    public void refund() {
        System.out.println("Brak środków do zwrotu.");
    }
}
