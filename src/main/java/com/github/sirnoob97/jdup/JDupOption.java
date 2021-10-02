package com.github.sirnoob97.jdup;

import picocli.CommandLine.Command;
import picocli.CommandLine.Parameters;

import java.nio.file.Path;

@Command
public class JDupOption {

    @Parameters(defaultValue = "${user.dir}", description = "Path to check for duplicates, default: ${DEFAULT_VALUE}.")
    Path path;
}
