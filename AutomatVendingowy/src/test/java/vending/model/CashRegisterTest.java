package vending.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CashRegisterTest {
    private CashRegister cashRegister;

    @BeforeEach
    void setUp() {
        // Gwarantujemy, że kasa jest pełna przed każdym testem
        cashRegister = new CashRegister();
    }

    @Test
    void testCanGiveSimpleChange() {
        assertTrue(cashRegister.canGiveChange(1.0), "Powinien wydać 1.00 zł");
        assertTrue(cashRegister.canGiveChange(5.0), "Powinien wydać 5.00 zł");
    }

    @Test
    void testCanGiveComplexChange() {
        // Testujemy 3.50 (2.0 + 1.0 + 0.5) - to są wartości,
        // które w systemie binarnym mają dobrą reprezentację.
        assertTrue(cashRegister.canGiveChange(3.5), "Powinien wydać 3.50 zł");
    }

    @Test
    void testCannotGiveImpossibleChange() {
        // Masz monety 0.1, 0.2, 0.5 itd. Nie masz 0.05 (5 groszy).
        // Algorytm musi zwrócić false.
        assertFalse(cashRegister.canGiveChange(0.05), "Nie powinien wydać 0.05 zł");
    }

    @Test
    void testExactChangeLogic() {
        // To przetestuje, czy pętla while i TreeMap działają poprawnie.
        assertTrue(cashRegister.canGiveChange(2.2), "Powinien wydać 2.20 zł");
    }
}
