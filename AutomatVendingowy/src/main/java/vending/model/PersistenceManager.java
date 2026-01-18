package vending.model;

import java.io.*;

public class PersistenceManager {
    private static final String FILE_NAME = "vending_storage.dat";

    // Zapis obiektu InventoryManager do pliku binarnego.
    public static void save(InventoryManager inventory) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            oos.writeObject(inventory);
            System.out.println("Stan magazynu został pomyślnie zapisany do pliku.");} catch (IOException e) {
            System.err.println("Nie udało się zapisać stanu: " + e.getMessage());}}

    //Wczytanie obiektu InventoryManager z pliku.
    //@return Zwraca wczytany obiekt lub null, jeśli plik nie istnieje.

    public static InventoryManager load() {
        File file = new File(FILE_NAME);
        if (!file.exists()) {
            System.out.println("Brak pliku zapisu. Inicjalizacja domyślna.");
            return null;}

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            Object loaded = ois.readObject();
            if (loaded instanceof InventoryManager) {
                return (InventoryManager) loaded;
            }
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Plik uszkodzony lub niekompatybilny: " + e.getMessage());
        }
        return null;
    }
}