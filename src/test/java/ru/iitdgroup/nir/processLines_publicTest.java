package ru.iitdgroup.nir;


import java.util.Arrays;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.testng.Assert;
import ru.iitdgroup.nir.App;


public class processLines_publicTest {

    public processLines_publicTest() {
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
    public void test_8() {
        final String expected = "/**JAVADOC**/\n"
                + "public class revolution {\n"
                + "JAVADOC\n"
                + "@DisplayName\n"
                + "    public static void end {\n"
                + "        System.out.println(\"anno2050\");"
                + "//hi test   "
                + "/*how is your mood"
                + "{*/    "
                + "    }"
                + "    }"
                + "    }";
        List<String> expectedLines = Arrays.asList(expected.split("\n"));

        Assert.assertTrue(new App().containsJavaDoc(expectedLines));
    }
    @Test
    public void test_10() {
        final String expected = "public class revolution {\n"
                + "@DisplayName\n"
                + "    public static void end {\n"
                + "        System.out.println(\"anno2050\");"
                + "//hi test   "
                + "/*how is your mood"
                + "{*/    "
                + "    }"
                + "    }"
                + "    }";
        List<String> expectedLines = Arrays.asList(expected.split("\n"));
        List<String> expectedLiness = (new App().processLines_public(expectedLines));
        Assert.assertTrue(expectedLiness.size() == 4);
        new App().processSingleLine_public(null,null,null);
    }
}
