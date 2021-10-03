package com.github.sirnoob97.jdup;

import com.github.sirnoob97.jdup.cli.JDupOption;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import picocli.CommandLine;
import picocli.CommandLine.ParameterException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static picocli.CommandLine.MissingParameterException;

class JDupOptionTest {

  private static JDupOption options;

  @BeforeEach
  void init() {
    options = new JDupOption();
  }

  @Test
  void pathParameter() {
    var dir = System.getenv("HOME");
    parseOptions(dir);

    assertEquals(options.getPath().toString(), dir);
  }

  @Test
  void noParameter() {
    assertThrows(ParameterException.class, () -> parseOptions(""));
  }

  @Test
  void invalidOption() {
    assertThrows(MissingParameterException.class, () -> parseOptions("-x"));
  }

  private void parseOptions(String... commandLineOptions) {
    new CommandLine(options).parseArgs(commandLineOptions);
  }
}
