package com.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.IOException;
import java.nio.file.Paths;

public class AppTest {

    @Test
    public void testCountLines() throws IOException {
        App app = new App();
        String testFilePath = Paths.get("src", "test", "resources", "test_input.txt").toString();
        int lineCount = app.countLines(testFilePath);
        assertEquals(3, lineCount, "The line count should be 3");
    }

    @Test
    public void testCountLinesEmptyFile() throws IOException {
        App app = new App();
        String testFilePath = Paths.get("src", "test", "resources", "empty.txt").toString();
        // Create an empty file for this test
        java.nio.file.Files.createFile(Paths.get(testFilePath));
        int lineCount = app.countLines(testFilePath);
        assertEquals(0, lineCount, "The line count should be 0");
        // Clean up the empty file after test
        java.nio.file.Files.deleteIfExists(Paths.get(testFilePath));
    }

    @Test
    public void testCountLinesNonExistentFile() {
        App app = new App();
        String testFilePath = Paths.get("src", "test", "resources", "nonexistent.txt").toString();
        assertThrows(IOException.class, () -> {
            app.countLines(testFilePath);
        }, "Expected IOException for non-existent file");
    }
}

