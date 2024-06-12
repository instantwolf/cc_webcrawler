package CCWebcrawler;

import CCWebcrawler.Structure.Link;
import HtmlParser.HtmlParserAdapter;
import java.util.*;

public class ParallelCrawler extends CCWebCrawler{


    public ParallelCrawler(List<String> startUrls, int targetDepth, HtmlParserAdapter parser){
        super(startUrls,targetDepth,parser);
    }


    public void crawlPages(){
        this.startLinks.stream().parallel().forEach(this::crawlPage);
    }


    @Override
    protected void crawlPagesAndSaveTargetIntoLinks(Set<Link> links, int depth){
        links.stream().parallel().forEach((x) -> {
            crawlPageAndModifyLinkState(x,depth);
        });
    }

}
