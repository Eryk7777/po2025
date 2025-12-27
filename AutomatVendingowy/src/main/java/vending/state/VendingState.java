package vending.state;

public interface VendingState {
    void insertMoney(double amount);
    void selectProduct(int id);
    void refund();
}
