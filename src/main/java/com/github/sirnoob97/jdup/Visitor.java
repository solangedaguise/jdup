package com.github.sirnoob97.jdup;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Set;
import java.util.function.Consumer;

import static java.nio.file.FileVisitResult.CONTINUE;
import static java.nio.file.FileVisitResult.SKIP_SUBTREE;

public class Visitor {

  private Path root;
  private Set<Path> files;
  private Set<String> ignore;

  private Visitor() {
  }

  public Visitor root(Path root) {
    this.root = root;
    return this;
  }

  public Visitor files(Set<Path> files) {
    this.files = files;
    return this;
  }

  public Visitor ignore(Set<String> ignore) {
    this.ignore = ignore;
    return this;
  }

  public static Set<Path> visitRootDir(Consumer<Visitor> consumer) {
    var visitor = new Visitor();
    consumer.accept(visitor);

    try {
      Files.walkFileTree(visitor.root, visitor.getFileVisitor());
    } catch (IOException e) {
      System.err.println(e.getMessage());
      System.exit(1);
    }

    return visitor.files;
  }

  private FileVisitor<Path> getFileVisitor() {
    return new SimpleFileVisitor<>() {
      @Override
      public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
        return !ignore.isEmpty() && ignore.contains(dir.getFileName().toString()) ? SKIP_SUBTREE : CONTINUE;
      }

      @Override
      public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        return CONTINUE;
      }

      @Override
      public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
        throw exc;
      }

      @Override
      public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
        if (exc == null) return CONTINUE;
        throw exc;
      }
    };
  }
}