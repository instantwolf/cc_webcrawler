import CCWebcrawler.Markdown.MarkdownGenerator;
import CCWebcrawler.Structure.HtmlHeading;
import CCWebcrawler.Structure.Link;
import CCWebcrawler.Structure.Website;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


public class MarkdownGeneratorTest {

    private static final int targetDepth = 1;
    private static final ArrayList<String> exampleLinks = new ArrayList<>(Set.of("www.exampleLink1.com", "www.exampleLink2.com", "www.exampleLink3.com", "www.exampleLink4.com", "www.exampleLink5.com"));


    @Test
    public void generate_markdown_for_empty_website() {
        Website emptyWebsite = getEmptyWebsite();
        Link emptyWebsiteLink = new Link(emptyWebsite.getUrl());
        emptyWebsiteLink.setDestination(emptyWebsite);
        String markdown =
                MarkdownGenerator.generateStartlinksMarkdown(List.of(emptyWebsiteLink), targetDepth);
        System.out.println(markdown);

        markDownHeaderTest(markdown, emptyWebsite);
    }

    @Test
    public void generate_markdown_for_first_level_website() {
        Website simpleWebsite = getSimpleWebsite();
        Link simpleWebsiteLink = new Link(simpleWebsite.getUrl());
        simpleWebsiteLink.setDestination(simpleWebsite);
        String markdown =
                MarkdownGenerator.generateStartlinksMarkdown(List.of(simpleWebsiteLink), targetDepth);

        //every Heading h1-h6 with its given prefix and indentation shall be contained
        markDownHeaderTest(markdown, simpleWebsite);
        test_website_links_markdown(markdown, simpleWebsite, 0);
        test_website_headings_markdown(markdown, simpleWebsite, 0);
    }

    @Test
    public void generate_markdown_for_nested_website() {
        Website nested = getNestedWebsite();
        Link nestedLink = new Link(nested.getUrl());
        nestedLink.setDestination(nested);
    }


    /**
     * Test subfunctions that are used serveral times
     */
    private void test_website_links_markdown(String markdown, Website website, int depth) {
        for (Link link : website.getLinks())
            Assertions.assertTrue(containsWordInLine(markdown, getLinkMarkDown(link, depth), -1));
    }

    private void test_website_headings_markdown(String markdown, Website website, int depth) {
        for (HtmlHeading heading : website.getHeadings())
            Assertions.assertTrue(containsWordInLine(markdown, getHeadingMarkDown(heading, depth), -1));
    }

    private String getHeadingMarkDown(HtmlHeading heading, int depth) {
        StringBuilder builder = new StringBuilder();
        builder.repeat("#", heading.getHeadingLevelInt()).append(" ");

        if (depth > 0)
            builder.repeat("--", depth).append(">");

        builder.append(" ");
        builder.append(heading.getContent());
        return builder.toString();
    }

    private String getLinkMarkDown(Link link, int depth) {
        String prefix = "<br>";

        prefix = prefix.concat("--".repeat(depth + 1).concat("> "));
        String linkIntro = (link.isBroken() ? "broken link" : "link to").concat(" ");
        prefix = prefix.concat(linkIntro);
        return prefix.concat("<a>{$1}</a>").replace("{$1}", link.url);
    }

    private boolean containsWordInLine(String markdown, String word, int lineNumber) {
        ArrayList<String> lines = new ArrayList<String>(Arrays.stream(markdown.split("\n")).toList());
        if (lineNumber >= 0)
            return lines.stream().filter(x -> x.equals(lines.get(lineNumber))).anyMatch(x -> x.contains(word));
        else
            return markdown.contains(word);
    }

    private void markDownHeaderTest(String markdown, Website website) {
        //what can we say about that. It can only consist of the header lines (should be 3)
        Assertions.assertTrue(getLinesFromMarkdown(markdown) >= 3);

        //structural assertions
        Assertions.assertTrue(containsWordInLine(markdown, "input", 0));
        Assertions.assertTrue(containsWordInLine(markdown, website.getUrl(), 0));

        Assertions.assertTrue(containsWordInLine(markdown, "depth", 1));
        Assertions.assertTrue(containsWordInLine(markdown, String.valueOf(targetDepth), 1));

        Assertions.assertTrue(containsWordInLine(markdown, "summary", 2));
    }

    /**
     * Website content generator helper functions
     */

    private Website getSimpleWebsite() {
        return generateExampleSite("www.example.org", 0, false);
    }

    private Website getEmptyWebsite() {
        return generateExampleSite("www.example.org", 0, true);
    }

    private Website getNestedWebsite() {
        ArrayList<Website> sites = new ArrayList<>();

        for (int i = 0; i < exampleLinks.size(); i++)
            sites.add(generateExampleSite(exampleLinks.get(i), i, false));

        for (int i = 0; i < exampleLinks.size() - 1; i++)
            sites.get(i).addChild(new Link(sites.get(i + 1).getUrl())); //add next site as child to previous one

        return sites.getFirst();
    }

    private Website generateExampleSite(String url, int depth, boolean empty) {
        ArrayList<Link> links = new ArrayList<>();
        ArrayList<HtmlHeading> headings = new ArrayList<>();

        if (!empty) {
            links = exampleLinks.stream().map(Link::new).collect(Collectors.toCollection(ArrayList::new));
            headings = generateExampleHeadings();
        }

        return new Website(url, links, headings, depth);
    }

    private ArrayList<HtmlHeading> generateExampleHeadings() {
        ArrayList<HtmlHeading> headings = new ArrayList<>();
        for (int i = 1; i <= 6; i++) {
            headings.add(new HtmlHeading(i, "ExampleHeading-H" + i));
        }
        return headings;
    }


    private int getLinesFromMarkdown(String markdown) {
        return markdown.split("\n").length;
    }

}
