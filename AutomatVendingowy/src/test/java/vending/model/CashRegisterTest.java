package vending.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CashRegisterTest {
    private CashRegister cashRegister;

    @BeforeEach
    void setUp() {
        // Gwarancja pełnej kasy przed każdym testem
        cashRegister = new CashRegister();
    }

    @Test
    void testCanGiveSimpleChange() {
        assertTrue(cashRegister.canGiveChange(1.0), "Powinien wydać 1.00 zł");
        assertTrue(cashRegister.canGiveChange(5.0), "Powinien wydać 5.00 zł");
    }

    @Test
    void testCanGiveComplexChange() {
        // Testujemy 3.50
        assertTrue(cashRegister.canGiveChange(3.5), "Powinien wydać 3.50 zł");
    }

    @Test
    void testCannotGiveImpossibleChange() {
        // Algorytm musi zwrócić false, bo nie ma 5gr.
        assertFalse(cashRegister.canGiveChange(0.05), "Nie powinien wydać 0.05 zł");
    }

    @Test
    void testExactChangeLogic() {
        // Testowanie pętli while i TreeMap
        assertTrue(cashRegister.canGiveChange(2.2), "Powinien wydać 2.20 zł");
    }
}
