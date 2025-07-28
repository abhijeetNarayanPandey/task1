
---

## ✅ `Makefile`

```makefile
# Makefile for LibraryAnalyzer project

# Java commands
JAVAC = javac
JAVA = java

# Source and output folders
SRC = app/src/main/java
OUT = out

# Full path to main source file
MAIN_SRC = $(SRC)/com/example/task/LibraryAnalyzer.java

# Main class with full package name
MAIN_CLASS = com.example.task.LibraryAnalyzer

# Compile the program
compile:
	mkdir -p $(OUT)
	$(JAVAC) -d $(OUT) $(MAIN_SRC)

# Run the program (default input folder is sample_libs)
run: compile
	$(JAVA) -cp $(OUT) $(MAIN_CLASS) sample_libs

# Clean compiled files
clean:
	rm -rf $(OUT)
