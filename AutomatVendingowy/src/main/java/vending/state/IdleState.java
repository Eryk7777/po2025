package vending.state;

import vending.model.VendingMachine;

public class IdleState implements VendingState {
    private VendingMachine machine;

    public IdleState(VendingMachine machine) {
        this.machine = machine;
    }

    @Override
    public void insertMoney(double amount) {
        System.out.println("Wrzucono: " + amount);
        machine.addBalance(amount);
        // Zmiana stanu na PaymentState (trzeba go stworzyć)
        // machine.setState(machine.getPaymentState());
    }

    @Override
    public void selectProduct(int id) {
        System.out.println("Najpierw wrzuć monety!");
    }

    @Override
    public void dispenseProduct() {
        System.out.println("Nic nie wybrano.");
    }

    @Override
    public void refund() {
        System.out.println("Brak środków do zwrotu.");
    }
}