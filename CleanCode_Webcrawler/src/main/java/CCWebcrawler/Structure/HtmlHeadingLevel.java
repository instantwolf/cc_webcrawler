package CCWebcrawler.Structure;

public enum HtmlHeadingLevel {
    H1(1),
    H2(2),
    H3(3),
    H4(4),
    H5(5),
    H6(6);
    private final int headingLevel;

    HtmlHeadingLevel(int headinglevel) {
        this.headingLevel = headinglevel;
    }


    public static HtmlHeadingLevel createFromString(String headingLevel) {
        String level = headingLevel.toLowerCase();
        return
                switch (level) {
                    case "h1" -> createFromInt(1);
                    case "h2" -> createFromInt(2);
                    case "h3" -> createFromInt(3);
                    case "h4" -> createFromInt(4);
                    case "h5" -> createFromInt(5);
                    case "h6" -> createFromInt(6);
                    default -> HtmlHeadingLevel.getDefaultLevel();
                };
    }


    public static boolean isValidHtmlHeadingLevel(int level) {
        return level >= 1 && level <= 6;
    }

    public static HtmlHeadingLevel createFromInt(int headingLevel) {
        return
                switch (headingLevel) {
                    case 1 -> H1;
                    case 2 -> H2;
                    case 3 -> H3;
                    case 4 -> H4;
                    case 5 -> H5;
                    case 6 -> H6;
                    default -> throw new IllegalStateException("Unexpected value: " + headingLevel);
                };
    }

    public static HtmlHeadingLevel getDefaultLevel() {
        return HtmlHeadingLevel.H1;
    }

    public int getHeadingLevelInt() {
        return this.headingLevel;
    }
}
