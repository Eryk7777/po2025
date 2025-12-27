package vending.model;
import java.io.*;

public class PersistenceManager {
    public static void save(InventoryManager im, String filename) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
            oos.writeObject(im);
        }
    }
}