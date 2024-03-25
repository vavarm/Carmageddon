package org.acme;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CompilerTest {

    @TempDir
    public Path tempDir;

    private Path toBeCompiledDir;
    private Path compiledDir;

    @BeforeEach
    void setUp() {
        toBeCompiledDir = tempDir.resolve("toBeCompiled");
        compiledDir = tempDir.resolve("compiled");
        Compiler.createDirectoriesIfNotExists(toBeCompiledDir);
        Compiler.createDirectoriesIfNotExists(compiledDir);
    }

    @Test
    void testCreateDirectoriesIfNotExists() throws IOException {
        Path dir = tempDir.resolve("newDir");
        Compiler.createDirectoriesIfNotExists(dir);
        assertTrue(Files.exists(dir));
    }

    @Test
    void testDeleteFileIfExists() throws IOException {
        Path file = tempDir.resolve("testFile.txt");
        Files.createFile(file);
        Compiler.deleteFileIfExists(file);
        assertFalse(Files.exists(file));
    }

    @Test
    void testCreateFileIfNotExists() throws IOException {
        Path file = tempDir.resolve("testFile.txt");
        Compiler.createFileIfNotExists(file);
        assertTrue(Files.exists(file));
    }

    @Test
    void testWriteInitialContentToFile() throws IOException {
        Path file = tempDir.resolve("Calculation.java");
        Compiler.writeInitialContentToFile(file);
        List<String> lines = Files.readAllLines(file);
        assertEquals(8, lines.size());
        assertEquals("package org.acme.compiled;", lines.get(0));
        assertEquals("", lines.get(1));
        assertEquals("public class Calculation {", lines.get(2));
        assertEquals("", lines.get(3));
        assertEquals(" private Calculation() {throw new IllegalStateException(\"Utility class\");}", lines.get(4));
    }

}