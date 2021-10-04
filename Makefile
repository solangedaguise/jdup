.PHONY: clean test package build install

test: clean
	@gradle test

clean:
	@gradle clean compileJava

package: clean
	@gradle compileJava shadowJar

# TODO: this task is for build a native image
build: package

# TODO: store the final native image into ~/.local/bin
install: build
