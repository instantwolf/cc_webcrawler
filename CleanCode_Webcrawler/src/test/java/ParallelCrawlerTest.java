import CCWebcrawler.ParallelCrawler;
import CCWebcrawler.SerialCrawler;
import CCWebcrawler.Structure.HtmlHeading;
import CCWebcrawler.Structure.Link;
import HtmlParser.JsoupHtmlParserAdapter;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
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

public class ParallelCrawlerTest {
    private static List<String> startLinks;

    private static List<Link> resultLinks;

    private static ArrayList<String> links;

    private static HashMap<String, Integer> headings;

    private static ParallelCrawler parallelCrawler;

    private static int targetDepth;

    JsoupHtmlParserAdapter parserAdapter;


    @BeforeEach
    public void init() {
        startLinks = new ArrayList<>();
        links = new ArrayList<>();
        headings = new HashMap<>();
        resultLinks = new ArrayList<>();
        parserAdapter = mock(JsoupHtmlParserAdapter.class);

        startLinks.add("https://example.com");
        startLinks.add("https://example.com");

        resultLinks.add(new Link("http://www.iana.org/domains/arpa"));
        resultLinks.add(new Link("http://www.iana.org/reviews"));
        resultLinks.add(new Link("http://www.iana.org/domains/arpa"));
        resultLinks.add(new Link("http://www.iana.org/reviews"));
        parallelCrawler = new ParallelCrawler(startLinks,targetDepth,parserAdapter);
    }

    @Test
    public void pagesNotCrawledTest() {
        List<Link> results = parallelCrawler.getResults();
        //output elements should be same as input elements, no pages crawled
        assertEquals(startLinks.size(), results.size());
        assertEquals(false, results.stream().anyMatch(Link::visited));
    }

    @Test
    public void crawlerTestSuccess() throws IOException {
        links.add("http://www.iana.org/domains/arpa");
        links.add("http://www.iana.org/reviews");

        headings.put("h1", 1);
        headings.put("h3", 3);


        when(parserAdapter.getLinks(startLinks.get(0))).thenReturn(links);
        when(parserAdapter.getLinks(startLinks.get(1))).thenReturn(links);

        when(parserAdapter.getHeadings(startLinks.get(0))).thenReturn(headings);
        when(parserAdapter.getHeadings(startLinks.get(1))).thenReturn(headings);


        targetDepth = 1;
        parallelCrawler = new ParallelCrawler(startLinks, targetDepth, parserAdapter);

        parallelCrawler.crawlPages();

        List<Link> results = parallelCrawler.getResults();
        assertEquals(startLinks.size(),results.size());
        assertEquals(startLinks.getFirst(), results.getFirst().url); //check parent node

        //every link is contained
        Set<String> resultLinks  = results.getFirst().getDestination().getLinks().stream().map(x -> x.url).collect(Collectors.toSet());
        for(String link : links)
            assertTrue(resultLinks.stream().anyMatch(x -> x.equals(link)));
        //amount of links is the same
        assertEquals(startLinks.size(), resultLinks.size());

        ArrayList<HtmlHeading> resultHeadings = results.getFirst().getDestination().getHeadings();
        for(HtmlHeading heading : resultHeadings)
            assertTrue(headings.containsKey(heading.getContent()) && headings.get(heading.getContent()) == heading.getHeadingLevelInt());
        //amount of links is the same
        assertEquals(startLinks.size(), resultLinks.size());

    }
}
