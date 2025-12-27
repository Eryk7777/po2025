package vending.model;

import vending.state.VendingState;
import vending.state.IdleState;

public class VendingMachine {
    private VendingState currentState;
    private double currentBalance = 0.0;
    private InventoryManager inventory =  new InventoryManager();
    private CashRegister cashRegister = new CashRegister();
    private Dispenser dispenser = new Dispenser();

    public VendingMachine() {
        // Początkowy stan
        this.currentState = new IdleState(this);
    }

    // Metody delegujące do stanu
    public void insertMoney(double amount) {
        currentState.insertMoney(amount);
    }

    public void addBalance(double amount) { this.currentBalance += amount; }
    public double getCurrentBalance() { return currentBalance; }
    public void resetBalance() { this.currentBalance = 0; }

    public void setInternalState(VendingState newState) {
        this.currentState = newState;
    }

    public InventoryManager getInventory() { return inventory; }
    public CashRegister getCashRegister() { return cashRegister; }
    public Dispenser getDispenser() { return dispenser; }
}

