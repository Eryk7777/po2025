package vending.model;

import java.util.HashMap;
import java.util.Map;

public class InventoryManager {
    // Mapa: Produkt -> Ilość sztuk
    private Map<Product, Integer> products = new HashMap<>();

    public void addProduct(Product product, int quantity) {
        products.put(product, products.getOrDefault(product, 0) + quantity);
    }

    public void removeOne(Product product) {
        if (products.containsKey(product) && products.get(product) > 0) {
            products.put(product, products.get(product) - 1);
        }
    }
    // Gettery i metody pomocnicze...
}
