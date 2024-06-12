package HtmlParser;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public interface HtmlParserAdapter {

    ArrayList<String> getLinks(String website) throws IOException;

    HashMap<String, Integer> getHeadings(String website) throws IOException;

}
