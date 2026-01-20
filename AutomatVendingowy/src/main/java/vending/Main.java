package vending;

import vending.factory.ProductFactory;
import vending.model.*;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        VendingMachine machine = new VendingMachine();

        InventoryManager loaded = PersistenceManager.load();
        if (loaded != null) {
            machine.setInventory(loaded);
            System.out.println("[SYSTEM] Stan wczytany z pliku.");
        } else {
            initDefaultInventory(machine);
        }

        System.out.println("\n--------------------------------------------------------------------");
        System.out.println("Witamy w Automacie Vendingowym! (Twórcy: Eryk Knap & Michał Sałapat) \nAby kupić produkt, wrzuć monety a następnie wybierz pozycję.");
        System.out.println("--------------------------------------------------------------------\n");

        printProductList(machine);

        Scanner scanner = new Scanner(System.in);

        while (true) {
            try { Thread.sleep(100); } catch (InterruptedException e) {}

            System.out.println("\n----------------------");
            System.out.println("AKTUALNE SALDO: " + machine.getBalance() + " zł");
            System.out.println("----------------------");

            System.out.println("Opcje: [1-4] Wrzuć monetę (1zł/2zł/5zł/0,5zł) || [5] Kup || [6] Zwrot || [L] Lista || [R] Serwis || [0] Wyjdź");
            System.out.print("Wybór: ");

            String input = scanner.next().toUpperCase();

            switch (input) {
                case "1" -> machine.insertMoney(1.0);
                case "2" -> machine.insertMoney(2.0);
                case "3" -> machine.insertMoney(5.0);
                case "4" -> machine.insertMoney(0.5);
                case "5" -> {
                    System.out.print("Podaj ID produktu: ");
                    if (scanner.hasNextInt()) {
                        machine.selectProduct(scanner.nextInt());
                        try { Thread.sleep(2200); } catch (InterruptedException e) {}
                    } else {
                        System.out.println("ID musi być liczbą!");
                        scanner.next();
                    }
                }
                case "6" -> machine.refund();
                case "L" -> printProductList(machine);
                case "R" -> {
                    System.out.print("Podaj kod Admina: ");
                    String code = scanner.next();
                    if (code.equals("1453")) {
                        // 1. Uzupełnienie produktów
                        machine.getInventory().restockAll();

                        // 2. Uzupełnienie monet do 10 sztuk
                        machine.getCashRegister().resetCoins();

                        System.out.println("SERWIS: Produkty uzupełnione do 5 sztuk.");
                        System.out.println("SERWIS: Stan monet zresetowany do 10 sztuk każdego nominału.");
                        printProductList(machine);
                    } else {
                        System.out.println("Błędny kod Admina!");
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
        String frameLine = "============================================================================================";
        String separatorLine = "|| -------------------------- || -------------------------- || -------------------------- ||";

        System.out.println("==================================== AUTOMAT VENDINGOWY ====================================");

        var allProducts = machine.getInventory().getAllProducts();
        List<Integer> ids = new ArrayList<>(allProducts.keySet());

        for (int i = 0; i < ids.size(); i += 3) {
            System.out.print("|| ");
            for (int j = 0; j < 3; j++) {
                if (i + j < ids.size()) {
                    int id = ids.get(i + j);
                    Product p = allProducts.get(id);
                    String info = String.format("[%d] %s - %.2fzł", id, p.getName(), p.getPrice());
                    System.out.printf("%-26s", info);
                    if (j < 2) System.out.print(" || ");
                }
            }
            System.out.println(" ||");

            System.out.print("|| ");
            for (int j = 0; j < 3; j++) {
                if (i + j < ids.size()) {
                    int id = ids.get(i + j);
                    int qty = machine.getInventory().getQuantity(id);
                    String status = (qty > 0) ? qty + " szt." : "BRAK";
                    String stock = "(Zostało " + status + ")";
                    System.out.printf("%-26s", stock);
                    if (j < 2) System.out.print(" || ");
                }
            }
            System.out.println(" ||");

            if (i + 3 < ids.size()) {
                System.out.println(separatorLine);
            }
        }
        System.out.println(frameLine);
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
    }
}