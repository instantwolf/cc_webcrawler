package CCWebcrawler;

import java.util.List;

public class MarkDownGenerator {
    private String markDownString;

    public MarkDownGenerator() {
        markDownString = "";
    }

    public String generateMarkDown(String inputUrl, int targetDepth, List<Website> crawledWebsites) {
        generateHeader(inputUrl, targetDepth);
        addCrawledWebsites(crawledWebsites);
        return this.markDownString;
    }

    private void addCrawledWebsites(List<Website> crawledWebsites) {
        for (Website website : crawledWebsites) {
            addCrawledWebsite(website, 0); // todo: evaluate depth
        }
    }

    private void addCrawledWebsite(Website website, int depth) {
        addHeadings(website.getHeadings(), depth);
        addLinks(website.getLinks(), depth);
        this.markDownString += "\n";
    }

    private void addHeadings(List<HtmlHeading> headings, int depth) {
        String headingPrefix = "";
        String depthPrefix = getDepthPrefix(depth);
        for (HtmlHeading heading : headings) {
            headingPrefix = getHeadingPrefix(heading.getHeadingLevelInt());
            this.markDownString += "\n";
            this.markDownString += headingPrefix + depthPrefix;
            this.markDownString += heading.getContent();
        }
    }

    private void addLinks(List<String> links, int depth) {
        String depthPrefix = getDepthPrefix(depth);
        for (String link : links) {
            this.markDownString += "\n";
            this.markDownString += "<br>" + depthPrefix + " <a>" + link + "</a>"; // todo: + broken link / link to
        }
    }

    private String getHeadingPrefix(int level) {
        return "#".repeat(level);
    }

    private String getDepthPrefix(int depth) {
        String prefix = "--".repeat(depth);
        return (prefix.length() == 0 ? prefix : prefix + ">");
    }
    private void generateHeader(String inputUrl, int targetDepth) {
        this.markDownString += "input: <a>" + inputUrl + "</a>\n";
        this.markDownString += "<br> depth" + targetDepth + "\n";
        this.markDownString += "<br> summary:\n";
    }
}
