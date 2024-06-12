package CCWebcrawler.Structure;

import CCWebcrawler.URLValidator;

public class Link {

    private static final String defaultProtocol = "https://";

    public String url;
    private Website destination;
    private boolean isBroken;

    public Link(String url) {
        setUrl(url);
        this.destination = null;
        this.isBroken = false;
    }

    public void setUrl(String url) {
        if (!URLValidator.hasProtocol(url))
            this.url = defaultProtocol + url;
        else
            this.url = url;
    }

    public boolean visited() {
        return destination != null && !isBroken;
    }

    public void setDestination(Website destination) {
        if (destination != null) {
            this.isBroken = false;
            this.destination = destination;
        }
    }

    public void setBroken() {
        this.isBroken = true;
        this.destination = null;
    }

    public Website getDestination() {
        return this.destination;
    }

    public boolean isBroken() {
        return isBroken;
    }

    @Override
    public String toString() {
        return url;
    }
}
