package CCWebcrawler;

import HtmlParser.HtmlParserAdapter;

import java.util.List;

public abstract class CrawlerFactory {

    public static CCWebCrawler create(List<String> urls, int targetDepth, HtmlParserAdapter parserAdapter) {
        if (urls.size() > 1) {
            return new ParallelCrawler(urls, targetDepth, parserAdapter);
        } else
            return new SerialCrawler(urls, targetDepth, parserAdapter);
    }


}
