package ru.iitdgroup.nir;


import java.io.File;
import java.util.ArrayList;
import java.util.List;


class App
{
    public static void main(String[] args) {

        final File folder = new File("C:\\Users\\nir\\Desktop\\model");

        List<String> result = new ArrayList<>();

        search(".*\\.java", folder, result);

        for (String s : result) {
            System.out.println(s);
        }

    }

    public static void search(final String pattern, final File folder, List<String> result) {
        for (final File f : folder.listFiles()) {

            if (f.isDirectory()) {
                search(pattern, f, result);
            }

            if (f.isFile()) {
                if (f.getName().matches(pattern)) {
                    result.add(f.getAbsolutePath());


                }
            }
        }

    }
}



