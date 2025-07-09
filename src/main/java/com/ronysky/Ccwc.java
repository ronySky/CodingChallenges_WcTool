package com.ronysky;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Ccwc {

    private WordCounterService service;
    private WordCounterParams params;

    public static void main(String[] args) {
        Ccwc ccwc = new Ccwc();
        ccwc.params = ccwc.parseArgs(args);
        ccwc.service = new WordCounterService(ccwc.params.text);
        String result = ccwc.count(ccwc.params);
        System.out.println(result);
    }

    private WordCounterParams parseArgs(String[] args) {
        List<String> argsList = new ArrayList<>(Arrays.asList(args));
        params = new WordCounterParams();
        parseFlags(argsList, params);
        parseText(argsList, params);

        return params;
    }

    private void parseFlags(List<String> argsList, WordCounterParams params) {
        if (argsList.contains("-c")) {
            params.countBytes = true;
            argsList.remove("-c");
        }
        if (argsList.contains("-m")) {
            params.countChars = true;
            argsList.remove("-m");
        }
        params.countLines = argsList.removeIf("-l"::equals);
        params.countWords = argsList.removeIf("-w"::equals);
        //If no arguments are provided, all of them expect countChars are set to true
        if (!params.countBytes && !params.countChars && !params.countLines && !params.countWords) {
            params.countBytes = params.countLines = params.countWords = true;
        }
    }

    private void parseText(List<String> argsList, WordCounterParams params) {
        if (argsList.size() > 1) {
            throw new IllegalArgumentException("Unknown parameters:" + String.join(" ", argsList));
        }

        if (argsList.isEmpty()) {
            try {
                params.text = new String(System.in.readAllBytes());
            } catch (IOException e) {
                throw new RuntimeException("Error reading from stdin");
            }
        } else {
            String filename = argsList.get(0);
            Path path = Path.of(filename);
            if (!Files.exists(path)) {
                throw new IllegalArgumentException("Filename %s not found".formatted(filename));
            }
            try {
                params.text = Files.readString(path);
                params.filename = filename;
            } catch (IOException e) {
                throw new RuntimeException("Error reading from" + filename);
            }
        }
    }

    private String count(WordCounterParams params) {
        List<String> result = new ArrayList<>();
        if (params.countLines) {
            result.add(String.valueOf(service.countLines()));
        }
        if (params.countWords) {
            result.add(String.valueOf(service.countWords()));
        }
        if (params.countBytes) {
            result.add(String.valueOf(service.countBytes()));
        }
        if (params.countChars) {
            result.add(String.valueOf(service.countChars()));
        }
        if (params.filename != null) {
            result.add(params.filename);
        }
        return String.join("\t", result);
    }

    private static class WordCounterParams {
        boolean countBytes;
        boolean countChars;
        boolean countLines;
        boolean countWords;
        String filename;
        String text;
    }
}