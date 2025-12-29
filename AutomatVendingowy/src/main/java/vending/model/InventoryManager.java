package vending.model;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class InventoryManager implements Serializable {
    private final Map<Integer, Product> products = new HashMap<>();
    private final Map<Integer, Integer> quantities = new HashMap<>();

    public void addProduct(Product p, int qty) {
        products.put(p.getId(), p);
        quantities.put(p.getId(), qty);
    }

    // NOWA METODA: Uzupełnianie wszystkich produktów do 5 sztuk
    public void restockAll() {
        for (Integer id : products.keySet()) {
            quantities.put(id, 5);
        }
    }

    public boolean hasItem(int id) {
        return quantities.getOrDefault(id, 0) > 0;
    }

    public void decrement(int id) {
        if (hasItem(id)) {
            quantities.put(id, quantities.get(id) - 1);
        }
    }

    public Product getProduct(int id) {
        return products.get(id);
    }

    public Map<Integer, Product> getAllProducts() {
        return products;
    }

    public int getQuantity(int id) {
        return quantities.getOrDefault(id, 0);
    }
}
