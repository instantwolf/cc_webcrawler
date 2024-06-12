package CCWebcrawler.InputHandler.Console;

import CCWebcrawler.InputHandler.InputReader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ConsoleInputReader implements InputReader {


    @Override
    public String readUrls() {
        System.out.println(ConsoleInputHandlerTexts.URL_PROMPT);
        return readInputLine();
    }

    @Override
    public String readDepth() {
        System.out.println(ConsoleInputHandlerTexts.TARGET_DEPTH_PROMPT);
        return readInputLine();
    }


    private static String readInputLine() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String var;
        try {
            var = reader.readLine();
        } catch (IOException e) {
            System.out.println(ConsoleInputHandlerTexts.ERROR_READING_INPUT);
            throw new RuntimeException(e);
        }
        return var.trim();
    }

}
