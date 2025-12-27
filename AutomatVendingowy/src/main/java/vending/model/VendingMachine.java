package vending.model;
import vending.state.*;

public class VendingMachine {
    private VendingState currentState;
    private double balance;
    private InventoryManager inventory;
    private Dispenser dispenser;

    public VendingMachine() {
        this.inventory = new InventoryManager();
        this.dispenser = new Dispenser();
        this.balance = 0;
        // Początkowy stan (musisz go zaimplementować, np. IdleState)
    }

    // Gettery i Settery
    public void setState(VendingState state) { this.currentState = state; }
    public void addBalance(double amount) { this.balance += amount; }
    public double getBalance() { return balance; }
    public void setBalance(double balance) { this.balance = balance; }
    public InventoryManager getInventory() { return inventory; }
    public Dispenser getDispenser() { return dispenser; }

    // Metody delegujące
    public void pressButton(int id) { currentState.selectProduct(id); }
    public void insertCoin(double amount) { currentState.insertMoney(amount); }
}