package org.acme;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class CompilerTest {

    @TempDir
    public Path tempDir;

    private Path toBeCompiledDir;
    private Path compiledDir;

    @BeforeEach
    void setUp() throws IOException {
        toBeCompiledDir = tempDir.resolve("toBeCompiled");
        compiledDir = tempDir.resolve("compiled");
        Compiler.createDirectoriesIfNotExists(toBeCompiledDir);
        Compiler.createDirectoriesIfNotExists(compiledDir);
    }

    @Test
    void testCreateDirectoriesIfNotExists() throws IOException {
        Compiler.createDirectoriesIfNotExists(compiledDir);
        assertTrue(Files.exists(compiledDir));
    }

    @Test
    void testDeleteFileIfExists() throws IOException {
        Path file = compiledDir.resolve("Calculation.java");
        Files.createFile(file);
        Compiler.deleteFileIfExists(file);
        assertFalse(Files.exists(file));
    }

    @Test
    void testCreateFileIfNotExists() throws IOException {
        Path file = compiledDir.resolve("Calculation.java");
        Compiler.createFileIfNotExists(file);
        assertTrue(Files.exists(file));
    }

    @Test
    void testWriteInitialContentToFile() throws IOException {
        Path file = compiledDir.resolve("Calculation.java");
        Compiler.writeInitialContentToFile(file);
        List<String> lines = Files.readAllLines(file);
        assertFalse(lines.isEmpty());
    }

    @Test
    void testGetCompilFiles() throws IOException {
        Path file1 = toBeCompiledDir.resolve("file1.compil");
        Path file2 = toBeCompiledDir.resolve("file2.compil");
        Path file3 = toBeCompiledDir.resolve("file3.txt");
        Files.write(file1, "plus".getBytes(), StandardOpenOption.CREATE);
        Files.write(file2, "moins".getBytes(), StandardOpenOption.CREATE);
        Files.write(file3, "hello world".getBytes(), StandardOpenOption.CREATE);

        List<Path> files = new ArrayList<>();
        Compiler.getCompilFiles(toBeCompiledDir, files);
        assertEquals(2, files.size());
    }

    @Test
    void createRouter() throws IOException {
        Path calculationFile = compiledDir.resolve("Calculation.java");
        Compiler.createFileIfNotExists(calculationFile);

        Path file1 = toBeCompiledDir.resolve("file1.compil");
        Path file2 = toBeCompiledDir.resolve("file2.compil");
        Files.createFile(file1);
        Files.createFile(file2);

        List<Path> files = new ArrayList<>();
        Compiler.getCompilFiles(toBeCompiledDir, files);

        Compiler.createRouter(calculationFile, files);

        List<String> lines = Files.readAllLines(calculationFile);
        String router = "  public static int calculate(String method) {    switch (method) {      case \"file1\":        return file1();      case \"file2\":        return file2();      default:        return 0;    }  }".replace("\n", "");
        String calculationFileContents = String.join("", lines);
        assertTrue(calculationFileContents.replace("\n", "").contains(router));
    }

    @Test
    void writeMethodToFile() throws IOException {
        Path file = toBeCompiledDir.resolve("file1.compil");
        Files.createFile(file);
        Files.write(file, "plus".getBytes(), StandardOpenOption.TRUNCATE_EXISTING);

        Path calculationFile = compiledDir.resolve("Calculation.java");
        Compiler.createFileIfNotExists(calculationFile);

        List<Path> files = new ArrayList<>();
        Compiler.getCompilFiles(toBeCompiledDir, files);

        Compiler.writeMethodToFile(calculationFile, files);

        List<String> lines = Files.readAllLines(calculationFile);
        String method = "  public static int file1() {    return +1;  }".replace("\n", "");
        String calculationFileContents = String.join("", lines);
        assertTrue(calculationFileContents.replace("\n", "").contains(method));
    }

    @Test
    void closeClass() throws IOException {
        Path calculationFile = compiledDir.resolve("Calculation.java");
        Compiler.createFileIfNotExists(calculationFile);

        Compiler.closeClass(calculationFile);

        List<String> lines = Files.readAllLines(calculationFile);
        String finalContent = "}".replace("\n", "");
        String calculationFileContents = String.join("", lines);
        assertTrue(calculationFileContents.replace("\n", "").contains(finalContent));

    }
}