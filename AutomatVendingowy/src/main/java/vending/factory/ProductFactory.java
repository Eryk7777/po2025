package vending.factory;
import vending.model.*;

public class ProductFactory {
    public static Product createProduct(int id, String name, double price) {
        return new Snack(id, name, price);
    }
}
