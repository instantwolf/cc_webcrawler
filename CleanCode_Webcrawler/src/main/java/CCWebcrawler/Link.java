package CCWebcrawler;

public class Link{

    public String url;
    public Website target;
    public boolean broken;

    public Link(String url) {
        this.url = url;
        this.target = null;
        this.broken = false;
    }

    public boolean visited(){
        return target != null || broken;
    }

    public void setBroken(){
        this.broken = true;
    }

    @Override
    public String toString() {
        return url;
    }
}
