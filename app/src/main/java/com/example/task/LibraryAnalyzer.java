package com.example.task;

import java.io.File;
import java.util.*;

public class LibraryAnalyzer {

    private static final String[] ARCHITECTURES = {
            "armeabi-v7a", "arm64-v8a", "x86", "x86-64", "mips"
    };

    private static final Map<String, String> libraryMap = new LinkedHashMap<>();

    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Usage: java LibraryAnalyzer <folder-path>");
            return;
        }

        File folder = new File(args[0]);
        if (!folder.exists() || !folder.isDirectory()) {
            System.out.println("Invalid folder path.");
            return;
        }

        List<File> libFiles = new ArrayList<>();
        scanForLibraries(folder, libFiles);

        for (File lib : libFiles) {
            String arch = detectArchitecture(lib.getName());
            if (arch != null) {
                libraryMap.put(lib.getName(), arch);
            }
        }

        printResults();
    }

    private static void scanForLibraries(File dir, List<File> libFiles) {
        File[] files = dir.listFiles();
        if (files == null) return;

        for (File file : files) {
            if (file.isDirectory()) {
                scanForLibraries(file, libFiles);
            } else if (file.getName().endsWith(".so")) {
                libFiles.add(file);
            }
        }
    }

    private static String detectArchitecture(String fileName) {
        for (String arch : ARCHITECTURES) {
            if (fileName.contains(arch)) {
                return arch;
            }
        }

        // Use naming heuristics for common patterns
        if (fileName.matches(".*v7.*")) return "armeabi-v7a";
        if (fileName.matches(".*v8.*|.*arm64.*")) return "arm64-v8a";
        if (fileName.matches(".*x86_64.*|.*x64.*")) return "x86-64";
        if (fileName.matches(".*x86.*")) return "x86";
        if (fileName.matches(".*mips.*")) return "mips";

        return null; // unknown architecture
    }

    private static void printResults() {
        System.out.println("Total number of libraries: " + libraryMap.size());
        System.out.printf("%-25s %-15s%n", "File", "ArchType");
        System.out.println("========================= ================");

        for (Map.Entry<String, String> entry : libraryMap.entrySet()) {
            System.out.printf("%-25s %-15s%n", entry.getKey(), entry.getValue());
        }
    }
}
