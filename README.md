# Directory Mapper Maven Plugin

The Directory Mapper Maven Plugin is a versatile tool that generates a text representation of your project's directory structure. It allows for both directory structure generation and folder creation based on a specified package name. This plugin is particularly useful for documenting project structures and ensuring consistency across multiple projects.

## Features
- Generates a directory tree structure and writes it to a specified file.
- Allows excluding specific folders from the output.
- Optionally generates a predefined folder structure within a specified package.
- Can be configured to run automatically during the Maven build lifecycle.

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
    <generateFolders>true</generateFolders>
    <printDirectory>true</printDirectory>
    <packageName>io.github.sardul3.course_service</packageName>
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

### Configuration Parameters

- **outputFile**: The file where the directory structure will be written. Default is `folder-structure.txt`.
- **excludedFolders**: A list of folder names to exclude from the directory structure.
- **generateFolders**: Flag to determine if the plugin should generate a predefined folder structure within the specified package. Default is `false`.
- **printDirectory**: Flag to determine if the plugin should print the directory structure to the specified output file. Default is `false`.
- **packageName**: The base package name for generating the folder structure. This is required if `generateFolders` is `true`.

### Running the Plugin

#### Command Line

To run the plugin, use the following Maven command:

```sh
mvn io.github.sardul3:directory-mapper-plugin:1.0.0:generate-directory-structure
```
This will generate a file named `folder-structure.txt` in your project's root directory with the directory tree structure:
```
+-- my-project/
| +-- src/
| | +-- main/
| | | +-- java/
| | | +-- resources/
| +-- target/ (skipped)
| +-- .git/ (skipped)
| +-- .idea/ (skipped)
| +-- .mvn/ (skipped)
| +-- pom.xml
| +-- README.md
```

### Maven Build Lifecycle

To integrate the plugin into the Maven build lifecycle, configure it in the `executions` section of your `pom.xml`. This will ensure the plugin runs automatically during the `package` phase or any other specified phase:

```xml
<plugin>
  <groupId>io.github.sardul3</groupId>
  <artifactId>directory-mapper-plugin</artifactId>
  <version>1.0.0</version>
  <executions>
    <execution>
      <id>generate-directory-structure</id>
      <phase>package</phase>
      <goals>
        <goal>generate-directory-structure</goal>
      </goals>
      <configuration>
        <generateFolders>true</generateFolders>
        <printDirectory>true</printDirectory>
        <packageName>com.example.app</packageName>
      </configuration>
    </execution>
  </executions>
</plugin>
```

### Example Usage Scenarios

#### Generating Only the Directory Structure

If you only want to generate the directory structure without creating any folders, set `generateFolders` to `false` and `printDirectory` to `true`:

```xml
<plugin>
  <groupId>io.github.sardul3</groupId>
  <artifactId>directory-mapper-plugin</artifactId>
  <version>1.0.0</version>
  <configuration>
    <generateFolders>false</generateFolders>
    <printDirectory>true</printDirectory>
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

### Generating Folders and Printing Directory Structure
To generate the folders and print the directory structure, set both generateFolders and printDirectory to true:
```xml
<plugin>
  <groupId>io.github.sardul3</groupId>
  <artifactId>directory-mapper-plugin</artifactId>
  <version>1.0.0</version>
  <configuration>
    <generateFolders>true</generateFolders>
    <printDirectory>true</printDirectory>
    <packageName>com.example.app</packageName>
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

