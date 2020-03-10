package ru.iitdgroup.nir;


import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.testng.Assert.assertEquals;
import ru.iitdgroup.nir.App;


public class ExtractFromQuotesTest {

    public ExtractFromQuotesTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Test
    public void test_6() {
        String expectedStringFromMethod = App.extractFromQuotes("String variableName = \"jack\";");
        assertEquals(expectedStringFromMethod, "jack");
    }
}


