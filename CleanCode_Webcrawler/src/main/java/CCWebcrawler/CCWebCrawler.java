package CCWebcrawler;

import CCWebcrawler.Structure.Link;
import java.util.List;

public interface CCWebCrawler {

    //command
    void crawlPages();

    //Query
    List<Link> getResults() ;

}
