package ru.iitdgroup.nir;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class MissingTestAdditions {

//
//    @Test
//    public void canHandleNullTest() {
//        App app = new App();
//        app.containsJavaDoc(null);
//    }
//
//    @Test
//    public void testContainsJavaDoc() {
//        App app = new App();
//        //create lines here
//        //app.containsJavaDoc(null);
//        fail("Not implemented yet");
//    }


    @Test
    public void whatToLearn(){
        assertEquals("A", "A");
        assertTrue(1+1==2);
        assertFalse(1*2==5);
        assertNotEquals("abc", "abf");
        assertNull(null, "Null should be null");
    }
}

