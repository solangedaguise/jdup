package com.github.sirnoob97.jdup;

import org.apache.commons.codec.digest.DigestUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class Sha256Table {
  
  private final static Map<String, List<String>> dups = new HashMap<>();

  public static Map<String, List<String>> getDups(Set<Path> files) {
    for (var file : files) {
      var hash = hash(file);
      put(hash, file);
    }

    return dups;
  }

  public static String hash(Path file) {
    var hash = "";
    try (var inputStream = Files.newInputStream(file)) {
      hash = DigestUtils.sha256Hex(inputStream);
    } catch (IOException e) {
      System.out.println(e.getMessage());
      System.exit(1);
    }

    return hash;
  }

  public static void put(String hash, Path file) {
    if (dups.containsKey(hash)) {
      dups.get(hash).add(file.toString());
      return;
    }
    if (!dups.containsKey(hash)) {
      var files = new ArrayList<String>();
      files.add(file.toString());

      dups.put(hash, files);
    }
  }
}
