import org.junit.jupiter.api.Test;

import static org.junit.Assert.*;

public class URLValidatorTest {

    @Test
    public void isValidURLSuccessWithoutProtocolTest() {
        assertTrue(URLValidator.isValidURL("www.orf.at"));
    }

    @Test
    public void isValidURLSuccessWithHTTPProtocolTest() {
        assertTrue(URLValidator.isValidURL("http://www.orf.at"));
    }

    @Test
    public void isValidURLSuccessWithHTTPSProtocolTest() {
        assertTrue(URLValidator.isValidURL("https://www.orf.at"));
    }

    @Test
    public void isInvalidURLWithWrongProtocolTest() {
        assertFalse(URLValidator.isValidURL("httpy://www.orf.at"));
    }

    @Test
    public void isValidURLWithTopDomainLengthEqualThree() {
        assertTrue(URLValidator.isValidURL("http://www.orf.com"));
    }

    @Test
    public void isValidURLWithTopDomainLengthEqualFour() {
        assertTrue(URLValidator.isValidURL("http://www.orf.info"));
    }

    @Test
    public void isInvalidURLWithTopDomainLengthTooBig() {
        assertFalse(URLValidator.isValidURL("http://www.orf.comomom"));
    }

    @Test
    public void isInvalidURLWithTopDomainLengthTooSmall() {
        assertFalse(URLValidator.isValidURL("http://www.orf.c"));
    }

    @Test
    public void isValidURLWithURI() {
        assertTrue(URLValidator.isValidURL("http://www.orf.info/abcd/"));
    }

    @Test
    public void isValidURLWithoutWWW() {
        assertTrue(URLValidator.isValidURL("http://orf.info/abcd"));
    }



    /**
     * Mögliche Tests: Teste einmal alle typen von URLS die valide sein können
     * (mit ohne protokoll, jeweils einmal http|https)
     *
     *  Generelle URL Struktur:
     *  [Protokoll]{?} [www.]{?} ([subdomain].){?} [domain].[top-level-domain] (([/][URI]){?} | [/]{?}) ([?][URL-Params]){?}
     *
     *  {?} ... Optionale Stelligkeit -> 0 oder 1
     *
     * Protokoll: zulässig für uns http|https
     *
     *  www: glaub es gibt auch www2 etc. aber für uns eigtl. wurscht?
     *
     *  subdomain .. egal einfach irgendeine Zeichenkette .. (Vorhandensein kann anhand der Punkte festgestellt werden)
     *
     *  domain -> irgendeine Zeichenkette
     *
     *  top-level-domain (normalerweise 2 zeichen iso code.. oft aber mehr zeichen (com / info / org)
     *
     *  URI: Wenn vorhanden immer mit schrägstrich als prefix.
     *  Schrägstrich kann aber auch ohne URI am Ende der URL vorkommen (optional)
     *
     *  URL-Params: Unterstützen wir einfach nicht.. sonst wirds zu kompliziert..
     *  daher auch kein Fragezeichen am Ende der URL|des URI strings
     */
}
