# Directory Mapper Maven Plugin

The Directory Mapper Maven Plugin is a tool that generates a text representation of your project's directory structure. It allows you to specify which folders to exclude from the output.

## Features
- Generates a directory tree structure and writes it to a specified file.
- Allows excluding specific folders from the output.

## Requirements
- Maven 3.0+
- JDK 8+

## Usage

To use this plugin, include it in your `pom.xml` file:

```xml
<plugin>
  <groupId>io.github.sardul3</groupId>
  <artifactId>directory-mapper-plugin</artifactId>
  <version>1.0.0</version>
  <configuration>
    <outputFile>folder-structure.txt</outputFile>
    <excludedFolders>
      <excludedFolder>.git</excludedFolder>
      <excludedFolder>.idea</excludedFolder>
      <excludedFolder>target</excludedFolder>
      <excludedFolder>.mvn</excludedFolder>
    </excludedFolders>
  </configuration>
  <executions>
    <execution>
      <goals>
        <goal>generate-directory-structure</goal>
      </goals>
    </execution>
  </executions>
</plugin>
```

## Configuration Parameters

- **outputFile**: The file where the directory structure will be written. Default is `folder-structure.txt`.
- **excludedFolders**: A list of folder names to exclude from the directory structure.

## Running the Plugin

To run the plugin, use the following Maven command:

```sh
mvn io.github.sardul3:directory-mapper-plugin:1.0.0:generate-directory-structure
```

This will generate a file named folder-structure.txt in your project's root directory with the directory tree structure.
```
+-- my-project/
|   +-- src/
|   |   +-- main/
|   |   |   +-- java/
|   |   |   +-- resources/
|   +-- target/ (skipped)
|   +-- .git/ (skipped)
|   +-- .idea/ (skipped)
|   +-- .mvn/ (skipped)
|   +-- pom.xml
|   +-- README.md
```
