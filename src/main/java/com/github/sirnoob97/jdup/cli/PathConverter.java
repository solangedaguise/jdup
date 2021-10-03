package com.github.sirnoob97.jdup.cli;

import picocli.CommandLine.ITypeConverter;
import picocli.CommandLine.TypeConversionException;

import java.nio.file.Path;

/**
 * PathConverter this class is necessary because @{link java.nio.file.Path#of(java.lang.String, java.lang.String...) Path.of("")}
 * returns the current path of the and that can be annoying.
 */
public class PathConverter implements ITypeConverter<Path> {

  public PathConverter() {
  }

  @Override
  public Path convert(String value) {
    if (value == null || value.isEmpty()) {
      throw new TypeConversionException("Missing required parameter: '<path>'");
    }
    return Path.of(value);
  }
}
