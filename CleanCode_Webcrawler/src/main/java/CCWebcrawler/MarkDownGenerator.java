package CCWebcrawler;

import CCWebcrawler.Structure.HtmlHeading;
import CCWebcrawler.Structure.Link;
import CCWebcrawler.Structure.Website;

import java.util.List;

public class MarkDownGenerator {

    private static final String MARKDOWN_HEADER_TEMPLATE = """
            input: <a>" {$1} "</a>
            <br>depth: {$2}
            <br>summary:
            """;

    private static final String LINK_MARKDOWN_TEMPLATE = """
            <br>{$1} {$2} <a>{$3}</a>
                                                                     
                                                                     
            """;

    private static final String HEADER_MARKDOWN_TEMPLATE = """
            {$1} {$2} {$3}
            """;


    public static String generateStartLinkMarkDown(List<Link> startLinks, int targetDepth) {
        String result = "";

        for (Link startLink : startLinks)
            result = result.concat(generateStartLinkMarkDown(startLink, targetDepth));

        return result;
    }


    public static String generateStartLinkMarkDown(Link startLink, int targetDepth) {
        String result = "";
        result = result.concat(generateMarkDownIntro(startLink.url, targetDepth));
        result = result.concat(generateLinkMarkdown(startLink, 1));
        return result;
    }


    private static String generateLinkMarkdown(Link link, int currentLinkDepth) {
        String result = "";

        if (currentLinkDepth != 1) {
            String depthPrefix = getDepthPrefix(currentLinkDepth); //a link is one level further down
            result = result.concat(insertValuesToLinkTemplate(depthPrefix, link));
        }

        if (link.visited()) {
            Website destination = link.getDestination();
            result = result.concat(generateHeadingsMarkdown(destination.getHeadings(), destination.depth));
            for (Link subLink : destination.getLinks())
                result = result.concat(generateLinkMarkdown(subLink, currentLinkDepth + 1));
        }

        return result;
    }


    private static String generateHeadingsMarkdown(List<HtmlHeading> headings, int depth) {
        String depthPrefix = getDepthPrefix(depth);
        String result = "";

        for (HtmlHeading heading : headings)
            result = result.concat(HEADER_MARKDOWN_TEMPLATE
                    .replace("{$1}", getHeadingPrefix(heading.getHeadingLevelInt()))
                    .replace("{$2}", depthPrefix)
                    .replace("{$3}", heading.getContent())
            );

        return result;
    }


    private static String getHeadingPrefix(int level) {
        return "#".repeat(level);
    }

    private static String getDepthPrefix(int depth) {
        String prefix = depth > 1 ? "--".repeat(depth - 1) : "";
        return (prefix.isEmpty() ? prefix : prefix + ">");
    }

    private static String insertValuesToLinkTemplate(String depthPrefix, Link link) {
        String linkTextPrefix = link.isBroken() ? "broken link" : "link to";
        return LINK_MARKDOWN_TEMPLATE.replace("{$1}", depthPrefix)
                .replace("{$2}", linkTextPrefix)
                .replace("{$3}", link.url);
    }

    private static String generateMarkDownIntro(String inputUrl, int targetDepth) {
        return MARKDOWN_HEADER_TEMPLATE.replace("{$1}", inputUrl).replace("{$2}", String.valueOf(targetDepth));
    }
}
