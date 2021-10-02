package com.github.sirnoob97.jdup.cli;

import picocli.CommandLine.Command;
import picocli.CommandLine.Parameters;
import picocli.CommandLine.Option;

import java.nio.file.Path;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Predicate;

@Command
public class JDupOption {

    @Parameters(defaultValue = "${user.dir}", description = "Path to check for duplicates, default: ${DEFAULT_VALUE}.")
    private Path path;

    @Option(names = {"-i", "--ignore"}, arity = "0..1", split = ",", description = "Comma separated list of directories to ignore.")
    private Set<String> ignore = new HashSet<>();
    
    public Path getPath() {
        return path;
    }

    public Set<String> getIgnore() {
        return ignore;
    }
}
