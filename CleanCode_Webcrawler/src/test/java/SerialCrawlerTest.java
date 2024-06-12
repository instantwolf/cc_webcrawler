import CCWebcrawler.SerialCrawler;
import CCWebcrawler.Structure.HtmlHeading;
import CCWebcrawler.Structure.Link;
import HtmlParser.JsoupHtmlParserAdapter;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
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

        List<Link> results = serialCrawler.getResults();
        assertEquals(1,results.size());
        assertEquals(startLinks.getFirst(), results.getFirst().url); //check parent node

        //every link is contained
        Set<String> resultLinks  = results.getFirst().getDestination().getLinks().stream().map(x -> x.url).collect(Collectors.toSet());
        for(String link : links)
            assertTrue(resultLinks.stream().anyMatch(x -> x.equals(link)));
        //amount of links is the same
        assertEquals(2, resultLinks.size());

        ArrayList<HtmlHeading> resultHeadings = results.getFirst().getDestination().getHeadings();
        for(HtmlHeading heading : resultHeadings)
            assertTrue(headings.containsKey(heading.getContent()) && headings.get(heading.getContent()) == heading.getHeadingLevelInt());
        //amount of links is the same
        assertEquals(2, resultLinks.size());

    }
}
