import CCWebcrawler.SerialCrawler;
import CCWebcrawler.Structure.Link;
import HtmlParser.JsoupHtmlParserAdapter;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class SerialCrawlerTest {

    private static List<String> startLinks;

    private static List<Link> resultLinks;

    private static ArrayList<String> links;

    private static HashMap<String, Integer> headings;

    private static SerialCrawler serialCrawler;

    private static int targetDepth;

    @BeforeAll
    public static void init() {
        startLinks = new ArrayList<>();
        links = new ArrayList<>();
        headings = new HashMap<>();
        resultLinks = new ArrayList<>();

        startLinks.add("https://example.com");

        resultLinks.add(new Link("http://www.iana.org/domains/arpa"));
        resultLinks.add(new Link("http://www.iana.org/reviews"));
    }

    @Test
    public void crawlerNotIntializedTest() {
        assertEquals(null, serialCrawler.getResults());
    }

    @Test
    public void crawlerTestSuccess() throws IOException {
        JsoupHtmlParserAdapter parserAdapter = mock(JsoupHtmlParserAdapter.class);

        links.add("http://www.iana.org/domains/arpa");
        links.add("http://www.iana.org/reviews");

        headings.put("h1", 1);
        headings.put("h3", 3);


        when(parserAdapter.getLinks(startLinks.getFirst())).thenReturn(links);
        when(parserAdapter.getHeadings(startLinks.getFirst())).thenReturn(headings);


        targetDepth = 1;
        serialCrawler = new SerialCrawler(startLinks, targetDepth, parserAdapter);

        serialCrawler.crawlPages();

        assertEquals(resultLinks, serialCrawler.getResults());
    }
}
