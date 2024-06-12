package HtmlParser;


import HtmlParser.JsoupImplementation.JsoupHtmlParser;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class JsoupHtmlParserAdapter implements HtmlParserAdapter {


    public ArrayList<String> getLinks(String website) throws IOException {
        ArrayList<String> result = new ArrayList<>();

        JsoupHtmlParser.getLinks(website)
                .forEach(x -> result.add(convertHTMLElementToLink(x)));

        return result;
    }


    public HashMap<String,Integer> getHeadings(String website) throws IOException {
        HashMap<String,Integer> result = new HashMap<>();

        Elements e = JsoupHtmlParser.getHeadings(website);
        for (Element element : e)
            result.putAll(convertHTMLElementToHeading(element));

        return result;
    }


    private HashMap<String,Integer> convertHTMLElementToHeading(Element e) {
        HashMap<String,Integer> result = new HashMap<>();
        result.put(e.text(), convertTagNameToLevel(e.tagName()));
        return result;
    }

    private Integer convertTagNameToLevel(String tagName) {
        return Integer.parseInt(tagName.substring(1,2));
    }

    public String convertHTMLElementToLink(Element e) {
        return e.attr("abs:href");
    }

}
