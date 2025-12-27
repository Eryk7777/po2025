package vending.model;
import vending.state.*;
import java.util.HashMap;
import java.util.Map;

public class VendingMachine {
    private VendingState currentState;
    private double balance = 0.0;
    private InventoryManager inventory = new InventoryManager();
    private final CashRegister cashRegister = new CashRegister();
    private final Dispenser dispenser = new Dispenser();

    public VendingMachine() {
        this.currentState = new IdleState(this);
    }

    public void insertMoney(double amount) { currentState.insertMoney(amount); }
    public void selectProduct(int id) { currentState.selectProduct(id); }
    public void refund() { currentState.refund(); }

    public void setInventory(InventoryManager inventory) {
        this.inventory = inventory;
    }
    public void setState(VendingState state) { this.currentState = state; }
    public double getBalance() { return balance; }
    public void setBalance(double balance) { this.balance = balance; }
    public InventoryManager getInventory() { return inventory; }
    public CashRegister getCashRegister() { return cashRegister; }
    public Dispenser getDispenser() { return dispenser; }

}