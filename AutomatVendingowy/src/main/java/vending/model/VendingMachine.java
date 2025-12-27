package vending.model;

import vending.state.VendingState;

public class VendingMachine {
    private VendingState currentState;
    private double currentBalance;
    private InventoryManager inventory;

    // Stany automatu
    private VendingState idleState;
    private VendingState processingPaymentState;

    public VendingMachine() {
        this.inventory = new InventoryManager();
        // Tutaj w przyszłości zainicjalizujesz konkretne stany
    }

    public void setState(VendingState state) {
        this.currentState = state;
    }

    // Delegowanie metod do aktualnego stanu
    public void pressButton(int id) { currentState.selectProduct(id); }
    public void insertCoin(double amount) { currentState.insertMoney(amount); }

    // Gettery i settery dla balansu...
}
