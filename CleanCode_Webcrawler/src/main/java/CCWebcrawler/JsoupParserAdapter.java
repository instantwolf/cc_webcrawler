package CCWebcrawler;

import JsoupParser.HtmlParser;
import JsoupParser.HtmlParserSelector;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class JsoupParserAdapter {


    public ArrayList<String> getLinks(String website) throws IOException {
        ArrayList<String> result = new ArrayList<>();

        HtmlParser.getLinks(website)
                .forEach(x -> result.add(convertHTMLElementToLink(x)));

        return result;
    }

    public ArrayList<HtmlHeading> getHeadings(String website) throws IOException {
        ArrayList<HtmlHeading> result = new ArrayList<>();

        Elements e = HtmlParser.getHeadings(website);
        for (Element element : e) result.add(convertHTMLElementToHeading(element));

        return result;
    }




    private HtmlHeading convertHTMLElementToHeading(Element e) {
        HtmlHeadingLevel level = HtmlHeadingLevel.createFromString(e.tagName());
        return new HtmlHeading(level, e.text());
    }


    public String convertHTMLElementToLink(Element e) {
        return e.attr("abs:href");
    }



}
