package ru.iitdgroup.nir;



import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.testng.Assert.assertEquals;
import ru.iitdgroup.nir.App;


public class WrapInJavaDocTest {

    public WrapInJavaDocTest() {
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
    public void test_9() {
        final String expected = " /**\n"
                + "* dream hope ambition\n"
                + "*/";
        assertEquals(new App().wrapInJavaDoc(1, "dream hope ambition"), expected);
    }
}



















