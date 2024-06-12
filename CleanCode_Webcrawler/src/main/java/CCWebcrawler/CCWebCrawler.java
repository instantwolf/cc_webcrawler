package CCWebcrawler;

import CCWebcrawler.Structure.HtmlHeading;
import CCWebcrawler.Structure.Link;
import CCWebcrawler.Structure.Website;
import HtmlParser.HtmlParserAdapter;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public abstract class CCWebCrawler {

    protected final HtmlParserAdapter parser;

    protected List<Link> startLinks;

    protected int targetDepth = 0;


    public CCWebCrawler(List<String> startUrls, int targetDepth, HtmlParserAdapter parser) {
        this.parser = parser;
        setTargetDepth(targetDepth);
        initStartLinks(startUrls);
    }


    //Command
    public abstract void crawlPages();

    //Query
    public List<Link> getResults() {
        return this.startLinks;
    }


    /**
     * This method fetches all pages of the link-targets within the current crawlSet
     * It is abstract because it is once implemented parallel, once serial.
     */
    protected abstract void crawlPagesAndSaveTargetIntoLinks(Set<Link> links, int depth);

    protected void crawlPage(Link startLink) {
        int currentDepth = 1;
        Set<Link> crawlSet = new HashSet<>();
        crawlSet.add(startLink);

        crawlPagesAndSaveTargetIntoLinks(crawlSet, currentDepth);

        while (currentDepth < targetDepth) {
            crawlSet = startLink.target.getLinksAtDepth(currentDepth).collect(Collectors.toSet());
            currentDepth++;
            crawlPagesAndSaveTargetIntoLinks(crawlSet, currentDepth);
        }
    }

    protected void crawlPageAndModifyLinkState(Link linkToCrawl, int depth) {
        try {
            linkToCrawl.target = crawlPage(linkToCrawl.url, depth);
        } catch (IOException e) {
            linkToCrawl.broken = true;
        }
    }

    protected Website crawlPage(String url, int depth) throws IOException {
        ArrayList<HtmlHeading> headings = convertHashMapToHtmlHeading(parser.getHeadings(url));
        ArrayList<Link> links = parser.getLinks(url).stream().map(Link::new).collect(Collectors.toCollection(ArrayList::new));
        return new Website(url, links, headings, depth);
    }

    protected ArrayList<HtmlHeading> convertHashMapToHtmlHeading(HashMap<String, Integer> map) {
        ArrayList<HtmlHeading> headings = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            String content = entry.getKey();
            Integer headingLevel = entry.getValue();
            headings.add(new HtmlHeading(headingLevel, content));
        }

        return headings;
    }


    private void initStartLinks(List<String> startUrls) {
        this.startLinks = startUrls.stream().map(Link::new).collect(Collectors.toList());
    }

    private void setTargetDepth(int targetDepth) {
        if (targetDepth > 0) this.targetDepth = targetDepth;
    }
}
