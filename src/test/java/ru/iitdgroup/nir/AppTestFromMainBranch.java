package ru.iitdgroup.nir;

import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.testng.Assert.assertEquals;

/**
 * Some experiments with TestNG
 */
public class AppTestFromMainBranch {

    @Test
    public void testSimple() {

    }


    @Test
    public void testRemoveJavaDoc() {
        App app = new App();

        List<String> javadocLines = new ArrayList<>(Arrays.asList(
                "package ...",
                "import ...",
                "",
                "/**ABC",
                "*   ddd",
                " */",
                "",
                "public class App{"));

        final List<String> after = app.removeJavaDoc(javadocLines);
        assertEquals(after.size(), 5);
        assertEquals(after.get(0),"package ...");

    }


    @Test
    public void testKeepComments() {
        App app = new App();

        /*
         * Example comment
         */

        List<String> commentLines = new ArrayList<>(Arrays.asList(
                "package test",
                "  /*ABC",
                "  *   ddd",
                " */",
                " ",
                " public class App{"
        ));

        final List<String> after = app.removeJavaDoc(commentLines);
        assertEquals(after.size(), 6);
        assertEquals(after.get(0),"package test");
        assertEquals(after.get(1),"  /*ABC");
        assertEquals(after.get(2),"  *   ddd");
        assertEquals(after.get(3)," */");
        assertEquals(after.get(4)," ");
        assertEquals(after.get(5)," public class App{");
    }


}
