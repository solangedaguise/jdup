INSTALL_PREFIX=$$HOME/.local/bin
JAR=./build/libs/jdup.jar
NATIVE_IMAGE=./bin/jdup
VERSION=$(shell git describe --tags --abbrev=0)

.PHONY: clean test package build install

clean:
	@gradle clean compileJava

test: clean
	@gradle test

package: clean
	@gradle compileJava shadowJar

# TODO: this task is for build a native image
build: package

# TODO: store the final native image into ~/.local/bin
install: build
