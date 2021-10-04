/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package com.github.sirnoob97.jdup;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

class AppTest {
  private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
  
  @BeforeEach
  public void setUpStreams() {
	  System.setOut(new PrintStream(outContent));
  }
	
  @Test
  void appHasAGreeting() {
  }
  
//  @Test
//  void fileNotExist(@TempDir Path tempDir) {
//	  
//	  public final ExpectedSystemExit exit = ExpectedSystemExit.none();
//	  
//	  String[] args = new String[1];
//	  args[0] = "existingDir";
//	  
//	  SystemLambda.catchSystemExit() -> {
//		  
//	  }
//	  App.main(args);
//	  assertEquals("The path must be an existing direcotry!!", outContent.toString());
//  
//  }
  
  //@Test
  void correctEmptyHash(@TempDir Path tempDir) {
	  try {
		  //Create temp empty file
		Path myTempDir = Files.createTempDirectory("temp");
		String path = myTempDir.toString();
		String[] args  = new String[1];
		args[0] = path;
		
		//Methode I want to test
		App.main(args);
		String t = outContent.toString();
		
		//Checking if console is empty
		assertEquals("", outContent.toString());
	  } catch (IOException e) {
			e.printStackTrace();
		}
  }
  
  @Test
  void correctHash(@TempDir Path tempDir) {
	  try {
		
		  //Create temp empty file
		Path myTempDir = Files.createTempDirectory("Dir");
		Path myTempFile = Files.createTempFile(myTempDir, "Temp", "file");
		List<String> content = Arrays.asList("Line 1", "Line 2", "Line 3");
		Files.write(myTempFile, "Hello World\n".getBytes(StandardCharsets.UTF_8));
		String path = myTempDir.toString();
		String[] args  = new String[1];
		args[0] = path;
		
		//Methode I want to test
		App.main(args);
		String t = outContent.toString();
		
		//Checking if console is empty
		assertEquals("", outContent.toString());
	  } catch (IOException e) {
			e.printStackTrace();
		}
  }
}
