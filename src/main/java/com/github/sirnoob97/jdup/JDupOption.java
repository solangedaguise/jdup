package com.github.sirnoob97.jdup;

import picocli.CommandLine;

public class JDupOption {
    @CommandLine.Option(names = {"-p", "--path"}, description = "Path to check for duplicates, default is current directory")
    String path;
}
