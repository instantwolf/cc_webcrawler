package CCWebcrawler;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class MarkDownWriter {

    public static void printMarkDownToFile(String markdown) {
        String fileName = generateMarkDownFileName();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            writer.write(markdown);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String generateMarkDownFileName(){
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedTime = formatter.format(now);
        return formattedTime + "crawling-report.md";
    }
}
