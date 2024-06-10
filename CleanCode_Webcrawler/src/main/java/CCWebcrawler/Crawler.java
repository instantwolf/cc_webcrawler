package CCWebcrawler;

import CCWebcrawler.Structure.HtmlHeading;
import CCWebcrawler.Structure.Link;
import CCWebcrawler.Structure.Website;
import HtmlParser.JsoupParserAdapter;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class Crawler implements  CCWebCrawler{


    private final String startURL;

    private int targetDepth = 0;

    private Website startPage;

    private final JsoupParserAdapter parser;


    public Crawler(String startUrl, int targetDepth, JsoupParserAdapter parser){
        this.startURL = startUrl;

        if(targetDepth > 0)
            this.targetDepth = targetDepth;

        this.parser = parser;
    }

    public Website getResults(){
        return this.startPage;
    }


    public void crawlPages() throws IOException{
        int currentDepth = 0;


        this.startPage = crawlPage(startURL,currentDepth++);

        Set<Website> crawlSet = new HashSet<>();
        crawlSet.add(startPage);

        while(currentDepth<= targetDepth){
            for (Website currentSite : crawlSet)
                    crawlPages(currentSite.getLinks(), currentDepth).forEach(currentSite::addChild);

            //now we add all newly crawled pages to the crawlSet
            crawlSet = startPage.getChildrenAtDepth(currentDepth++).collect(Collectors.toSet());
        }
    }


    private ArrayList<Website> crawlPages(ArrayList<Link> links, int depth) throws IOException{
        ArrayList<Website> results = new ArrayList<>();
        for(Link link : links)
            results.add(crawlPage(link.toString(),depth));
        return results;
    }


    private Link crawlPageAndModifyLinkState(Link linkToCrawl, int depth){
        try{
            linkToCrawl.target = crawlPage(linkToCrawl.url,depth);
        }
        catch(IOException e){
            linkToCrawl.broken = true;
        }
        return linkToCrawl;
    }


    private Website crawlPage(String url, int depth) throws IOException {
        ArrayList<HtmlHeading> headings = convertHashMapToHtmlHeading(parser.getHeadings(url));
        ArrayList<Link> links = parser.getLinks(url).stream().map(Link::new).collect(Collectors.toCollection(ArrayList::new));
        return new Website(url,links,headings,depth);
    }

    private ArrayList<HtmlHeading> convertHashMapToHtmlHeading(HashMap<String,Integer> map){
        ArrayList<HtmlHeading> headings = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            String content = entry.getKey();
            Integer headingLevel = entry.getValue();
            headings.add(new HtmlHeading(headingLevel,content));
        }

        return headings;
    }


}
