package com.github.sirnoob97.jdup.cli;

import picocli.CommandLine;
import picocli.CommandLine.IParameterExceptionHandler;
import picocli.CommandLine.Model.CommandSpec;
import picocli.CommandLine.ParameterException;
import picocli.CommandLine.UnmatchedArgumentException;

import java.io.PrintWriter;

public class InvalidInputHandler implements IParameterExceptionHandler {

  public InvalidInputHandler() {
  }

  @Override
  public int handleParseException(ParameterException ex, String[] args) throws Exception {
    CommandLine cmd = ex.getCommandLine();
    PrintWriter err = cmd.getErr();

    // if tracing at DEBUG level, show the location of the issue
    if ("DEBUG".equalsIgnoreCase(System.getProperty("picocli.trace"))) {
      err.println(cmd.getColorScheme().stackTraceText(ex));
    }

    err.format("%s%n%n", cmd.getColorScheme().errorText(ex.getMessage())); // bold red
    UnmatchedArgumentException.printSuggestions(ex, err);
    err.format("Usage:%n%s%n", cmd.getHelp().fullSynopsis());

    CommandSpec spec = cmd.getCommandSpec();
    err.format("Try '%s --help' for more information.%n", spec.qualifiedName());

    return cmd.getExitCodeExceptionMapper() != null
        ? cmd.getExitCodeExceptionMapper().getExitCode(ex)
        : spec.exitCodeOnInvalidInput();

  }
}
