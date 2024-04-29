public class HtmlHeading {

    HtmlHeadingLevel level;

    String content = "";

    HtmlHeading(String tag, String content){
        this.level = HtmlHeadingLevel.createFromString(tag);
        this.content = content;
    }

    HtmlHeading(HtmlHeadingLevel level, String content){
        this.level = level;
        this.content = content;
    }

    HtmlHeading(int headingLevel, String content){

        this.level =  HtmlHeadingLevel.isValidHtmlHeadingLevel(headingLevel) ?
                 HtmlHeadingLevel.createFromInt(headingLevel) : HtmlHeadingLevel.getDefaultLevel();
        this.content = content;
   }

    public String getContent(){
        return this.content;
    }

    public HtmlHeading setContent(String content){
        this.content = content;
        return this;
    }

    public int getHeadingLevelInt(){ return this.level.getHeadingLevelInt(); }

    @Override
    public String toString() {
        return "HtmlHeading{" +
                "level=" + level +
                ", content='" + content + '\'' +
                '}';
    }
}
