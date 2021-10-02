/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package com.github.sirnoob97.jdup;

import com.github.sirnoob97.jdup.cli.JDupOption;
import picocli.CommandLine;

import java.nio.file.Files;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

public class App {

  public static void main(String[] args) {
    JDupOption options = new JDupOption();
    new CommandLine(options).parseArgs(args);
    var path = options.getPath();

    if (!Files.isDirectory(path) || !Files.exists(path)) {
      System.err.println("The path must be an existing directory!!");
      System.exit(1);
    }
    var files = Visitor.visitRootDir(visitor ->
        visitor.root(path)
            .files(new HashSet<>())
            .ignore(options.getIgnore()));

    Sha256Table.getDups(files)
        .entrySet().stream()
        .filter(entry -> entry.getValue().size() > 1)
        .filter(options.getEmptyFilesPredicate())
        .peek(entry -> System.out.format("%nFiles that share the hash: %s%n", entry.getKey()))
        .map(Map.Entry::getValue)
        .flatMap(List::stream)
        .forEach(System.out::println);
  }

}
