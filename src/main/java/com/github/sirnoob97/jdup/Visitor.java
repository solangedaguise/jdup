package com.github.sirnoob97.jdup;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitor;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Set;

import static java.nio.file.FileVisitResult.CONTINUE;

public class Visitor {
  
  public static FileVisitor<Path> getFileVisitor(Set<Path> files, Set<String> ignore) {
    return new FileVisitor<>() {
      @Override
      public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
        return ignore.contains(dir.getFileName().toString()) ? FileVisitResult.SKIP_SIBLINGS : CONTINUE;
      }

      // TODO: Fix the recursion problem, when the file is a directory the children are never visited
      @Override
      public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        if(!Files.isDirectory(file)) files.add(file);
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