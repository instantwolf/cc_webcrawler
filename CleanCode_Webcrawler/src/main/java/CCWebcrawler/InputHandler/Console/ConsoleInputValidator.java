package CCWebcrawler.InputHandler.Console;

import CCWebcrawler.InputHandler.InputValidator;
import CCWebcrawler.URLValidator;

public class ConsoleInputValidator implements InputValidator {


    @Override
    public boolean isValidURL(String input) {
        return URLValidator.isValidURL(input);
    }


    @Override
    public boolean isValidDepth(String targetDepth) {
        if (isNumeric(targetDepth) || Integer.parseInt(targetDepth) >= 1)
            return true;
        else
            return false;
    }


    private static boolean isNumeric(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }


}
