import CCWebcrawler.MarkdownFileWriter;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.Optional;
import java.util.stream.Stream;

import static org.junit.Assert.*;

public class MarkdownFileWriterTest {

    private static String directoryPath;

    @BeforeAll
    public static void initDirectoryPath(){
        directoryPath = Paths.get("../../target").toAbsolutePath().toString();
    }

    @Test
    public void newFileCreatedTest() {
        long fileCountBefore = countFiles(directoryPath);

        MarkdownFileWriter.printMarkDownToFile("Test");

        assertEquals(fileCountBefore, countFiles(directoryPath));
    }

    @Test
    public void writeToFileTest() {
        MarkdownFileWriter.printMarkDownToFile("Test");
        try {
            Optional<Path> latestFile = findLatestFile(directoryPath);
            String fileOutput = readMDFile(latestFile.get().toString());
            assertEquals("Test", fileOutput);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void writeToFileTestWithNewLine() {
        MarkdownFileWriter.printMarkDownToFile("Test\nTest");
        try {
            Optional<Path> latestFile = findLatestFile(directoryPath);
            String fileOutput = readMDFile(latestFile.get().toString());
            assertEquals("Test\nTest", fileOutput);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    // Helper Methods

    private static long countFiles(String path) {
        try (Stream<Path> files = Files.walk(Paths.get(path))) {
            return files.filter(Files::isRegularFile).count();
        } catch (IOException e) {
            e.printStackTrace();
            return -1;
        }
    }

    private static String readMDFile(String file) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                contentBuilder.append(line).append(System.lineSeparator());
            }
        }
        return contentBuilder.toString();
    }

    // Finds and return last modified file
    private static Optional<Path> findLatestFile(String directoryPath) throws IOException {
        try (var paths = Files.walk(Paths.get(directoryPath))) {
            return paths.filter(Files::isRegularFile)
                    .max(Comparator.comparingLong(p -> p.toFile().lastModified()));
        }
    }
}
