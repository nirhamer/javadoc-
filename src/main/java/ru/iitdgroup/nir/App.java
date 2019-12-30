package ru.iitdgroup.nir;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class App {

    @Parameter(names = {"-r", "-recurse"}, description = "Recursively search path for java files")
    private boolean recurse = false;

    /**
     * A flag: true - delete old javadoc, false - keep
     */
    @Parameter(names = {"-f", "-regen"}, description = "Force regeneration")
    private boolean regen = false;

    /**
     * The path to find java files
     */
    @Parameter(description = "Path")
    private String path;

    public static void main(String[] args) {
        App app = new App();
        JCommander.newBuilder()
                .addObject(app)
                .build()
                .parse(args);
        try {
            app.run();
        } catch (IOException e) {
            System.out.println("Unable to find path");
        }
    }

    public void run() throws IOException {

        if (path == null) {
            System.out.println("A valid path must be supplied");
            return;
        }

        List<Path> files =  recurse ?  searchRecursive(): searchNonRecursive();
        for (Path path : files) {
            generateJavaDoc(path);
        }
    }

    public List<Path> searchNonRecursive() throws IOException {
        return Files.list(Paths.get(path)).filter(f -> f.toString().endsWith(".java")).collect(Collectors.toList());
    }

    /**
     * Walks for all files in {@link App#path} folder
     * @return list of {@link Path} for all *.java files
     * @throws IOException in case if something is wrong within the filesystem
     */
    public List<Path> searchRecursive() throws IOException {
        List<Path> result;
        try (Stream<Path> walk = Files.walk(Paths.get(path))) {
            result = walk.filter(f -> f.toString().endsWith(".java")).collect(Collectors.toList());
        }
        return result;
    }

    public void generateJavaDoc(Path path) throws IOException {
        List<String> lines = Files.readAllLines(path);
        if(containsJavaDoc(lines) && !regen){
            return;
        }

        if(regen){
            lines = removeJavaDoc(lines);
        }

        StringBuilder sb = new StringBuilder();
        sb.append("/**");
        boolean complete = false;
        for(int i = 0; i < lines.size(); i++){
            if(lines.get(i).contains("@DisplayName")){
                sb.append("\n * ").append(extractFromAnnotationWithParameter(lines.get(i), "name"));
            }
            if(lines.get(i).contains("@Description")){
                sb.append("\n * ").append(extractFromQuotes(lines.get(i)));
                sb.append("\n**/");
                complete = true;
            }

            if( complete && (lines.get(i).isEmpty() && !lines.get(i).startsWith("@"))){
                lines.add(i, sb.toString());
                i++;
                sb.setLength(0);
                sb.append("/**");
                complete = false;
            }
        }
        Files.write(path, lines);
    }

    public boolean containsJavaDoc(List<String> lines){
        for (String line: lines) {
            if(line.startsWith("/**")){
                return true;
            }
        }
        return false;
    }

    public static String extractFromAnnotationWithParameter(String line, String variableName){
        final Pattern p = Pattern.compile(variableName + "[ \\t]*=[ \\t]*\"(.*?)\"");
        final Matcher m = p.matcher(line);
        if (m.find()) {
            return m.group(1);
        }
        return null;
    }

    public static String extractFromQuotes(String line){
        final Pattern p = Pattern.compile("\"(.*?)\"");
        final Matcher m = p.matcher(line);
        if (m.find()) {
            return m.group(1);
        }
        return null;
    }

    public List<String> removeJavaDoc(List<String> lines) {
        lines.removeIf(line -> line.startsWith("/**") || line.startsWith("*") || line.startsWith("**/"));
        return lines;
        /*
        * this is a very important line
         */

    }
}