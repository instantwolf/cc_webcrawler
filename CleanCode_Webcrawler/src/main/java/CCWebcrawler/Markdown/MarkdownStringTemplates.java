package CCWebcrawler.Markdown;

public enum MarkdownStringTemplates {

    MARKDOWN_INTRO_TEMPLATE("""
            input: <a>{$1}</a>
            <br>depth: {$2}
            <br>summary:
            """),
    LINK_MARKDOWN_TEMPLATE("""
            <br>{$1} {$2} <a>{$3}</a>
                                                                     
                                                                     
            """),
    HEADER_MARKDOWN_TEMPLATE("""
            {$1} {$2} {$3}
            """);


    private final String text;

    MarkdownStringTemplates(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return text;
    }

}
