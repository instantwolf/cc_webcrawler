package CCWebcrawler;

import JsoupParser.HtmlParser;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Crawler {


    private final String startURL;

    private int targetDepth = 0;

    private Website page;

    private final JsoupParserAdapter parser;


    public Crawler(String startUrl, int targetDepth, JsoupParserAdapter parser){
        this.startURL = startUrl;

        if(targetDepth > 0)
            this.targetDepth = targetDepth;

        this.parser = parser;
    }

    public Website getResults() throws IOException{
        if(this.page == null)
            crawlPages();

        return this.page;
    }



    private void crawlPages() throws IOException{
        int currentDepth = 0;
        this.page = crawlPage(startURL,currentDepth++);

        Set<Website> crawlSet = new HashSet<>();
        crawlSet.add(page);

        while(currentDepth<= targetDepth){
            for (Website currentSite : crawlSet)
                    crawlPages(currentSite.getLinks(), currentDepth).forEach(currentSite::addChild);

            //now we add all newly crawled pages to the crawlSet
            crawlSet = page.getChildrenAtDepth(currentDepth++).collect(Collectors.toSet());
        }
    }


    private ArrayList<Website> crawlPages(ArrayList<Link> links, int depth) throws IOException{
        ArrayList<Website> results = new ArrayList<>();
        for(Link link : links)
            results.add(crawlPage(link.toString(),depth));
        return results;
    }

    private Website crawlPage(String url, int depth) throws IOException {
        ArrayList<HtmlHeading> headings = parser.getHeadings(url);
        ArrayList<Link> links = parser.getLinks(url).stream().map(Link::new).collect(Collectors.toCollection(ArrayList::new));
        return new Website(url,links,headings,depth);
    }


}
