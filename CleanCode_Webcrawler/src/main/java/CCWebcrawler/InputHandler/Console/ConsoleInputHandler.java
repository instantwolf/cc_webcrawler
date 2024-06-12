package CCWebcrawler.InputHandler.Console;

import CCWebcrawler.InputHandler.InputHandler;
import CCWebcrawler.InputHandler.InputReader;
import CCWebcrawler.InputHandler.InputValidator;

import java.util.List;

public class ConsoleInputHandler implements InputHandler {

    private final CharSequence urlSeparator = " ";

    private final InputReader inputReader;

    private final InputValidator inputValidator;


    public ConsoleInputHandler() {
        this.inputReader = new ConsoleInputReader();
        this.inputValidator = new ConsoleInputValidator();
    }

    @Override
    public List<String> handleURLInput() {
        String consoleInput = inputReader.readUrls();
        List<String> separatedUrls = separateUrls(consoleInput);
        List<String> invalidURLs = separatedUrls.stream().filter(x -> !inputValidator.isValidURL(x)).toList();

        if (!invalidURLs.isEmpty()) {
            System.out.println(ConsoleInputHandlerTexts.INVALID_URL_RESPONSE);
            System.out.println("Invalid arguments: " + String.join(", ", invalidURLs));
            return handleURLInput();
        }

        return separatedUrls;
    }

    @Override
    public Integer handleTargetDepthInput() {
        String consoleInput = inputReader.readDepth();
        boolean isValidInput = inputValidator.isValidDepth(consoleInput);


        if (!isValidInput) {
            System.out.println(ConsoleInputHandlerTexts.INVALID_TARGET_DEPTH_PROMPT);
            return handleTargetDepthInput();
        }

        return Integer.parseInt(consoleInput);
    }


    private List<String> separateUrls(String consoleInput) {
        return List.of(consoleInput.split(urlSeparator.toString()));
    }


}

