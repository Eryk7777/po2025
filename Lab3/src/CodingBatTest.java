import static org.junit.Assert.*;

public class CodingBatTest {
    CodingBat test = new CodingBat();

    @org.junit.Test
    public void sleepIn() {
        assertTrue(test.sleepIn(false, false));
        assertFalse(test.sleepIn(true, false));
        assertTrue(test.sleepIn(false, true));
    }

    @org.junit.Test
    public void nearHundred() {
        assertTrue(test.nearHundred(93));
        assertFalse(test.nearHundred(89));
        assertTrue(test.nearHundred(90));
    }

    @org.junit.Test
    public void countEvens() {
        int[] n = {2, 1, 2, 3, 4};
        int[] m = {2, 2, 0};
        int[] o = {1, 2, 5};

        assertEquals(3, test.countEvents(n));
        assertEquals(3, test.countEvents(m));
        assertEquals(1, test.countEvents(o));
    }

    @org.junit.Test
    public void helloName() {
        String n1 = "Michal";
        String n2 = "Eryk";
        String n3 = "Nikodem";

        assertEquals("Hello Michal!", test.helloName(n1));
        assertEquals("Hello Eryk!", test.helloName(n2) );
        assertEquals("Hello Nikodem!", test.helloName(n3) );
    }
}