package vending.model;
import java.io.Serializable;
import java.util.Collections;
import java.util.Map;
import java.util.TreeMap;

public class CashRegister implements Serializable {
    private Map<Double, Integer> coins = new TreeMap<>(Collections.reverseOrder());

    public CashRegister() {
        coins.put(5.0, 10);
        coins.put(2.0, 10);
        coins.put(1.0, 10);
        coins.put(0.5, 10);
        coins.put(0.2, 10);
        coins.put(0.1, 10);
    }

    public void processChange(double amount) {
        double remaining = Math.round(amount * 100.0) / 100.0;
        if (remaining <= 0) return;

        System.out.println("[KASA] Wydawanie reszty: " + remaining + " zł");
        for (double coin : coins.keySet()) {
            while (remaining >= coin && coins.get(coin) > 0) {
                remaining = Math.round((remaining - coin) * 100.0) / 100.0;
                coins.put(coin, coins.get(coin) - 1);
                System.out.println("  > Wydano monetę: " + coin + " zł");
            }
        }
    }
}