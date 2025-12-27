package vending.model;

public class Snack implements Product {
    private final int id;
    private final String name;
    private final double price;

    public Snack(int id, String name, double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    @Override public String getName() { return name; }
    @Override public double getPrice() { return price; }
    @Override public int getId() { return id; }
}
