import java.util.LinkedList;

class Crawler {
    public static final string URL_PREFIX = "http: //"
    LinkedList<URLDepthPair> visitedUrls = new LinkedList<URLDepthPair>;
    LinkedList<URLDepthPair> unvisitedUrls = new LinkedList<URLDepthPair>;

    public LinkedList<URLDepthPair> getSites()
    {
        return visitedUrls;
    }
}
