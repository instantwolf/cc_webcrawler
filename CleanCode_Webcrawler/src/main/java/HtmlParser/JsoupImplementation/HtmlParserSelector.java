package HtmlParser.JsoupImplementation;

public enum HtmlParserSelector {

    LINK(false),
    HEADING(true);

    private final boolean selectsHeadings;

    private static final String HEADING_SELECTOR = "h1, h2, h3, h4, h5, h6";

    private static final String LINK_SELECTOR = "a[href]";


    HtmlParserSelector(boolean type){
        this.selectsHeadings = (boolean)type;
    }



    public String toString(){
        if (selectsHeadings)
            return HEADING_SELECTOR;
        else return LINK_SELECTOR;
    }

}
