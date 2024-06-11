package CCWebcrawler.Structure;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Website {

    private final String url;

    private final Set<Link> links;

    private final ArrayList<HtmlHeading> headings;

    public int depth;


    public Website(String url, ArrayList<Link> links, ArrayList<HtmlHeading> headings, int depth){
        this.url = url;
        this.links = new HashSet<>(links);
        this.headings = headings;
        this.depth = depth;
    }


    public Stream<Website> getAllCrawledSubSites(){
        Stream<Website> res =  this.links.stream().map(x -> x.target).flatMap(Website::getAllCrawledSubSites);

        if (this.depth != 0){ //if not depth 0, add this instance to resultSet
         List<Website> intermediate = res.collect(Collectors.toList());
         intermediate.add(this);
         res = intermediate.stream();
        }
        return res;
    }


    public Stream<Website> getChildrenAtDepth(int depth){
        if(depth > this.depth)
            return this.links.stream()
                    .filter(x -> !x.broken && x.target != null)
                    .flatMap(x -> x.target.getChildrenAtDepth(depth));
        else return Stream.of(this);
    }

    public Stream<Link> getLinksAtDepth(int depth){
        if(depth > this.depth)
            return this.links.stream()
                    .filter(x -> !x.broken && x.target != null)
                    .flatMap(x -> x.target.getLinksAtDepth(depth));
        else return this.links.stream();
    }



    public ArrayList<HtmlHeading> getHeadings() {
        return this.headings;
    }


    public Set<Link> getLinks() {
        return this.links;
    }


    public String getUrl(){
        return this.url;
    }

    public void addChild(Link child){
        this.links.add(child);
    }



    @Override
    public String toString() {
        return "Website{" +
                "url='" + url + '\'' +
                ", links=" + links +
                ", headings=" + headings +
                ", depth=" + depth +
                "\n }";
    }

}
