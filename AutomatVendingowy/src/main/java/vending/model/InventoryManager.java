package vending.model;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class InventoryManager implements Serializable {
    private Map<Integer, Integer> stock = new HashMap<>(); // ID -> Ilość
    private Map<Integer, Product> products = new HashMap<>(); // ID -> Obiekt

    public void addProduct(Product p, int quantity) {
        products.put(p.getId(), p);
        stock.put(p.getId(), quantity);
    }

    public Product getProduct(int id) { return products.get(id); }
    public boolean isAvailable(int id) { return stock.getOrDefault(id, 0) > 0; }

    public void decrementStock(int id) {
        stock.put(id, stock.get(id) - 1);
    }
}