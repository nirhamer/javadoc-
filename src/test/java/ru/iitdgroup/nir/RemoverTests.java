package ru.iitdgroup.nir;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.testng.Assert.assertEquals;

/**
 * All tests to remove javadoc carefully
 */
public class RemoverTests {

    /**
     * An instance of  the  application to be used by all tests
     */
    private static App sharedApp;

    @BeforeClass
    public static void setUp(){
        sharedApp = new App();
    }





    @Test
    public void testExcel_1() {
        final String example = "package ru.iitdgroup.nir;\n" +
                "import org.testng.annotations.Test;\n" +
                "public class ExampleTest {\n" +
                "       @Test\n" +
                "    public void testSimple(){";

        List<String> lines = Arrays.asList(example.split("\n"));
        final List<String> after = sharedApp.removeJavaDoc(lines);
        assertEquals(after.size(), lines.size());
    }



    @Test
    public void testExcel_2() {
        final String example = "package ru.iitdgroup.nir;\n" +
                "import org.testng.annotations.Test;\n" +
                "public class ExampleTest {\n" +
                " //    @Test\n" +
                "    public void testSimple(){";

        List<String> lines = Arrays.asList(example.split("\n"));
        final List<String> after = sharedApp.removeJavaDoc(lines);
        assertEquals(after.size(), lines.size());
    }


    @Test
    public void testExcel_3() {
        final String example = "package ru.iitdgroup.nir;\n" +
                "import org.testng.annotations.Test;\n" +
                "public class ExampleTest {\n" +
                "/*\n" +
                "can u tall the difference between\n" +
                " multi comment and Javadoc\n" +
                "*/\n" +
                "\n" +
                "/**\n" +
                "*\n" +
                "*\n" +
                "*I suppose were about to find out now \n" +
                "*/\n" +
                "    public void testSimple(){\n";
        final String expected = "package ru.iitdgroup.nir;\n" +
                "import org.testng.annotations.Test;\n" +
                "public class ExampleTest {\n" +
                "/*\n" +
                "can u tall the difference between\n" +
                " multi comment and Javadoc\n" +
                "*/\n" +
                "\n" +
                "    public void testSimple(){\n";


        List<String> lines = new ArrayList<>(Arrays.asList(example.split("\n")));
        final List<String> after = sharedApp.removeJavaDoc(lines);
        assertEquals(String.join("\n",after)+"\n", expected);
    }

    @Test
    public void testString_Join(){
        assertEquals(String.join("\n","aaa","bbb"),"aaa\nbbb");
    }

    @Test
    public void testExcel_4() {
        final String example = "package ru.iitdgroup.nir;\n" +
                "import org.testng.annotations.Test;\n" +
                "public class ExampleTest {\n" +
                "       \n" +
                "/**The HelloWorld program implements an application that\n" +
                " * simply displays \"Hello World\n" +
                "*/\n" +
                "{ \n" +
                "    // our program begins with a call to main()\n" +
                "    // Prints \"Hello, World\n" +
                "    public static void main(String args[]) \n" +
                "    { \n" +
                "        System.out.println(\"Hello, World\"); \n" +
                "    } \n" +
                "} \n" +
                "\n" +
                "\n" +
                "    public void testSimple(){";
        final String expected = "package ru.iitdgroup.nir;\n" +
                "import org.testng.annotations.Test;\n" +
                "public class ExampleTest {\n" +
                "       \n" +"{ \n" +
                "    // our program begins with a call to main()\n" +
                "    // Prints \"Hello, World\n" +
                "    public static void main(String args[]) \n" +
                "    { \n" +
                "        System.out.println(\"Hello, World\"); \n" +
                "    } \n" +
                "} \n" +
                "\n" +
                "\n" +
                "    public void testSimple(){";


        List<String> lines = new ArrayList<>(Arrays.asList(example.split("\n")));
        final List<String> after = sharedApp.removeJavaDoc(lines);
        assertEquals(String.join("\n",after), expected);
    }


    @Test
    public void testExcel_5() {
        final String example = "package ru.iitdgroup.nir;\n" +
                "\n" +
                "import org.testng.annotations.Test;\n" +
                "\n" +
                "/**\n" +
                " * Some experiments with TestNG\n" +
                " *\n" +
                " *\n" +
                " * look at\n" +
                " * https://testng.org/doc/\n" +
                " * https://www.tutorialspoint.com/testng/testng_overview.htm\n" +
                " *\n" +
                " */\n" +
                "public class ExampleTest {\n" +
                "\n" +
                "    @Test\n" +
                "    public void testSimple(){";
        final String expected = "package ru.iitdgroup.nir;\n" +
                "\n" +
                "import org.testng.annotations.Test;\n" +
                "\n" + "public class ExampleTest {\n" +
                "\n" +
                "    @Test\n" +
                "    public void testSimple(){";


        List<String> lines = new ArrayList<>(Arrays.asList(example.split("\n")));
        final List<String> after = sharedApp.removeJavaDoc(lines);
        assertEquals(String.join("\n",after), expected);
    }


    @Test
    public void testExcel_6() {
        final String example = "package ru.iitdgroup.nir;\n" +
                "import org.testng.annotations.Test;\n" +
                "public class ExampleTest {\n" +
                "\n" +
                "/*\n" +
                "Sum of two numbers\n" +
                "*/\n" +
                "\n" +
                "public class AddTwoNumbers {\n" +
                "\n" +
                "   public static void main(String[] args) {\n" +
                "        \n" +
                "      int num1 = 5, num2 = 15, sum;\n" +
                "      sum = num1 + num2;\n" +
                "\n" +
                "      System.out.println(\"Sum of these numbers: \"+sum);\n" +
                "   }\n" +
                "}\n" +
                "\n" +
                "    public void testSimple(){";


        List<String> lines = Arrays.asList(example.split("\n"));
        final List<String> after = sharedApp.removeJavaDoc(lines);
        assertEquals(after.size(), lines.size());
    }


    @Test
    public void testExcel_7() {
        final String example = "package ru.iitdgroup.nir;\n" +
                "import org.testing.annotations.Test;\n" +
                "public class ExampleTest {\n" +
                "\n" +
                "class Integers {\n" +
                "  public static void main(String[] arguments) {\n" +
                "    int c; //declaring a variable\n" +
                " \n" +
                "  /* Using a for loop to repeat instruction execution */\n" +
                " \n" +
                "    for (c = 1; c <= 10; c++) {\n" +
                "      System.out.println(c);\n" +
                "    }\n" +
                "  }\n" +
                "}\n" +
                "\n" +
                "    public void testSimple(){";


        List<String> lines = Arrays.asList(example.split("\n"));
        final List<String> after = sharedApp.removeJavaDoc(lines);
        assertEquals(after.size(), lines.size());
    }



    @Test
    public void testExcel_8() {
        final String example = "package ru.iitdgroup.nir;\n" +
                "import org.testng.annotations.Test;\n" +
                "public class ExampleTest {\n" +
                "\n" +
                "//test *1\n" +
                "\n" +
                "/*\n" +
                "test 2\n" +
                "*/\n" +
                "\n" +
                "    public void testSimple(){";


        List<String> lines = Arrays.asList(example.split("\n"));
        final List<String> after = sharedApp.removeJavaDoc(lines);
        assertEquals(after.size(), lines.size());
    }



    @Test
    public void testExcel_9() {
        final String example = "package ru.iitdgroup.nir;\n" +
                "import org.testng.annotations.Test;\n" +
                "public class ExampleTest {\n" +
                "\n" +
                "//*\n" +
                " test 3\n" +
                "*/\n" +
                "\n" +
                " public static void main(String args[]) \n" +
                "    { \n" +
                "        System.out.println(\"Hello, World\"); \n" +
                "    } \n" +
                "} \n" +
                "\n" +
                "\n" +
                "/*\n" +
                "keep this line\n" +
                "*/\n" +
                "\n" +
                "\n" +
                "\n" +
                "\n" +
                "    public void testSimple(){";


        List<String> lines = Arrays.asList(example.split("\n"));
        final List<String> after = sharedApp.removeJavaDoc(lines);
        assertEquals(after.size(), lines.size());
    }


}
