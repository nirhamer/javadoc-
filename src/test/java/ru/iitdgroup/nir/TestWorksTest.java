package ru.iitdgroup.nir;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class TestWorksTest {

    @Test
    public void testAdd() {
        assertEquals(TestWorks.add(1, 2), 3);
        assertEquals(TestWorks.add(40, 20), 60);
    }

    @Test

    public void  testD() {
        assertEquals(TestWorks.divide(10,2), 5);

        assertTrue(Double.isInfinite(TestWorks.divide(10,0) ));

    }

}