package com.github.sirnoob97.jdup;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import picocli.CommandLine;

import static org.junit.jupiter.api.Assertions.assertEquals;

class JDupOptionTest {

    private JDupOption options = new JDupOption();

    @Test
    void pathOptionShort() {
        parseOptions(options, "-p", "/path/to/dir");

        assertEquals(options.path, "/path/to/dir");
    }

    @Test
    void pathOptionLong() {
        parseOptions(options, "--path", "/path/to/dir");

        assertEquals(options.path, "/path/to/dir");
    }

    @Test
    void invalidOption() {
        Assertions.assertThrows(CommandLine.UnmatchedArgumentException.class, () -> {
            parseOptions(options, "-x");
        });
    }

    private void parseOptions(JDupOption options, String ... commandLineOptions) {
        new CommandLine(options).parseArgs(commandLineOptions);
    }
}
