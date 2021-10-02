package com.github.sirnoob97.jdup;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import picocli.CommandLine;

import static org.junit.jupiter.api.Assertions.assertEquals;

class JDupOptionTest {

    private JDupOption options = new JDupOption();

    @Test
    void pathParameter() {
        var currDir = System.getProperty("user.dir");
        parseOptions(options, currDir);

        assertEquals(options.path.toString(), currDir);
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
