import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import CCWebcrawler.URLValidator;

public class URLValidatorTest {

    @Test
    public void isValidURLSuccessWithoutProtocolTest() {
        Assertions.assertTrue(URLValidator.isValidURL("www.orf.at"));
    }

    @Test
    public void isValidURLSuccessWithHTTPProtocolTest() {
        Assertions.assertTrue(URLValidator.isValidURL("http://www.orf.at"));
    }

    @Test
    public void isValidURLSuccessWithHTTPSProtocolTest() {
        Assertions.assertTrue(URLValidator.isValidURL("https://www.orf.at"));
    }

    @Test
    public void isInvalidURLWithWrongProtocolTest() {
        Assertions.assertFalse(URLValidator.isValidURL("httpy://www.orf.at"));
    }

    @Test
    public void isValidURLWithTopDomainLengthEqualThree() {
        Assertions.assertTrue(URLValidator.isValidURL("http://www.orf.com"));
    }

    @Test
    public void isValidURLWithTopDomainLengthEqualFour() {
        Assertions.assertTrue(URLValidator.isValidURL("http://www.orf.info"));
    }

    @Test
    public void isInvalidURLWithTopDomainLengthTooBig() {
        Assertions.assertFalse(URLValidator.isValidURL("http://www.orf.comomom"));
    }

    @Test
    public void isInvalidURLWithTopDomainLengthTooSmall() {
        Assertions.assertFalse(URLValidator.isValidURL("http://www.orf.c"));
    }

    @Test
    public void isValidURLWithURI() {
        Assertions.assertTrue(URLValidator.isValidURL("http://www.orf.info/abcd/"));
    }

    @Test
    public void isValidURLWithoutWWW() {
        Assertions.assertTrue(URLValidator.isValidURL("http://orf.info/abcd"));
    }
}
