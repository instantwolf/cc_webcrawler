package CCWebcrawler;

import CCWebcrawler.Structure.Link;
import CCWebcrawler.Structure.Website;

import java.io.IOException;

public interface CCWebCrawler {

    //command
    void crawlPages() throws IOException;

    //Query
    Link getResults() ;

}
