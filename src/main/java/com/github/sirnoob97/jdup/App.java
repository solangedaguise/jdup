/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package com.github.sirnoob97.jdup;

public class App {
  }

  public static String hash(Path file) {
    var hash = new String();
    try (var inputStream = Files.newInputStream(file)) {
      hash = DigestUtils.sha256Hex(inputStream);
    } catch (IOException e) {
      e.printStackTrace();
    }

    return hash;
  }

  public static void put(String hash, Path file) {
    if (hashes.containsKey(hash)) {
      hashes.get(hash).add(file.toString());
      return;
    }
    if (!hashes.containsKey(hash)) {
      var files = new ArrayList<String>();
      files.add(file.toString());

      hashes.put(hash, files);
    }
  }
}
