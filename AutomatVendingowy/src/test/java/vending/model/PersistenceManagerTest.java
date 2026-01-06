package vending.model;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import vending.factory.ProductFactory;
import java.io.File;
import java.nio.file.Path;
import static org.junit.jupiter.api.Assertions.*;

public class PersistenceManagerTest {

    @Test
    void testSaveAndLoad() {
        InventoryManager inv = new InventoryManager();
        inv.addProduct(ProductFactory.createSnack(1, "ZapisanyProdukt", 5.0), 10);

        // Zapisujemy
        PersistenceManager.save(inv);

        // Wczytujemy
        InventoryManager loaded = PersistenceManager.load();

        assertNotNull(loaded, "Wczytany manager nie powinien być nullem");
        assertEquals(10, loaded.getQuantity(1), "Ilość produktu powinna być zachowana po wczytaniu");
        assertEquals("ZapisanyProdukt", loaded.getProduct(1).getName());
    }
}