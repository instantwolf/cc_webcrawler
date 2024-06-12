package CCWebcrawler.Markdown;

import CCWebcrawler.Structure.HtmlHeading;
import CCWebcrawler.Structure.Link;
import CCWebcrawler.Structure.Website;

import java.util.ArrayList;
import java.util.List;

public class MarkdownGenerator {


    private static final int defaultStartpageDepth = 1;

    public static String generateStartlinksMarkdown(List<Link> startLinks, int targetDepth) {
        String result = "";

        for (Link startLink : startLinks)
            result = result.concat(generateStartlinkMarkdown(startLink, targetDepth));

        return result;
    }


    public static String generateStartlinkMarkdown(Link startLink, int targetDepth) {
        String result = "";
        result = result.concat(MarkdownStringGenerator.generatePageIntroMarkdown(startLink, targetDepth));
        result = result.concat(generatePageLinksMarkdown(startLink, defaultStartpageDepth));
        return result;
    }


    private static String generatePageLinksMarkdown(Link link, int currentLinkDepth) {
        String result = "";

        //startLinks don't need to generate it`s own Link-Markdown because they have the markdown Intro
        if (currentLinkDepth != 1)
            result = result.concat(MarkdownStringGenerator.generateLinkMarkdown(link, currentLinkDepth));

        if (link.visited()) {
            Website destination = link.getDestination();
            String destinationHeadingsMarkdown;
            result = result.concat(generatePageHeadingsMarkdown(destination.getHeadings(), destination.depth));
            for (Link subLink : destination.getLinks())
                result = result.concat(generatePageLinksMarkdown(subLink, currentLinkDepth + 1));
        }

        return result;
    }


    private static String generatePageHeadingsMarkdown(ArrayList<HtmlHeading> headings, int depthOfHeading) {
        String result = "";

        for (HtmlHeading heading : headings)
            result = result.concat(MarkdownStringGenerator.generateHeadingMarkdown(heading, depthOfHeading));

        return result;
    }
}
