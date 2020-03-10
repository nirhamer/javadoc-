package ru.iitdgroup.nir;

import org.junit.Test;

import static org.testng.Assert.assertEquals;

public class ExtractFromAnnotationWithParameter {

    
    @Test
    public void test_1() {
        App app = new App();
        String expectedStringFromMethod = App.extractFromAnnotationWithParameter("String variableName = \"1995\";", "variableName");
        assertEquals(expectedStringFromMethod, "1995");
    }

    @Test
    public void test_2() {
        App app = new App();
        String expectedStringFromMethod = App.extractFromAnnotationWithParameter("String variableName = \"lore\";", "variableName");
        assertEquals(expectedStringFromMethod, "lore");
    }

}
