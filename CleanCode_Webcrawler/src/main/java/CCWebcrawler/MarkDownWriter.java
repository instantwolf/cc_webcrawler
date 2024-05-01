package CCWebcrawler;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;


public class MarkDownWriter {
    private MarkDownGenerator generator = new MarkDownGenerator();
    private String content = "";

    private static String filePath = "output.md";

    public void printToMDFile(String inputUrl, int targetDepth, List<Website> crawledWebsites) {
        content = generator.generateMarkDown(inputUrl, targetDepth, crawledWebsites);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write(content);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
