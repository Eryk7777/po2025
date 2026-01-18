package vending.model;

import java.io.Serializable;
import java.util.Collections;
import java.util.Map;
import java.util.TreeMap;

public class CashRegister implements Serializable {
    private static final long serialVersionUID = 1L;
    private final Map<Double, Integer> coins = new TreeMap<>(Collections.reverseOrder());

    public CashRegister() {
        // Stan początkowy kasy
        coins.put(5.0, 10);
        coins.put(2.0, 10);
        coins.put(1.0, 10);
        coins.put(0.5, 10);
        coins.put(0.2, 10);
        coins.put(0.1, 10);
    }

    /**
     * Dodaje monetę do kasy (powinno być wywoływane przy insertMoney w VendingMachine)
     */
    public void addCoin(double coin) {
        // Zaokrąglamy nominał, aby uniknąć błędów klucza w mapie
        double roundedCoin = Math.round(coin * 100.0) / 100.0;
        coins.put(roundedCoin, coins.getOrDefault(roundedCoin, 0) + 1);
    }

    /**
     * Sprawdza, czy automat posiada odpowiednie monety.
     */
    public boolean canGiveChange(double amount) {
        double remaining = Math.round(amount * 100.0) / 100.0;
        if (remaining <= 0) return true;

        Map<Double, Integer> tempCoins = new TreeMap<>(Collections.reverseOrder());
        tempCoins.putAll(coins);

        for (double coin : tempCoins.keySet()) {
            while (remaining >= (coin - 0.001) && tempCoins.get(coin) > 0) {
                remaining = Math.round((remaining - coin) * 100.0) / 100.0;
                tempCoins.put(coin, tempCoins.get(coin) - 1);
            }
        }

        // Zmiana: sprawdzamy czy zostało mniej niż grosz, a nie idealne zero
        return remaining < 0.01;
    }

    /**
     * Faktycznie wydaje monety.
     */
    public void processChange(double amount) {
        double remaining = Math.round(amount * 100.0) / 100.0;
        if (remaining <= 0) return;

        System.out.println("Wydawanie reszty: " + remaining + " zł");
        for (double coin : coins.keySet()) {
            while (remaining >= (coin - 0.001) && coins.get(coin) > 0) {
                remaining = Math.round((remaining - coin) * 100.0) / 100.0;
                coins.put(coin, coins.get(coin) - 1);
                System.out.println("  > Wydano monetę: " + coin + " zł");
            }
        }
    }
}