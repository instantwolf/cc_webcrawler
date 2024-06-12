package CCWebcrawler.InputHandler.Console;

public enum ConsoleInputHandlerTexts {

    URL_PROMPT("Enter the desired URL like: https://www.example.com. \n" +
            "If you want to crawl multiple pages in parallel, enter the URLs in the given format separated by a whitespace. \n" +
            "eg. [URL1] [URL2]"),
    INVALID_URL_RESPONSE("Invalid URL. Please provide a valid URL: \n " +
            "URL Pattern: ((http|https)://)?(www.)?([subdomain].)?[domain].[top-level-domain](/[URI])?"),
    TARGET_DEPTH_PROMPT("Enter the depth of the websites to crawl: "),
    INVALID_TARGET_DEPTH_PROMPT("Invalid depth, please provide a positive  number bigger than zero"),
    ERROR_READING_INPUT("Invalid depth, please provide a positive  number bigger than zero");

    private final String text;

    ConsoleInputHandlerTexts(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return text;
    }
}
