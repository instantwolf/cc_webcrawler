package CCWebcrawler;

import CCWebcrawler.Structure.Link;
import HtmlParser.HtmlParserAdapter;
import java.util.*;

public class SerialCrawler extends CCWebCrawler{


    public SerialCrawler(List<String> startUrls, int targetDepth, HtmlParserAdapter parser){
        super(startUrls,targetDepth,parser);
    }


    public void crawlPages(){
       crawlPage(this.startLinks.getFirst());
    }


    @Override
    protected void crawlPagesAndSaveTargetIntoLinks(Set<Link> links, int depth){
        for(Link link : links)
            crawlPageAndModifyLinkState(link, depth);
    }

}
