package ru.iitdgroup.nir;

import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.testng.Assert.assertEquals;

/**
 * Some experiments with TestNG
 */
public class AppTest2 {

    @Test
    public void testSimple() {

    }


    @Test
    public void testRemoveJavaDoc() {
        App app = new App();

        List<String> javadocLines = new ArrayList<>(Arrays.asList(
                "package ...\n",
                "import ...\n",
                "\n",
                "/**ABC\n",
                "*   ddd\n",
                " */\n",
                "\n",
                "public class App{\n"));

        final List<String> after = app.removeJavaDoc(javadocLines);
        assertEquals(after.size(), 6);

        assertEquals(after.get(0),"package ...");

    }


    @Test
    public void testKeepRegularComments() {
        App app = new App();

        /*
        * Example comment
         */

        List<String> commentLines = new ArrayList<>(Arrays.asList(
                "package test\n",
                "  /*ABC\n",
                "  *   ddd\n",
                " */\n",
                " \n",
                " public class App{\n"
        ));

        final List<String> after = app.removeJavaDoc(commentLines);

        System.out.println(after);

        assertEquals(after.size(), 6);
        assertEquals(after.get(0),"package test");
        assertEquals(after.get(1),"  /*ABC");
        assertEquals(after.get(2),"  *   ddd");
        assertEquals(after.get(3)," */");
        assertEquals(after.get(4)," ");
        assertEquals(after.get(5)," public class App{");
    }


}
