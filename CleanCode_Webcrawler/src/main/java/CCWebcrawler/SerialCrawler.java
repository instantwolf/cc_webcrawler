package CCWebcrawler;

import CCWebcrawler.Structure.HtmlHeading;
import CCWebcrawler.Structure.Link;
import CCWebcrawler.Structure.Website;
import HtmlParser.HtmlParserAdapter;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class SerialCrawler implements  CCWebCrawler{


    private final Link startLink;

    private int targetDepth = 0;


    private final HtmlParserAdapter parser;


    public SerialCrawler(String startUrl, int targetDepth, HtmlParserAdapter parser){
        this.startLink = new Link(startUrl);

        if(targetDepth > 0)
            this.targetDepth = targetDepth;

        this.parser = parser;
    }

    public List<Link> getResults(){
        return List.of(this.startLink);
    }


    public void crawlPages(){
        int currentDepth = 1;
        Set<Link> crawlSet = new HashSet<>();
        crawlSet.add(startLink);

        //fetching the start page initially (induction basis)
        //we do not increase depth for startPage -> stays 0
        crawlPagesAndSaveTargetIntoLinks(crawlSet,currentDepth);

        while(currentDepth < targetDepth){
            crawlSet = startLink.target.getLinksAtDepth(currentDepth).collect(Collectors.toSet());
            currentDepth++;
            crawlPagesAndSaveTargetIntoLinks(crawlSet, currentDepth);
        }
    }


    private void crawlPagesAndSaveTargetIntoLinks(Set<Link> links, int depth){
        for(Link link : links)
            crawlPageAndModifyLinkState(link, depth);
    }


    private void crawlPageAndModifyLinkState(Link linkToCrawl, int depth){
        try{
            linkToCrawl.target = crawlPage(linkToCrawl.url,depth);
        }
        catch(IOException e){
            linkToCrawl.broken = true;
        }
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
