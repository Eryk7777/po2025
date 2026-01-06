package vending.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class VendingMachineTest {
    private VendingMachine machine;

    @BeforeEach
    void setUp() {
        machine = new VendingMachine(); // Automat domyślnie startuje w IdleState
    }

    @Test
    void testInitialBalance() {
        assertEquals(0.0, machine.getBalance(), "Początkowe saldo powinno wynosić 0");
    }

    @Test
    void testInsertMoneyIncreasesBalance() {
        machine.insertMoney(5.0);
        assertEquals(5.0, machine.getBalance(), "Saldo powinno wzrosnąć o 5.0");

        machine.insertMoney(2.0);
        assertEquals(7.0, machine.getBalance(), "Saldo powinno sumować monety");
    }

    @Test
    void testRefundResetsBalance() {
        machine.insertMoney(5.0);
        machine.refund();
        assertEquals(0.0, machine.getBalance(), "Po zwrocie saldo powinno zostać zresetowane");
    }
}