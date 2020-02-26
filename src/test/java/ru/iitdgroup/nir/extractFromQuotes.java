package ru.iitdgroup.nir;

import org.testng.annotations.Test;

import java.util.Collections;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class extractFromQuotes {





@Test
    public void test1(){
    App app = new App();
    final String regex = "\"(.*?)\"";
    final String string = "\"hi my name is jack how are u doing this fine day\"";

    final Pattern pattern = Pattern.compile(regex, Pattern.MULTILINE);
    final Matcher matcher = pattern.matcher(string);

    while (matcher.find()) {
        System.out.println("Full match: " + matcher.group(0));
        for (int i = 1; i <= matcher.groupCount(); i++) {
            System.out.println("Group " + i + ": " + matcher.group(i));
            App.extractFromQuotes(regex);
            App.extractFromQuotes(string);
        }
    }
}









    @Test
    public void test2(){
        App app = new App();
        final String regex = "\"(.*?)\"";
        final String string = "\"what  a lovely day were having today (i bet john is having fun)\"";

        final Pattern pattern = Pattern.compile(regex, Pattern.MULTILINE);
        final Matcher matcher = pattern.matcher(string);

        while (matcher.find()) {
            System.out.println("Full match: " + matcher.group(0));
            for (int i = 1; i <= matcher.groupCount(); i++) {
                System.out.println("Group " + i + ": " + matcher.group(i));
                app.addJavaDocPlaceHolders(Collections.singletonList(regex));
                app.addJavaDocPlaceHolders(Collections.singletonList(string));
            }
        }
    }

}

