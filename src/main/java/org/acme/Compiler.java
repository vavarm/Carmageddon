package org.acme;

import java.io.*;
import java.nio.file.*;
import java.util.*;

public class Compiler {
    public static void main(String[] args) {
        // create a file named "Calculation" in compiled directory
        Path compiledDir = Paths.get("src/main/java/org/acme/compiled");
        try {
            Files.createDirectories(compiledDir);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Path calculationFile = compiledDir.resolve("Calculation.java");
        // if file exists then delete the file
        if (Files.exists(calculationFile)) {
            try {
                Files.delete(calculationFile);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            Files.createFile(calculationFile);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // write the initial content in the file
        StringBuilder initialContent = new StringBuilder("package org.acme.compiled;\n\npublic class Calculation {\n");
        initialContent.append("\n");
        initialContent.append(" private Calculation() {throw new IllegalStateException(\"Utility class\");}\n");
        initialContent.append("\n");
        initialContent.append(" static final int ZERO = 0;\n");
        initialContent.append("\n");
        try {
            Files.write(calculationFile, initialContent.toString().getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }

        // open all files in the toBeCompiled directory
        Path toBeCompiledDir = Paths.get("src/main/toBeCompiled");
        List<Path> files = new ArrayList<>();
        try {
            Files.walk(toBeCompiledDir)
                    .filter(Files::isRegularFile)
                    .forEach(files::add);
            // remove all files that do not end with ".compil"
            files.removeIf(file -> !file.getFileName().toString().endsWith(".compil"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        // create the router
        /*
        public static int calculate(String method){
            switch (method){
            // for each file in the toBeCompiled directory
                case "<filename>":
                    return <filename>();
                default:
                    return 0;
            }
        }
         */
        StringBuilder router = new StringBuilder("  public static int calculate(String method) {\n    switch (method) {\n");
        for (Path file : files) {
            String fileName = file.getFileName().toString().replace(".compil", "");
            router.append(String.format("      case \"%s\":\n        return %s();\n", fileName, fileName));
        }
        router.append("      default:\n        return 0;\n    }\n  }\n");
        try {
            Files.write(calculationFile, router.toString().getBytes(), StandardOpenOption.APPEND);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // for each file, read the content,
        // create a method like this:
        // public int static <name of the file>(){
        //     return <(+1 if plus) (-1 if moins) for each line>
        // }
        // and write it in the file named "Calculation" before the last "}"
        for (Path file : files) {
            String fileName = file.getFileName().toString().replace(".compil", "");
            System.out.println("Creating method for " + fileName);
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
                // if the stringbuilder is empty, then add 0
                if (result.isEmpty()) {
                    result.append("0");
                }
                String methodContent = String.format("  public static int %s() {\n    return %s;\n  }\n", fileName, result.toString());
                Files.write(calculationFile, methodContent.getBytes(), StandardOpenOption.APPEND);
            } catch (IOException e) {
                e.printStackTrace();
            }

            // close the Calculation class
        }
        String finalContent = "}\n";
        try {
            Files.write(calculationFile, finalContent.getBytes(), StandardOpenOption.APPEND);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
