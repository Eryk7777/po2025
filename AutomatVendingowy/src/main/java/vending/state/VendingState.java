package vending.state;

// Interfejs bazowy dla wszystkich stanów
public interface VendingState {
    void selectProduct(int id);
    void insertMoney(double amount);
    void dispenseProduct();
    void refund();
}
