package org.writer;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CsvWriterTest {

    private static CsvWriter csvWriter;

    @BeforeAll
    static void setUp() {
        csvWriter = new CsvWriter();
    }

    /**
     * Тестирует метод записи данных в файл с null в качестве параметра {@code data}.
     * Возникает {@link NullPointerException}.
     *
     * @throws IOException не выбрасывается.
     */
    @Test
    void writeToFileNullTest() throws IOException {
        Path file = Files.createTempFile("null", ".csv");

        NullPointerException exception = assertThrows(NullPointerException.class, () ->
                csvWriter.writeToFile(null, file.toString()));

        assertEquals("Cannot invoke \"java.util.List.iterator()\" because \"data\" is null",
                exception.getMessage());
    }

    /**
     * Тестирует метод записи данных в файл с пустым списком в качестве параметра {@code data}.
     * Получаем пустой файл.
     *
     * @throws IOException не выбрасывается.
     */
    @Test
    void writeToFileEmptyListTest() throws IOException {
        Path file = Files.createTempFile("empty", ".csv");

        csvWriter.writeToFile(new ArrayList<>(), file.toString());

        List<String> lines = Files.readAllLines(file);
        assertTrue(lines.isEmpty());
    }
}