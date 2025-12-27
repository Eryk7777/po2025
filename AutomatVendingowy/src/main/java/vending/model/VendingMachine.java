package vending.model;
import vending.state.*;
import java.util.HashMap;
import java.util.Map;

public class VendingMachine {
    private VendingState currentState;
    private double balance = 0.0;
    private final Map<Integer, Product> inventory = new HashMap<>();

    public VendingMachine() {
        this.currentState = new IdleState(this);
    }

    public void addProduct(Product p) {
        inventory.put(p.getId(), p);
    }

    // Gettery i Settery
    public void setState(VendingState state) { this.currentState = state; }
    public double getBalance() { return balance; }
    public void setBalance(double balance) { this.balance = balance; }
    public Map<Integer, Product> getInventory() { return inventory; }

    // Delegacja do stanu
    public void insertMoney(double amount) { currentState.insertMoney(amount); }
    public void selectProduct(int id) { currentState.selectProduct(id); }
    public void refund() { currentState.refund(); }
}
