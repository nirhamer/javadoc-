package ru.iitdgroup.nir;



import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.testng.Assert;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
import ru.iitdgroup.nir.App;


public class JavaDocPlaceHolderTest {

    public JavaDocPlaceHolderTest() {
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

    /**
     * Prints the content on system::out
     *
     * @param linesToPrint list of string lines to print
     */
    private void printList(List<String> linesToPrint) {
        linesToPrint.stream()
                .forEach(System.out::println);
    }

    /**
     * Return modifiable list from a big string. The string is chopped by new
     * line separators
     *
     * @param source string to chop
     * @return List of strings
     */
    private List<String> toList(String source) {
        return new ArrayList<>(Arrays.asList(source.split("\n")));
    }


    @Test
    public void test_1() {
        App app = new App();

        final String input = "public class pears {\n"
                + "    @DisplayName\n"
                + "    public static void bar() {\n"
                + "        System.out.println(\"lads\");\n"
                + "    }";
        List<String> inputLines
                = new ArrayList<>(Arrays.asList(input.split("\n"))); //we need to create an modifiable list from asList;



        final String expected = "JAVADOC\n"
                + "public class pears {\n"
                + "JAVADOC\n"
                + "    @DisplayName\n"
                + "    public static void bar() {\n"
                + "        System.out.println(\"lads\");\n"
                + "    }";
        List<String> expectedLines = Arrays.asList(expected.split("\n"));

        List<String> actualLines = app.addJavaDocPlaceHolders(inputLines);

        assertEquals(expectedLines, actualLines);
    }

    @Test
    public void test_2() {

        App app = new App();
        final String input = "public class gen1020 {\n"
                + "    @DisplayName\n"
                + "    public static void end() {\n"
                + "        System.out.println(\"2020\");\n"
                + "    }\n"
                + "    }\n";
        List<String> inputLines = toList(input);

        final String expected = "JAVADOC\n"
                + "public class gen1020 {\n"
                + "JAVADOC\n"
                + "    @DisplayName\n"
                + "    public static void end() {\n"
                + "        System.out.println(\"2020\");\n"
                + "    }\n"
                + "    }\n";
        List<String> expectedLines = Arrays.asList(expected.split("\n"));

        List<String> actualLines = app.addJavaDocPlaceHolders(inputLines);

        assertEquals(expectedLines, actualLines);
    }

    @Test
    public void test_3() {
        App app = new App();
        final String input = "public class revolution {\n"
                + "@DisplayName\n"
                + "    public static void end {\n"
                + "        System.out.println(\"anno2050\");"
                + "//hi test   "
                + "/*how is your mood"
                + "{*/    "
                + "    }"
                + "    }"
                + "    }";
        List<String> inputLines = toList(input);

        final String expected = "JAVADOC\n"
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

        List<String> actualLines = app.addJavaDocPlaceHolders(inputLines);

        assertEquals(expectedLines, actualLines);
    }
}
