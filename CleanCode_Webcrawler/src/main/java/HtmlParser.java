import org.jsoup.Connection;
import org.jsoup.HttpStatusException;
import org.jsoup.Jsoup;
import org.jsoup.UnsupportedMimeTypeException;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.util.List;

public class HtmlParser {


    private static final String HEADING_SELECTOR = "h1, h2, h3, h4, h5, h6";

    private static final String linkSelector = "a[href]";

    public Elements getHeadings(String website) throws IOException{
        return getElements(website, List.of(HEADING_SELECTOR));
    }


    public Elements getElements(String website, List<String> selectors) throws IOException, MalformedURLException, HttpStatusException, UnsupportedMimeTypeException, SocketTimeoutException {


        //fetch site html
        Document document =  Jsoup.connect(website).get();


        Elements result = new Elements().empty();

        for (String selector : selectors) {
            result.addAll(document.select(selector));
        }

        return result;
    }
}
