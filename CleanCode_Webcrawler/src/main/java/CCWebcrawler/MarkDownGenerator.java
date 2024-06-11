package CCWebcrawler;

import CCWebcrawler.Structure.HtmlHeading;
import CCWebcrawler.Structure.Link;
import CCWebcrawler.Structure.Website;

import java.util.List;
import java.util.Set;

public class MarkDownGenerator {

    private static final String MARKDOWN_HEADER_TEMPLATE = """
                                                            input: <a>" {$1} "</a>
                                                            <br>depth: {$2}
                                                            <br>summary:
                                                            """;

    private static final String LINK_MARKDOWN_TEMPLATE = """
                                                         <br> {$1} {$2} <a>{$3}</a>
                                                         """;

    private static final String HEADER_MARKDOWN_TEMPLATE =  """
                                                            {$1} {$2} {$3}
                                                            """;

    public static String generateMarkDownForWebsite(String inputUrl, int targetDepth, Website website) {
        String result = "";

        if(website.depth == 0)
            result = result.concat(generateMarkDownIntro(inputUrl, targetDepth));

        result = result.concat(generateHeadingMarkdown(website.getHeadings(), website.depth));
        result = result.concat(generateLinksMarkdown(website.getLinks(), targetDepth));
        return result;
    }

    private static String generateHeadingMarkdown(List<HtmlHeading> headings, int depth) {
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

    private static String generateLinksMarkdown(Set<Link> links, int depth) {
        String depthPrefix = getDepthPrefix(depth);
        String result = "";
        for (Link link : links)
            result = result.concat(insertValuesToLinkTemplate(depthPrefix,link));
        return result;
    }

    private static String getHeadingPrefix(int level) {
        return "#".repeat(level);
    }

    private static String getDepthPrefix(int depth) {
        String prefix = "--".repeat(depth);
        return (prefix.isEmpty() ? prefix : prefix + ">");
    }

    private static String insertValuesToLinkTemplate(String depthPrefix, Link link){
        String linkTextPrefix = link.broken ? "broken link" : "link to";
        return LINK_MARKDOWN_TEMPLATE.replace("{$1}", depthPrefix)
                                        .replace("{$2}", linkTextPrefix)
                                        .replace("{$3}", link.url);
    }

    private static String generateMarkDownIntro(String inputUrl, int targetDepth) {
        return MARKDOWN_HEADER_TEMPLATE.replace("{$1}", inputUrl).replace("{$2}", String.valueOf(targetDepth));
    }
}
