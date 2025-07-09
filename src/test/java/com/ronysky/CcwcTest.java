package com.ronysky;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CcwcTest {
    private static final String FILENAME = "src/main/resources/test.txt";
    private ByteArrayOutputStream output;

    @BeforeEach
    void setup() {
        output = new ByteArrayOutputStream();
        System.setOut(new PrintStream(output));
    }

    @Test
    void When_FileDoesNotExist_Then_ThrowException() {
        assertThrows(IllegalArgumentException.class, () -> Ccwc.main(new String[]{"test.tx"}));
    }

    @Test
    void When_OnlyByteFlagIsPresent_Then_OnlyPrintByteCount() {
        Ccwc.main(new String[]{"-c", FILENAME});
        assertEquals(formatOutputString("342190\t"), output.toString());
    }

    @Test
    void When_OnlyLineFlagIsPresent_Then_OnlyPrintLineCount() {
        Ccwc.main(new String[]{"-l", FILENAME});
        assertEquals(formatOutputString("7145\t" ), output.toString());
    }

    @Test
    void When_OnlyWordFlagIsPresent_Then_OnlyPrintWordCount() {
        Ccwc.main(new String[]{"-w", FILENAME});
        assertEquals(formatOutputString("58164\t" ), output.toString());
    }

    @Test
    void When_OnlyCharFlagIsPresent_Then_OnlyPrintCharCount() {
        Ccwc.main(new String[]{"-m", FILENAME});
        assertEquals(formatOutputString("339292\t" ), output.toString());
    }

    @Test
    void When_MultipleFlagsArePresent_Then_PrintEveryTrueFlagCount() {
        Ccwc.main(new String[]{"-c", "-l", "-m", FILENAME});
        assertEquals(formatOutputString("7145\t342190\t339292\t" ), output.toString());
    }

    @Test
    void When_NoFlagsArePresent_ThenPrintLineWordCharCount() {
        Ccwc.main(new String[]{FILENAME});
        assertEquals(formatOutputString("7145\t58164\t342190\t"), output.toString());
    }

    @Test
    void When_NoFilenameIsPresent_Then_ReadFromSystemIn() {
        System.setIn(new ByteArrayInputStream("ABCDE\nF".getBytes()));
        Ccwc.main(new String[]{"-l"});
        assertEquals("2\n", output.toString());
    }

    @Test
    void When_NoFilenameIsPresent_And_SystemInIsEmpty_Then_ThrowException() {
        assertThrows(IllegalArgumentException.class, () -> Ccwc.main(new String[]{"-c"}));
    }

    private String formatOutputString(String prefix) {
        return prefix + FILENAME + "\n";
    }
}
