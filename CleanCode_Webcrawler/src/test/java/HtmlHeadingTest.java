import CCWebcrawler.Structure.HtmlHeading;
import CCWebcrawler.Structure.HtmlHeadingLevel;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class HtmlHeadingTest {
    private HtmlHeading heading;

    @Test
    public void createHeadingWithTagTest() {
        heading = new HtmlHeading("h1", "Heading 1");
        assertNotNull(heading);
    }

    @Test
    public void createHeadingWithHtmlHeadingLevelTest() {
        heading = new HtmlHeading(HtmlHeadingLevel.H1, "Heading 1");
        assertNotNull(heading);
    }

    @Test
    public void createHeadingWithHeadingLevelTest() {
        heading = new HtmlHeading(1, "Heading 1");
        assertNotNull(heading);
    }

    @Test
    public void getContentTest() {
        heading = new HtmlHeading(1, "Heading 1");
        assertEquals("Heading 1", heading.getContent());
    }

    @Test
    public void setContentTest() {
        heading = new HtmlHeading(1, "");
        heading.setContent("Heading 1");
        assertEquals("Heading 1", heading.getContent());
    }

    @Test
    public void getHeadingLevelTest() {
        heading = new HtmlHeading("h1", "");
        assertEquals(1, heading.getHeadingLevelInt());
    }

}
