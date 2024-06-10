package CCWebcrawler;

import CCWebcrawler.Structure.Website;

import java.io.IOException;

public interface CCWebCrawler {

    //command
    void crawlPages() throws IOException;

    //Query
    Website getResults() ;

}
