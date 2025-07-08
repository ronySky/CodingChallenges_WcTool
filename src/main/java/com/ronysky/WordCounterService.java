package com.ronysky;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.StringTokenizer;
import java.util.stream.Stream;

public class WordCounterService {

    private final Path path;

    public WordCounterService(Path path) {
        this.path = path;
    }

    public long countBytes() throws IOException {
        return Files.readAllBytes(path).length;
    }

    public long countLines() throws IOException {
        final Stream<String> lines = Files.lines(path);
        try (lines) {
            return lines.count();
        }
    }

    public long countWords() throws IOException {
        final Stream<String> lines = Files.lines(path);
        try (lines) {
            return Long.valueOf(lines
                    .map(line -> new StringTokenizer(line).countTokens())
                    .reduce(0, Integer::sum));
        }
    }

    public long countChars() throws IOException {
        return Files.readString(path).length();
    }
}
