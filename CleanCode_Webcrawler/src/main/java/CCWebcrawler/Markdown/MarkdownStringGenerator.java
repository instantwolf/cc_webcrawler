package CCWebcrawler.Markdown;

import CCWebcrawler.Structure.HtmlHeading;
import CCWebcrawler.Structure.Link;

import java.util.Collection;
import java.util.List;

/**
 * SRP:
 * Template Class that separates the actual markdown string generation from the markdown generation algorithm
 */
public class MarkdownStringGenerator {


    public static String generateHeadingMarkdown(HtmlHeading heading, int depthOfHeading) {
        String indentationPrefix = getIndentationPrefix(depthOfHeading);
        String headingPrefix = getHeadingPrefix(heading.getHeadingLevelInt());
        String content = heading.getContent();
        List<String> replacementValues = List.of(headingPrefix, indentationPrefix, content);

        return replaceVariablesInTemplateByValues(MarkdownStringTemplates.HEADER_MARKDOWN_TEMPLATE.toString(),
                replacementValues);
    }


    public static String generateLinkMarkdown(Link link, int depthOfLink) {
        String indentationPrefix = getIndentationPrefix(depthOfLink);
        String linkStateInfix = getStateLinkInfix(link);
        List<String> replacementValues = List.of(indentationPrefix, linkStateInfix, link.url);

        return replaceVariablesInTemplateByValues(MarkdownStringTemplates.LINK_MARKDOWN_TEMPLATE.toString(),
                replacementValues);
    }

    public static String generatePageIntroMarkdown(Link startLink, int crawlTargetDepth) {
        String inputUrl = startLink.url;
        List<String> replacementValues = List.of(inputUrl, String.valueOf(crawlTargetDepth));

        return replaceVariablesInTemplateByValues(MarkdownStringTemplates.MARKDOWN_INTRO_TEMPLATE.toString(),
                replacementValues);
    }

    private static String getStateLinkInfix(Link link) {
        return link.isBroken() ? "broken link" : "link to";
    }

    private static String getHeadingPrefix(int level) {
        return "#".repeat(level);
    }

    private static String getIndentationPrefix(int depth) {
        String prefix = depth > 1 ? "--".repeat(depth - 1) : "";
        return (prefix.isEmpty() ? prefix : prefix + ">");
    }

    private static String replaceVariablesInTemplateByValues(String template, Collection<String> values) {
        String result = template;
        int currentPlaceHolder = 1;

        for (String value : values)
            result = result.replace("{$" + currentPlaceHolder++ + "}", value);

        return result;
    }


}
