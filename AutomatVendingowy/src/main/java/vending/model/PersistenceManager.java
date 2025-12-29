package vending.model;

import java.io.*;

public class PersistenceManager {
    private static final String FILE_NAME = "vending_storage.dat";

    /**

    Zapisuje obiekt InventoryManager do pliku binarnego.*/
    public static void save(InventoryManager inventory) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            oos.writeObject(inventory);
            System.out.println("[SYSTEM] Stan magazynu został pomyślnie zapisany do pliku.");} catch (IOException e) {
            System.err.println("[BŁĄD ZAPISU] Nie udało się zapisać stanu: " + e.getMessage());}}

    /**

    Wczytuje obiekt InventoryManager z pliku.
    @return Zwraca wczytany obiekt lub null, jeśli plik nie istnieje.*/
    public static InventoryManager load() {
        File file = new File(FILE_NAME);
        if (!file.exists()) {
            System.out.println("[SYSTEM] Brak pliku zapisu. Inicjalizacja domyślna.");
            return null;}

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            Object loaded = ois.readObject();
            if (loaded instanceof InventoryManager) {
                return (InventoryManager) loaded;
            }
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("[BŁĄD ODCZYTU] Plik uszkodzony lub niekompatybilny: " + e.getMessage());
        }
        return null;
    }
}