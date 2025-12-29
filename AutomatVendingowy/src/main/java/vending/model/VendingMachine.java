package vending.model;
import vending.state.*;

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

    public void setState(VendingState state) { this.currentState = state; }
    public void setInventory(InventoryManager inventory) { this.inventory = inventory; }
    public InventoryManager getInventory() { return inventory; }
    public double getBalance() { return balance; }
    public void setBalance(double b) { this.balance = Math.round(b * 100.0) / 100.0; }
    public CashRegister getCashRegister() { return cashRegister; }
    public Dispenser getDispenser() { return dispenser; }
}