package CCWebcrawler;


import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class URLValidator {

    private static final String URL_REGEX =
            "^((http|https):\\/\\/)?[a-zA-Z0-9]+(([\\-\\.])?[a-zA-Z0-9]*)\\.[a-zA-Z]{2,5}(\\/\\w*)*$";

    private static final String HASPROTOCOL_REGEX =
            "^(http|https):\\/\\/.*";

    private static final Pattern URL_PATTERN = Pattern.compile(URL_REGEX);

    private static final Pattern HASPROTOCOL_PATTERN = Pattern.compile(HASPROTOCOL_REGEX);

    public static boolean isValidURL(String url) {
        Matcher matcher = URL_PATTERN.matcher(url);
        return matcher.matches();
    }

    public static boolean hasProtocol(String url) {
        Matcher matcher = HASPROTOCOL_PATTERN.matcher(url);
        return matcher.matches();
    }
}