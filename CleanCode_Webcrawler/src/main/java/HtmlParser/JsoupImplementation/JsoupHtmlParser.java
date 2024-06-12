package HtmlParser.JsoupImplementation;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.List;

public class JsoupHtmlParser {


    public static Elements getLinks(String website) throws IOException {
        return getElements(website, List.of(JsoupHtmlParserSelector.LINK));
    }

    public static Elements getHeadings(String website) throws IOException {
        return getElements(website, List.of(JsoupHtmlParserSelector.HEADING));
    }


    private static Elements getElements(String website, List<JsoupHtmlParserSelector> selectors) throws IOException {
        Document document = Jsoup.connect(website).get();
        Elements result = new Elements().empty();
        for (JsoupHtmlParserSelector selector : selectors) {
            Elements e = document.select(selector.toString());
            result.addAll(e);
        }
        return result;
    }

}
