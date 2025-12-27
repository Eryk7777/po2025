package vending.factory;
import vending.model.*;

public class ProductFactory {
    public static Product createSnack(int id, String name, double price) {
        return new Snack(id, name, price);
    }
}