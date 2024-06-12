package CCWebcrawler;

import CCWebcrawler.InputHandler.Console.ConsoleInputHandler;
import CCWebcrawler.InputHandler.InputHandler;
import CCWebcrawler.Structure.Link;
import HtmlParser.JsoupHtmlParserAdapter;

import java.util.List;

public class Main {


    public static void main(String[] args) {
        InputHandler inputHandler = new ConsoleInputHandler();
        List<String> urls = inputHandler.handleURLInput();
        Integer targetDepth = inputHandler.handleTargetDepthInput();

        JsoupHtmlParserAdapter parser = new JsoupHtmlParserAdapter();
        CCWebCrawler crawler = CrawlerFactory.create(urls, targetDepth, parser);

        crawler.crawlPages();

        List<Link> startLinks = crawler.getResults();

        String markDown = MarkDownGenerator.generateStartLinkMarkDown(startLinks, targetDepth);

        System.out.println(markDown);
        MarkdownFileWriter.printMarkDownToFile(markDown);
    }


}




