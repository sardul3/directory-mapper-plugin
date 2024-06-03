package com.sardul3.maven;

import lombok.extern.slf4j.Slf4j;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@Slf4j
@Mojo(name = "generate-directory-structure")
public class DirectoryMapperMojo extends AbstractMojo {

    @Parameter(property = "outputFile", defaultValue = "folder-structure.txt")
    private String outputFile;

    @Parameter(property = "excludedFolders")
    private String[] excludedFolders;

    @Parameter(property = "packageName")
    private String packageName;

    @Parameter(property = "generateFolders", defaultValue = "false")
    private boolean generateFolders;

    @Parameter(property = "createDirectoryMap", defaultValue = "true")
    private boolean createDirectoryMap;


    @Override
    public void execute() throws MojoExecutionException {
        String rootPath = System.getProperty("user.dir");
        File root = new File(rootPath);
        if (generateFolders && packageName == null) {
            throw new MojoExecutionException("packageName must be specified when generateFolders is true");
        }

        try (FileWriter writer = new FileWriter(outputFile)) {
            if (generateFolders) {
                generateDirectoryStructure(root, packageName, writer);
            }
            if (createDirectoryMap) {
                printDirectoryTree(root, 0, writer);
            }
            getLog().info("Folder structure written to " + outputFile);
        } catch (IOException e) {
            getLog().error("An error occurred while writing folder structure", e);
            throw new MojoExecutionException("Failed to generate directory structure", e);
        }
    }

    private void printDirectoryTree(File folder, int level, FileWriter writer)
            throws IOException {
        Set<String> excludedSet = new HashSet<>(Arrays.asList(excludedFolders));
        if (folder.isDirectory()) {
            writer.write(getIndentString(level) + "+-- " + folder.getName() + "/\n");
            File[] files = folder.listFiles();
            if (files != null) {
                // Sort files and directories alphabetically
                Arrays.sort(files, (f1, f2) -> f1.getName().compareToIgnoreCase(f2.getName()));
                for (File file : files) {
                    // Skip excluded folders
                    if (file.isDirectory() && excludedSet.contains(file.getName())) {
                        writer.write(getIndentString(level + 1) + "+-- " + file.getName() + "/ (skipped)\n");
                    } else {
                        printDirectoryTree(file, level + 1, writer);
                    }
                }
            }
        } else {
            writer.write(getIndentString(level) + "+-- " + folder.getName() + "\n");
        }
    }

    private void generateDirectoryStructure(File root, String packageName, FileWriter writer)
            throws IOException {
        File basePackageDir = new File(root, "src/main/java/" + packageName.replace('.', '/'));
        basePackageDir.mkdirs(); // Ensure base package structure exists
        createDirectory(basePackageDir, "controller", writer);
        createDirectory(basePackageDir, "dto/mapper", writer);
        createDirectory(basePackageDir, "dto/request", writer);
        createDirectory(basePackageDir, "dto/response", writer);
        createDirectory(basePackageDir, "exception/errors", writer);
        createDirectory(basePackageDir, "exception/handlers", writer);
        createDirectory(basePackageDir, "exception/util", writer);
        createDirectory(basePackageDir, "model", writer);
        createDirectory(basePackageDir, "repository", writer);
        createDirectory(basePackageDir, "service", writer);
    }

    private void createDirectory(File root, String relativePath, FileWriter writer)
            throws IOException {
        String[] folders = relativePath.split("/");
        File currentDir = root;
        StringBuilder indent = new StringBuilder();
        for (String folder : folders) {
            currentDir = new File(currentDir, folder);
            currentDir.mkdirs();
            writer.write(indent.toString() + "+-- " + folder + "/\n");
            indent.append("|   ");
        }
    }

    private String getIndentString(int level) {
        return "|   ".repeat(Math.max(0, level));
    }
}
