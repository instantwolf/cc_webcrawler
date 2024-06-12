import CCWebcrawler.Structure.HtmlHeadingLevel;
import org.junit.Test;

import static org.junit.Assert.*;

public class HtmlHeadingLevelTest {

    @Test
    public void createFromIntValidTest() {
        assertEquals(HtmlHeadingLevel.H1, HtmlHeadingLevel.createFromInt(1));
        assertEquals(HtmlHeadingLevel.H2, HtmlHeadingLevel.createFromInt(2));
        assertEquals(HtmlHeadingLevel.H3, HtmlHeadingLevel.createFromInt(3));
        assertEquals(HtmlHeadingLevel.H4, HtmlHeadingLevel.createFromInt(4));
        assertEquals(HtmlHeadingLevel.H5, HtmlHeadingLevel.createFromInt(5));
        assertEquals(HtmlHeadingLevel.H6, HtmlHeadingLevel.createFromInt(6));
    }

    @Test
    public void createFromIntInvalidTest() {
        assertThrows(IllegalStateException.class, () -> HtmlHeadingLevel.createFromInt(7));
    }

    @Test
    public void createFromStringValidTest() {
        assertEquals(HtmlHeadingLevel.H1, HtmlHeadingLevel.createFromString("h1"));
        assertEquals(HtmlHeadingLevel.H2, HtmlHeadingLevel.createFromString("h2"));
        assertEquals(HtmlHeadingLevel.H3, HtmlHeadingLevel.createFromString("h3"));
        assertEquals(HtmlHeadingLevel.H4, HtmlHeadingLevel.createFromString("h4"));
        assertEquals(HtmlHeadingLevel.H5, HtmlHeadingLevel.createFromString("h5"));
        assertEquals(HtmlHeadingLevel.H6, HtmlHeadingLevel.createFromString("h6"));
    }

    @Test
    public void createFromStringInvalidTest() {
        assertEquals(HtmlHeadingLevel.getDefaultLevel(), HtmlHeadingLevel.createFromString("h7"));
    }

    @Test
    public void isValidHtmlHeadingLevelTest() {
        assertTrue(HtmlHeadingLevel.isValidHtmlHeadingLevel(1));
        assertTrue(HtmlHeadingLevel.isValidHtmlHeadingLevel(2));
        assertTrue(HtmlHeadingLevel.isValidHtmlHeadingLevel(3));
        assertTrue(HtmlHeadingLevel.isValidHtmlHeadingLevel(4));
        assertTrue(HtmlHeadingLevel.isValidHtmlHeadingLevel(5));
        assertTrue(HtmlHeadingLevel.isValidHtmlHeadingLevel(6));
    }

    @Test
    public void isInvalidHtmlHeadingLevelTest() {
        assertFalse(HtmlHeadingLevel.isValidHtmlHeadingLevel(0));
        assertFalse(HtmlHeadingLevel.isValidHtmlHeadingLevel(7));
    }

    @Test
    public void getDefaultHeadingLevelTest() {
        assertEquals(HtmlHeadingLevel.H1, HtmlHeadingLevel.getDefaultLevel());
    }
}
