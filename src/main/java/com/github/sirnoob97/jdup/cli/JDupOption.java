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

  private static final String EMPTY_FILE_HASH = "e3b0c44298fc1c149afbf4c8996fb92427ae41e4649b934ca495991b7852b855";
  private static final Predicate<Map.Entry<String, List<String>>> allowEmptyFiles = entry -> entry.getKey().equals(EMPTY_FILE_HASH);
  private static final Predicate<Map.Entry<String, List<String>>> denyEmptyFiles = entry -> !entry.getKey().equals(EMPTY_FILE_HASH);

  @Parameters(defaultValue = "${user.dir}", description = "Path to check for duplicates, default: ${DEFAULT_VALUE}.")
  private Path path;

  @Option(names = { "-i", "--ignore" }, arity = "0..1", split = ",", description = "Comma separated list of directories to ignore.")
  private Set<String> ignore = new HashSet<>();

  @Option(names = { "-e", "--empty" }, description = "Only show empty files.")
  private boolean emptyFiles;

  public Path getPath() {
    return path;
  }

  public Set<String> getIgnore() {
    return ignore;
  }

  public Predicate<Map.Entry<String, List<String>>> getEmptyFilesPredicate() {
    return emptyFiles ? allowEmptyFiles : denyEmptyFiles;
  }
}
