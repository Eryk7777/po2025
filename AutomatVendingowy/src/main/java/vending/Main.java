package vending;
import vending.factory.ProductFactory;
import vending.model.VendingMachine;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        VendingMachine machine = new VendingMachine();
        Scanner scanner = new Scanner(System.in);

        machine.getInventory().addProduct(ProductFactory.createSnack(1, "Cola", 4.5), 5);
        machine.getInventory().addProduct(ProductFactory.createSnack(2, "Baton", 2.2), 10);

        while (true) {
            System.out.println("\n[1] Wrzuć 1zł | [2] Wybierz produkt | [3] Zwrot | [0] Koniec");
            int cmd = scanner.nextInt();
            if (cmd == 1) machine.insertMoney(1.0);
            else if (cmd == 2) {
                System.out.print("ID: ");
                machine.selectProduct(scanner.nextInt());
            }
            else if (cmd == 3) machine.refund();
            else if (cmd == 0) break;
        }
    }
}