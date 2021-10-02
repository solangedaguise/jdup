package com.github.sirnoob97.jdup;

import picocli.CommandLine.Command;
import picocli.CommandLine.Parameters;
import picocli.CommandLine.Option;

import java.nio.file.Path;
import java.util.HashSet;
import java.util.Set;

@Command
public class JDupOption {

    @Parameters(defaultValue = "${user.dir}", description = "Path to check for duplicates, default: ${DEFAULT_VALUE}.")
    Path path;

    @Option(names = {"-i", "--ignore"}, arity = "0..1", split = ",", description = "Comma separated list of directories to ignore.")
    Set<String> ignore = new HashSet<>();
}
