package CCWebcrawler.Structure;

import CCWebcrawler.URLValidator;

public class Link{

    private static final String defaultProtocoll = "https://";

    public String url;
    public Website target;
    public boolean broken;

    public Link(String url) {
        setUrl(url);
        this.target = null;
        this.broken = false;
    }

    public void setUrl(String url){
        if(!URLValidator.hasProtocol(url))
            this.url = defaultProtocoll+url;
        else
            this.url = url;
    }

    public boolean visited(){
        return target != null && !broken;
    }


    @Override
    public String toString() {
        return url;
    }
}
