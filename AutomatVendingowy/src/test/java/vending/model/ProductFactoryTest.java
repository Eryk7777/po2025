package vending.model;

import org.junit.jupiter.api.Test;
import vending.factory.ProductFactory;
import static org.junit.jupiter.api.Assertions.*;

public class ProductFactoryTest {

    @Test
    void testCreateSnack() {
        Product p = ProductFactory.createSnack(10, "Baton", 4.50);

        assertNotNull(p, "Obiekt nie powinien być nullem");
        assertEquals(10, p.getId());
        assertEquals("Baton", p.getName());
        assertEquals(4.50, p.getPrice());
        assertTrue(p instanceof Snack, "Produkt powinien być instancją klasy Snack");
    }
}