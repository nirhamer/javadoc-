package ru.iitdgroup.nir;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

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

/**
 *
 * @author
 */
class processSingleLine_publicTest {

    public processSingleLine_publicTest() {
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
        List<String> expectedLines = Arrays.asList(expected.split("\n"));
        List<AnnotationInformation> information = new ArrayList<>();
        App.FoundValues foundValues = new App.FoundValues();
        int count = 0;
        for (String line : expectedLines) {
            count++;
            new App().processSingleLine_public(information, line, foundValues);
        }
        Assert.assertTrue(count == 4);
    }
}

