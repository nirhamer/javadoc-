package ru.iitdgroup.nir;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class App {

    @SuppressWarnings("CanBeFinal") //set by JCommander
    @Parameter(names = {"-r", "-recurse"}, description = "Recursively search path for java files")
    private boolean recurse = false;

    @SuppressWarnings("CanBeFinal") //set by JCommander
    @Parameter(names = {"-f", "-regen"}, description = "Force regeneration")
    private boolean regen = false;

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

        List<Path> files = recurse ? searchRecursive() : searchNonRecursive();
        for (Path path : files) {
            readAndWriteFile(path);
        }
    }

    public List<Path> searchNonRecursive() throws IOException {
        return Files.list(Paths.get(path)).filter(f -> f.toString().endsWith(".java")).collect(Collectors.toList());
    }

    public List<Path> searchRecursive() throws IOException {
        List<Path> result;
        try (Stream<Path> walk = Files.walk(Paths.get(path))) {
            result = walk.filter(f -> f.toString().endsWith(".java")).collect(Collectors.toList());
        }
        return result;
    }

    public void readAndWriteFile(Path path) throws IOException {
        List<String> lines = Files.readAllLines(path);
        lines = processLines(lines);
        if (lines == null) return;
        Files.write(path, lines);
    }

    protected List<String> processLines(List<String> lines) {
        if (containsJavaDoc(lines) && !regen) {
            return null;
        }

        if (regen) {
            lines = removeJavaDoc(lines);
        }

        lines = addJavaDocPlaceHolders(lines);
        List<AnnotationInformation> information = new ArrayList<>();
        boolean foundClassDisplayName = false;
        boolean foundClassDescription = false;
        for (String line : lines) {
            if (!foundClassDisplayName && line.contains("@DisplayName")) {
                foundClassDisplayName = true;
                String displayName = extractFromAnnotationWithParameter(line, "name");
                if (information.isEmpty()) {
                    AnnotationInformation info = new AnnotationInformation(true);
                    info.setDisplayName(displayName);
                    information.add(info);
                } else {
                    information.get(0).setDescription(displayName);

                }
                continue;
            }

            if (!foundClassDescription && line.contains("@Description")) {
                foundClassDescription = true;
                String description = extractFromQuotes(line);
                if (information.isEmpty()) {
                    AnnotationInformation info = new AnnotationInformation(true);
                    info.setDescription(description);
                    information.add(info);
                } else {
                    information.get(0).setDescription(description);
                }
                continue;
            }

            if (foundClassDisplayName && line.contains("@DisplayName")) {
                information.add(new AnnotationInformation(extractFromAnnotationWithParameter(line, "")));
            }
        }
        int index = 0;
        for (int i = 0; i < lines.size(); i++) {
            String line = lines.get(i);
            if (line.contains("JAVADOC")) {
                AnnotationInformation info = information.get(index++);
                lines.set(i, wrapInJavaDoc(lines.get(i + 1).indexOf(lines.get(i + 1).trim())
                        , info.getDisplayName(), info.getDescription()));
            }
        }
        return lines;
    }


    public int determinePadding(List<String> lines, int index) {
        int padding = 0;
        for (int i = index; i < lines.size(); i++) {
            if (!lines.get(i).trim().isEmpty()) {
                for (char c : lines.get(i).toCharArray()) {
                    if (Character.isWhitespace(c)) {
                        padding++;
                    } else {
                        break;
                    }
                }
            }
        }
        return padding;
    }


    public List<String> addJavaDocPlaceHolders(List<String> lines) {
        boolean foundClass = false;
        for (int i = 0; i < lines.size(); i++) {
            if (lines.get(i).contains("{") && !foundClass) {
                foundClass = true;
                lines.add(i++, "JAVADOC");
            }

            if (foundClass && lines.get(i).contains("@DisplayName")) {
                lines.add(i++, "JAVADOC");
            }
        }
        return lines;
    }

    public String wrapInJavaDoc(int padding, String... lines) {
        StringBuilder offset = new StringBuilder();
        for (int i = 0; i < padding; i++) {
            offset.append(" ");
        }
        StringBuilder sb = new StringBuilder();
        sb.append(offset).append("/**");
        sb.append(System.lineSeparator());
        for (String line : lines) {
            if (!line.isEmpty()) {
                sb.append(offset).append(" * ").append(line);
                sb.append(System.lineSeparator());
            }
        }
        sb.append(offset).append("**/");
        return sb.toString();
    }

    public boolean containsJavaDoc(List<String> lines) {
        for (String line : lines) {
            if (line.startsWith("/**")) {
                return true;
            }
        }
        return false;
    }

    public static String extractFromAnnotationWithParameter(String line, String variableName) {
        final Pattern p = Pattern.compile(variableName + "[ \\t]*=[ \\t]*\"(.*?)\"");
        final Matcher m = p.matcher(line);
        if (m.find()) {
            return m.group(1);
        }
        return null;
    }

    public static String extractFromQuotes(String line) {
        final Pattern p = Pattern.compile("\"(.*?)\"");
        final Matcher m = p.matcher(line);
        if (m.find()) {
            return m.group(1);
        }
        return null;
    }

    public List<String> removeJavaDoc(List<String> lines) {
        States currentState = States.TEXT;
        //lines.removeIf(line -> line.trim().startsWith("/**") || line.trim().startsWith("*") || line.trim().startsWith("**/"));
        for (Iterator<String> iterator = lines.iterator(); iterator.hasNext(); ) {
            String line = iterator.next();
            switch (currentState) {
                case TEXT:
                    if (line.contains("/**")) {
                        currentState = States.JAVADOC;
                        iterator.remove();  //remove this line
                    }
                    break;
                case JAVADOC:
                    iterator.remove();   //this line
                    if (line.contains("*/")) {
                        currentState = States.TEXT;
                    }
                    break;
                default:
                    System.out.println("What is the state??? I don't know how to handle it!!!");
            }


            System.out.printf("Checking: -->> %s, state is %s%n",
                    line,
                    currentState);
        }
        return lines;
    }

    private enum States{
        TEXT,
        JAVADOC,
        SINGLE_COMMENT,
        MULTI_COMMENT
    }
}