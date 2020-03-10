package ru.iitdgroup.nir;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.testng.Assert;
import ru.iitdgroup.nir.AnnotationInformation;
import ru.iitdgroup.nir.App;


public class processDescription_publicTest {

    public processDescription_publicTest() {
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
        String[] expectedLines = expected.split("\n");
        List<AnnotationInformation> information = new ArrayList<>();
        App.FoundValues foundValues = new App.FoundValues();
        boolean flag = true;
        for (String line : expectedLines) {
            flag = new App().processDescription_public(information, line, foundValues);
        }
        Assert.assertFalse(flag);
    }
}
