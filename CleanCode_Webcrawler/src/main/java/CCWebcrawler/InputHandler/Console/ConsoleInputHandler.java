package CCWebcrawler.InputHandler.Console;

import CCWebcrawler.InputHandler.InputHandler;
import CCWebcrawler.InputHandler.InputReader;
import CCWebcrawler.InputHandler.InputValidator;

import java.util.List;

public class ConsoleInputHandler implements InputHandler {

    private final CharSequence urlSeparator = " ";

    private final InputReader inputReader;

    private final InputValidator inputValidator;


    public ConsoleInputHandler(){
        this.inputReader = new ConsoleInputReader();
        this.inputValidator = new ConsoleInputValidator();
    }

    @Override
    public List<String> handleURLInput() {
        String consoleInput = inputReader.readUrls();
        boolean isValidInput = false;

        List<String> separatedUrls = separateUrls(consoleInput);
        isValidInput = separatedUrls.stream().allMatch(inputValidator::isValidURL);


        if(!isValidInput) {
            System.out.println(ConsoleInputHandlerTexts.INVALID_URL_RESPONSE);
            return handleURLInput();
        }

        return separatedUrls;
    }

    @Override
    public Integer handleTargetDepthInput() {
        String consoleInput = inputReader.readDepth();
        boolean isValidInput = inputValidator.isValidDepth(consoleInput);


        if(!isValidInput) {
            System.out.println(ConsoleInputHandlerTexts.INVALID_TARGET_DEPTH_PROMPT);
            return handleTargetDepthInput();
        }

        return Integer.parseInt(consoleInput);
    }


    private List<String> separateUrls(String consoleInput){
        return List.of(consoleInput.split(" "));
    }


}

