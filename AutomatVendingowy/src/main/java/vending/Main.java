package vending;

import vending.factory.ProductFactory;
import vending.model.InventoryManager;
import vending.model.PersistenceManager;
import vending.model.VendingMachine;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        VendingMachine machine = new VendingMachine();

        // 1. Próba wczytania zapisanego stanu
        InventoryManager loadedInventory = PersistenceManager.load();

        if (loadedInventory != null) {
            // Jeśli plik istniał, podmieniamy pusty magazyn na ten wczytany
            machine.setInventory(loadedInventory);
            System.out.println("Wczytano stan magazynu z pliku.");
        } else {
            // Jeśli pliku nie ma, dodajemy produkty ręcznie (tylko za pierwszym razem)
            System.out.println("Pierwsze uruchomienie - inicjalizacja magazynu.");
            machine.getInventory().addProduct(ProductFactory.createSnack(1, "Cola", 4.5), 5);
            machine.getInventory().addProduct(ProductFactory.createSnack(2, "Baton", 2.2), 10);
        }

        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\n[1] Wrzuć 1zł | [2] Wybierz | [3] Zwrot | [0] Zapisz i Wyjdź");
            int cmd = scanner.nextInt();

            if (cmd == 1) machine.insertMoney(1.0);
            else if (cmd == 2) {
                System.out.print("ID: ");
                machine.selectProduct(scanner.nextInt());
            }
            else if (cmd == 3) machine.refund();
            else if (cmd == 0) {
                // 2. Zapis przy wyjściu
                PersistenceManager.save(machine.getInventory());
                break;
            }
        }
    }
}