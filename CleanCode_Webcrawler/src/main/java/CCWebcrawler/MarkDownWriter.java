package CCWebcrawler;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class MarkDownWriter {

    private static final String successMessagePrefix = "Markdown has been written to: ";
    private static final String failureMessagePrefix = "An error has occurred while writing the markdown to the file: ";

    public static void printMarkDownToFile(String markdown) {
        String fileName = generateMarkDownFileName();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            writer.write(markdown);
            writeInfoMessage(successMessagePrefix, fileName);
        } catch (IOException e) {
            writeInfoMessage(failureMessagePrefix, e.getMessage());
        }
    }

    private static void writeInfoMessage(String prefix, String addendum){
        System.out.println(prefix+addendum);
    }

    private static String generateMarkDownFileName(){
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedTime = formatter.format(now);
        return formattedTime + "crawling-report.md";
    }
}
