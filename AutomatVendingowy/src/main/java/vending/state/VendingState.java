package vending.state;

import java.io.Serializable;

   // Interfejs stanu automatu.
   // Implementacja Serializable, aby stan maszyny mógł być teoretycznie zapisany.

public interface VendingState extends Serializable {
    void insertMoney(double amount);
    void selectProduct(int id);
    void refund();
}