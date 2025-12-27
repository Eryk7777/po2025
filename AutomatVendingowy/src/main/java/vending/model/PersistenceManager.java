package vending.model;

import java.io.*;

public class PersistenceManager {
    private static final String fileName = "inventory.dat";

    public static void save(InventoryManager inventory) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))) {
            oos.writeObject(inventory);
            System.out.println("Stan magazynu zapisany pomyślnie.");
        } catch (IOException e) {
            System.err.println("Błąd zapisu: " + e.getMessage());
        }
    }

    public static InventoryManager load() {
        File file = new File(fileName);
        if (!file.exists()) return null;

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName))) {
            return (InventoryManager) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Błąd odczytu: " + e.getMessage());
            return null;
        }
    }
}