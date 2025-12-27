package vending.model;

import java.io.*;

public class PersistenceManager {
    private static final String FILE_NAME = "vending_state.dat";

    public static void saveMachineState(InventoryManager inventory) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            oos.writeObject(inventory);
            System.out.println("Zapisano stan magazynu.");
        } catch (IOException e) {
            System.out.println("Błąd: " + e.getMessage());
        }
    }
}