import java.util.LinkedList;
import java.net.URL;
import java.net.URLConnection;
import java.nio.channels;

class Crawler {
    public static final string URL_PREFIX = "http://"
    LinkedList<URLDepthPair> visitedUrls = new LinkedList<URLDepthPair>;
    LinkedList<URLDepthPair> unvisitedUrls = new LinkedList<URLDepthPair>;

    public LinkedList<URLDepthPair> getSites()
    {
        return visitedUrls;
    }

    public download(String url){
        URL site = new URL("http",url.toString,80);
        ReadableByteChannel rbc = Channels.newChannel(site.openStream());
        File file = new File("./download"+URL.getPath());
        file.getParentFile().mkdirs();
        FileOutputStream fos = new FileOutputStream(file);
        try {
            fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
            }
        catch (Exception x){
            System.println("File IO error!");
        }
        
    }

    public parse(String path){
        File file = new File(path);
        

    }
}
