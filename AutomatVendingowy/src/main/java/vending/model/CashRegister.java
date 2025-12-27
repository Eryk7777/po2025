package vending.model;

import java.io.Serializable;
import java.util.Collections;
import java.util.Map;
import java.util.TreeMap;

public class CashRegister implements Serializable {
    // Mapa: Nominał -> Ilość sztuk w maszynie
    private Map<Double, Integer> coins = new TreeMap<>(Collections.reverseOrder());

    public CashRegister() {
        // Początkowy zapas monet
        coins.put(5.0, 10);
        coins.put(2.0, 10);
        coins.put(1.0, 10);
        coins.put(0.5, 10);
    }

    public void processChange(double amount) {
        double remaining = Math.round(amount * 100.0) / 100.0;
        System.out.println("Wydawanie reszty.");

        for (double coin : coins.keySet()) {
            while (remaining >= coin && coins.get(coin) > 0) {
                remaining -= coin;
                remaining = Math.round(remaining * 100.0) / 100.0;
                coins.put(coin, coins.get(coin) - 1);
                System.out.println("Wydano: " + coin + " zł.");
            }
        }
    }
}