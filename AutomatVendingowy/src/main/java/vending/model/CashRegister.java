package vending.model;

import java.util.*;

public class CashRegister {
    // Mapa: Nominał -> Ilość monet w maszynie (posortowana malejąco)
    private Map<Double, Integer> coins = new TreeMap<>(Collections.reverseOrder());

    public CashRegister() {
        // Inicjalizacja automatu monetami
        coins.put(5.0, 10);
        coins.put(2.0, 20);
        coins.put(1.0, 20);
        coins.put(0.50, 50);
    }

    public void giveChange(double amount) {
        System.out.println("Wydaję resztę: " + amount);
        double remaining = amount;

        for (double denomination : coins.keySet()) {
            while (remaining >= denomination && coins.get(denomination) > 0) {
                remaining -= denomination;
                remaining = Math.round(remaining * 100.0) / 100.0; // Zaokrąglenie błędów double
                coins.put(denomination, coins.get(denomination) - 1);
                System.out.println("Wydano monetę: " + denomination);
            }
        }

        if (remaining > 0) {
            System.out.println("Błąd: Brak drobnych do wydania pełnej reszty! Zostało: " + remaining);
        }
    }
}