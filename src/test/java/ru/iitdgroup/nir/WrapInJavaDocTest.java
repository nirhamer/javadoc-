package ru.iitdgroup.nir;

import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

import static org.testng.Assert.assertEquals;


/**
 * Test javaDoc test
 */
public class WrapInJavaDocTest {

    //region Finished tests
    /**
     * Test full lines of code
     */
    @Test
    public void testExcel_1() {

    App app = new App();

        final String[] strings = {"dream", "hope", "ambition"};
        final String wrapInJavaDoc = app.wrapInJavaDoc(5, strings);
        System.out.println(wrapInJavaDoc);
        assertEquals(wrapInJavaDoc, "     /**\r\n" +
                "      * dream\r\n" +
                "      * hope\r\n" +
                "      * ambition\r\n" +
                "     */");
    }

    @Test
    public void testExcel_2() {


        App app = new App();

        final String[] strings = {"/jack", "/*and wolf", "nero"};
        final String wrapInJavaDoc = app.wrapInJavaDoc(5, strings);
        System.out.println(wrapInJavaDoc);
        assertEquals(wrapInJavaDoc, "     /**\r\n" +
                "      * /jack\r\n" +
                "      * /*and wolf\r\n" +
                "      * nero\r\n" +
                "     */");
    }

    @Test
    public void testExcel_3() {


        App app = new App();

        final String[] strings = {"/**not javaDoc", "//fair day", "/*close"};
        final String wrapInJavaDoc = app.wrapInJavaDoc(5, strings);
        System.out.println(wrapInJavaDoc);
        assertEquals(wrapInJavaDoc, "     /**\r\n" +
                "      * /**not javaDoc\r\n" +
                "      * //fair day\r\n" +
                "      * /*close\r\n" +
                "     */");
    }
    //endregion

















}
