package CCWebcrawler;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Website {

    private final String url;

    private final ArrayList<String> links;

    private final ArrayList<HtmlHeading> headings;

    public int depth;

    private final ArrayList<Website> children;



    public Website(String url, ArrayList<String> links, ArrayList<HtmlHeading> headings, int depth){
        this.url = url;
        this.links = links;
        this.headings = headings;
        this.depth = depth;

        this.children = new ArrayList<>();
    }


    public void addChild(Website site){
        this.children.add(site);
    }

    public Stream<Website> getAllCrawledSubSites(){
        Stream<Website> res =  this.children.stream().flatMap(Website::getAllCrawledSubSites);

        if (this.depth != 0){ //if not depth 0, add this instance to resultSet
         List<Website> intermediate = res.collect(Collectors.toList());
         intermediate.add(this);
         res = intermediate.stream();
        }
        return res;
    }

    public Stream<Website> getChildrenAtDepth(int depth){
        if(depth > this.depth)
            return this.children.stream()
                    .flatMap(x -> x.getChildrenAtDepth(depth));
        else return Stream.of(this);
    }

    public ArrayList<HtmlHeading> getHeadings() {
        return this.headings;
    }

    public ArrayList<String> getLinks() {
        return this.links;
    }

    public String getUrl(){
        return this.url;
    }

    @Override
    public String toString() {
        return "Website{" +
                "url='" + url + '\'' +
                ", links=" + links +
                ", headings=" + headings +
                ", depth=" + depth +
                ", children: { \n" + children +
                "\n }";
    }

}
