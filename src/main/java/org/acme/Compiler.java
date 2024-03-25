package org.acme;

import java.io.*;
import java.nio.file.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Stream;

public class Compiler {

    private static final String DOT_COMPIL = ".compil";
    private static final Logger LOGGER = Logger.getLogger(Compiler.class.getName());
    public static void main(String[] args) {
        // create a file named "Calculation" in compiled directory
        Path compiledDir = Paths.get("src/main/java/org/acme/compiled");
        createDirectoriesIfNotExists(compiledDir);

        Path calculationFile = compiledDir.resolve("Calculation.java");
        // if file exists then delete the file
        deleteFileIfExists(calculationFile);
        createFileIfNotExists(calculationFile);

        // write the initial content in the file
        writeInitialContentToFile(calculationFile);

        // open all files in the toBeCompiled directory
        Path toBeCompiledDir = Paths.get("src/main/toBeCompiled");
        List<Path> files = new ArrayList<>();
        try (Stream<Path> walk = Files.walk(toBeCompiledDir)) {
            walk.filter(Files::isRegularFile)
                    .forEach(files::add);
            // remove all files that do not end with ".compil"
            files.removeIf(file -> !file.getFileName().toString().endsWith(DOT_COMPIL));
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, e, () -> "Error opening all files " + toBeCompiledDir);
        }

        // create the router
        StringBuilder router = new StringBuilder("  public static int calculate(String method) {\n    switch (method) {\n");
        for (Path file : files) {
            String fileName = file.getFileName().toString().replace(DOT_COMPIL, "");
            router.append(String.format("      case \"%s\":%n        return %s();%n", fileName, fileName));
        }
        router.append("      default:\n        return 0;\n    }\n  }\n");
        try {
            Files.write(calculationFile, router.toString().getBytes(), StandardOpenOption.APPEND);
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, e, () -> "Error create the router");
        }

        // for each file, read the content,
        // create a method
        // and write it in the file named "Calculation" before the last 
        for (Path file : files) {
            String fileName = file.getFileName().toString().replace(DOT_COMPIL, "");
            try {
                List<String> lines = Files.readAllLines(file);
                StringBuilder result = new StringBuilder();
                for (String line : lines) {
                    if (line.equals("plus")) {
                        result.append("+1");
                    } else if (line.equals("moins")) {
                        result.append("-1");
                    }
                }
                // if the string builder is empty, then add 0
                if (result.isEmpty()) {
                    result.append("0");
                }
                String methodContent = String.format("  public static int %s() {%n    return %s;%n  }%n", fileName, result);
                Files.write(calculationFile, methodContent.getBytes(), StandardOpenOption.APPEND);
            } catch (IOException e) {
                LOGGER.log(Level.SEVERE, e, () -> "Error reading all lines from file " + file);
            }

            // close the Calculation class
        }
        String finalContent = "}\n";
        try {
            Files.write(calculationFile, finalContent.getBytes(), StandardOpenOption.APPEND);
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, e, () -> "Error writing final content to file " + calculationFile);
        }
    }

    static void createDirectoriesIfNotExists(Path directory) {
        try {
            Files.createDirectories(directory);
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, e, () -> "Error creating directories " + directory);
        }
    }

    static void deleteFileIfExists(Path file) {
        try {
            if (Files.exists(file)) {
                Files.delete(file);
            }
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, e, () -> "Error deleting file " + file);
        }
    }

    static void createFileIfNotExists(Path file) {
        try {
            if (!Files.exists(file)) {
                Files.createFile(file);
            }
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, e, () -> "Error creating file " + file);
        }
    }

    static void writeInitialContentToFile(Path file) {
        StringBuilder initialContent = new StringBuilder("package org.acme.compiled;\n\npublic class Calculation {\n");
        initialContent.append("\n");
        initialContent.append(" private Calculation() {throw new IllegalStateException(\"Utility class\");}\n");
        initialContent.append("\n");
        initialContent.append(" static final int ZERO = 0;\n");
        initialContent.append("\n");
        try {
            Files.write(file, initialContent.toString().getBytes());
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, e, () -> "Error writing initial content to file " + file);
        }
    }
}
