package ru.iitdgroup.nir;

import org.testng.annotations.Test;

import java.util.Collections;

import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertTrue;

public class ContainsJavaDoc {



    @Test public void test1(){
        App app = new App();
        assertEquals(app.containsJavaDoc(Collections.singletonList("/**Hi!\n" +
                "How are you ?\n" +
                "I'm gr\n" +
                "EAT !\n" +
                " hi that's too funny\n" +
                "alright\n" +
                "see you later")),true);
    }
}
