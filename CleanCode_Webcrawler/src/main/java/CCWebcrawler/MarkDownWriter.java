package CCWebcrawler;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class MarkDownWriter {

    private static final String successMessagePrefix = "Markdown has been written to: ";
    private static final String failureMessagePrefix = "An error has occurred while writing the markdown to the file: ";

    private static final String markDownFilePathPrefix = "CleanCode_WebCrawler/src/main/java/resources/";

    public static void printMarkDownToFile(String markdown) {
        String fileName = getPathMarkdownFileDestination().concat(generateMarkDownFileName());

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

    private static String getPathMarkdownFileDestination(){
        Path absolutePath = Paths.get(markDownFilePathPrefix).toAbsolutePath();
        if(Files.exists(absolutePath))
            return absolutePath.toString().concat("/");
        else
            return "";
    }

    private static String generateMarkDownFileName(){
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH_mm_ss_SSSSSS");
        String formattedTime = formatter.format(now);
        return "crawling-report"+"_"+formattedTime+".md";
    }
}
