package vending;

import vending.factory.ProductFactory;
import vending.model.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        VendingMachine machine = new VendingMachine();

        InventoryManager loaded = PersistenceManager.load();
        if (loaded != null) {
            machine.setInventory(loaded);
            System.out.println("Stan wczytany z pliku.");
        } else {
            initDefaultInventory(machine);
        }

        printProductList(machine);

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n------------------------");
            System.out.println("AKTUALNE SALDO: " + machine.getBalance() + " zł");
            System.out.println("------------------------");
            System.out.println("Opcje: [1-3] Wrzuć monetę (1zł/2zł/5zł) | [4] Kup | [5] Zwrot | [L] Lista | [R] Uzupełnij (Serwis) | [0] Wyjdź");
            System.out.print("Wybór: ");

            String input = scanner.next().toUpperCase();

            switch (input) {
                case "1" -> machine.insertMoney(1.0);
                case "2" -> machine.insertMoney(2.0);
                case "3" -> machine.insertMoney(5.0);
                case "4" -> {
                    System.out.print("Podaj ID produktu: ");
                    if (scanner.hasNextInt()) {
                        machine.selectProduct(scanner.nextInt());
                    } else {
                        System.out.println("ID musi być liczbą!");
                        scanner.next();
                    }
                }
                case "5" -> machine.refund();
                case "L" -> printProductList(machine);
                case "R" -> {
                    // Autoryzacja kodem admina
                    System.out.print("Podaj kod ADMINA: ");
                    String code = scanner.next();

                    if (code.equals("1453")) {
                        machine.getInventory().restockAll();
                        System.out.println("Autoryzacja przebiegła pomyślnie. Wszystkie produkty zostały uzupełnione do 5 sztuk.");
                        printProductList(machine);
                    } else {
                        System.out.println("Błędny kod. Brak uprawnień do uzupełniania zapasów.");
                    }
                }
                case "0" -> {
                    PersistenceManager.save(machine.getInventory());
                    System.out.println("Zamykanie...");
                    System.exit(0);
                }
                default -> System.out.println("Nieznana opcja.");
            }
        }
    }

    private static void printProductList(VendingMachine machine) {
        System.out.println("\n=============== DOSTĘPNE PRODUKTY ================");
        machine.getInventory().getAllProducts().forEach((id, p) -> {
            int qty = machine.getInventory().getQuantity(id);
            String status = (qty > 0) ? qty + " szt." : "BRAK";
            System.out.printf("[%d] %-15s | Cena: %.2f zł | Stan: %s\n", id, p.getName(), p.getPrice(), status);
        });
        System.out.println("==================================================");
    }

    private static void initDefaultInventory(VendingMachine machine) {
        InventoryManager inv = machine.getInventory();
        inv.addProduct(ProductFactory.createSnack(1, "Sprunk", 3.2), 5);
        inv.addProduct(ProductFactory.createSnack(2, "Cola", 3.5), 5);
        inv.addProduct(ProductFactory.createSnack(3, "7-Nights", 6.0), 5);
        inv.addProduct(ProductFactory.createSnack(4, "Kit-Dog", 3.0), 5);
        inv.addProduct(ProductFactory.createSnack(5, "Andromeda", 2.5), 5);
        inv.addProduct(ProductFactory.createSnack(6, "White", 4.0), 5);
        inv.addProduct(ProductFactory.createSnack(7, "Down Weak", 8.0), 5);
        inv.addProduct(ProductFactory.createSnack(8, "Ocean Dew", 3.2), 5);
        inv.addProduct(ProductFactory.createSnack(9, "Adult Bueno", 5.0), 5);
        System.out.println("Zainicjalizowano domyślne produkty (5 sztuk).");
    }
}