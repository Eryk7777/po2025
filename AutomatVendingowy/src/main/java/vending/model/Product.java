package vending.model;
import java.io.Serializable;

public interface Product extends Serializable {
    String getName();
    double getPrice();
    int getId();
}
