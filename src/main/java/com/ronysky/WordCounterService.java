package com.ronysky;

import java.util.StringTokenizer;

public class WordCounterService {

    private final String text;

    public WordCounterService(String text) {
        this.text = text;
    }

    public long countBytes() {
        return text.getBytes().length;
    }

    public long countLines() {
        return text.split("\n").length;
    }

    public long countWords() {
        return new StringTokenizer(text).countTokens();
    }

    public long countChars() {
        return text.length();
    }
}
