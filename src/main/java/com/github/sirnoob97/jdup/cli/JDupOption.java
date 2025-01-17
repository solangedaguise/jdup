package com.github.sirnoob97.jdup.cli;

import com.github.sirnoob97.jdup.Sha256Table;
import com.github.sirnoob97.jdup.Visitor;
import picocli.CommandLine.Command;
import picocli.CommandLine.Parameters;
import picocli.CommandLine.Option;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.function.Predicate;

@Command(headerHeading = "Usage:%n%n",
    header = "Duplicate files finder.",
    synopsisHeading = "%n",
    parameterListHeading = "%nParameters:%n%n",
    optionListHeading = "%nOptions%n%n",
    descriptionHeading = "%nDescription:%n%n",
    description = "Find duplicate files based on SHA256 hash, file name is ignored.",
    sortOptions = false,
    abbreviateSynopsis = true)
public class JDupOption implements Callable<Integer> {

  private static final String EMPTY_FILE_HASH = "e3b0c44298fc1c149afbf4c8996fb92427ae41e4649b934ca495991b7852b855";
  private static final Predicate<Map.Entry<String, List<String>>> allowEmptyFiles = entry -> entry.getKey().equals(EMPTY_FILE_HASH);
  private static final Predicate<Map.Entry<String, List<String>>> denyEmptyFiles = entry -> !entry.getKey().equals(EMPTY_FILE_HASH);

  @Parameters(arity = "1",
      converter = PathConverter.class,
      description = "Path to check for duplicates.")
  private Path path;


  @Option(names = { "-i", "--ignore" },
      arity = "0..1",
      split = ",",
      paramLabel = "DIR",
      description = "Comma separated list of directories to ignore.")
  private Set<String> ignore = new HashSet<>();

  @Option(names = { "-e", "--empty" }, description = "Only show empty files.")
  private boolean emptyFiles;

  @Option(names = { "-h", "--help" }, usageHelp = true, description = "display this help message")
  private boolean usageHelpRequested;

  public Path getPath() {
    return path;
  }

  public Set<String> getIgnore() {
    return ignore;
  }

  public boolean isEmptyFiles() {
    return emptyFiles;
  }

  public boolean isUsageHelpRequested() {
    return usageHelpRequested;
  }

  private Predicate<Map.Entry<String, List<String>>> getEmptyFilesPredicate() {
    return emptyFiles ? allowEmptyFiles : denyEmptyFiles;
  }

  @Override
  public Integer call() {
    if (!Files.isDirectory(path) || !Files.exists(path)) {
      System.err.println("The path must be an existing directory!!");
      System.exit(1);
    }
    var files = Visitor.visitRootDir(visitor ->
        visitor.root(path)
            .files(new HashSet<>())
            .ignore(ignore));

    Sha256Table.getDups(files)
        .entrySet().stream()
        .filter(entry -> entry.getValue().size() > 1)
        .filter(getEmptyFilesPredicate())
        .peek(entry -> System.out.format("%nFiles that share the hash: %s%n", entry.getKey()))
        .map(Map.Entry::getValue)
        .flatMap(List::stream)
        .forEach(System.out::println);

    return 0;
  }
}
