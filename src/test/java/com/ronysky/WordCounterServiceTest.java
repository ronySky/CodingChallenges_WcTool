package com.ronysky;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;

class WordCounterServiceTest {
    WordCounterService service;

    @BeforeEach
    void setup() throws IOException {
        InputStream input = Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("test.txt"));
        String text = new String(input.readAllBytes());
        service = new WordCounterService(text);
    }

    @Test
    void When_CountBytes_Then_ReturnByteCount() {
        long expected = 342190;
        long actual = service.countBytes();

        assertEquals(expected, actual);
    }

    @Test
    void When_CountLines_Then_ReturnLineCount() {
        long expected = 7145;
        long actual = service.countLines();

        assertEquals(expected, actual);
    }

    @Test
    void When_CountWords_Then_ReturnWordCount() {
        long expected = 58164;
        long actual = service.countWords();

        assertEquals(expected, actual);
    }

    @Test
    void When_CountChars_Then_ReturnCharCount() {
        long expected = 339292;
        long actual = service.countChars();

        assertEquals(expected, actual);
    }
}