package vending.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import vending.factory.ProductFactory;
import static org.junit.jupiter.api.Assertions.*;

public class InventoryManagerTest {

    private InventoryManager inventory;

    @BeforeEach
    void setUp() {
        // Przygotowujemy czysty manager przed każdym testem
        inventory = new InventoryManager();

        // Dodajemy produkt testowy: ID 1, 5 sztuk
        Product p = ProductFactory.createSnack(1, "Baton Testowy", 2.50);
        inventory.addProduct(p, 5);
    }

    @Test
    void testInitialQuantity() {
        // Sprawdzamy czy na start jest 5 sztuk
        assertEquals(5, inventory.getQuantity(1), "Początkowa ilość powinna wynosić 5.");
    }

    @Test
    void testDecrementProduct() {
        // Symulujemy zakup
        inventory.decrement(1);
        assertEquals(4, inventory.getQuantity(1), "Ilość po zakupie powinna spaść do 4.");
    }

    @Test
    void testHasItemAvailability() {
        // Sprawdzamy dostępność
        assertTrue(inventory.hasItem(1), "Produkt powinien być dostępny.");

        // Zmniejszamy do zera
        for (int i = 0; i < 5; i++) {
            inventory.decrement(1);
        }

        assertFalse(inventory.hasItem(1), "Produkt powinien być niedostępny przy stanie 0.");
    }

    @Test
    void testRestockAll() {
        // Zmniejszamy stan
        inventory.decrement(1);
        inventory.decrement(1);
        assertEquals(3, inventory.getQuantity(1));

        // Uruchamiamy funkcję serwisową
        inventory.restockAll();

        assertEquals(5, inventory.getQuantity(1), "Po restocku stan powinien wrócić do 5.");
    }

    @Test
    void testGetProduct() {
        Product p = inventory.getProduct(1);
        assertNotNull(p);
        assertEquals("Baton Testowy", p.getName());
    }
}