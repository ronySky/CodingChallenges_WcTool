package com.ronysky;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;

class WordCounterServiceTest {
    WordCounterService service;

    @BeforeEach
    void setup() throws URISyntaxException {
        Path path = Paths.get(Objects.requireNonNull(WordCounterServiceTest.class.getClassLoader().getResource("test.txt")).toURI());
        service = new WordCounterService(path);
    }

    @Test
    void When_CountBytes_Then_ReturnByteCount() throws IOException {
        long expected = 342190;
        long actual = service.countBytes();

        assertEquals(expected, actual);
    }

    @Test
    void When_CountLines_Then_ReturnLineCount() throws IOException {
        long expected = 7145;
        long actual = service.countLines();

        assertEquals(expected, actual);
    }

    @Test
    void When_CountWords_Then_ReturnWordCount() throws IOException {
        long expected = 58164;
        long actual = service.countWords();

        assertEquals(expected, actual);
    }

    @Test
    void When_CountChars_Then_ReturnCharCount() throws IOException {
        long expected = 339292;
        long actual = service.countChars();

        assertEquals(expected, actual);
    }
}
