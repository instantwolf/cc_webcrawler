import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

public class Website {

    List<HtmlHeading> headings;
    List<String> links;


    Website(){
        headings = new ArrayList<>();
        links = new ArrayList<>();
    }

    Website(Elements headings, Elements links){
        new Website();

    }

    public List<HtmlHeading> getWebsiteHeadings() {
        return this.headings;
    }

    public List<String> getLinks() {
        return this.links;
    }


    public void addHeading(String headingTag, String content){
        HtmlHeading new = new HtmlHeading(headingTag,content);
    }

}

