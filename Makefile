# Makefile for LibraryAnalyzer

# Java Compiler
JAVAC = javac
JAVA = java

# Main class name
MAIN = LibraryAnalyzer

# Source files
SOURCES = $(MAIN).java

# Class files
CLASSES = $(MAIN).class

# Default target: compile
all: $(CLASSES)

$(CLASSES): $(SOURCES)
	$(JAVAC) $(SOURCES)

# Run the program with a folder argument
run:
	$(JAVA) $(MAIN) ./sample_libs

# Clean up class files
clean:
	rm -f *.class
