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

    public void download(String url){
        URL site = new URL("http",url.toString,80);
        ReadableByteChannel rbc = Channels.newChannel(site.openStream());
        File file = new File("./download/"+URL.getPath());
        file.getParentFile().mkdirs();
        FileOutputStream fos = new FileOutputStream(file);
        try {
            fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
            }
        catch (Exception x){
            System.println("File IO error!");
        }
        
    }

    public parse(String path,int depth){
        File file = new File(path);
        Scanner s = new Scanner(input);
        s.findInLine("<a\s+(?:[^>]*?\s+)?href=([\"'])(.*?)\1");
        MatchResult result = s.match();
        for (int i = 1; i<result.groupCount(); i++){
            unvisitedUrls.add(new URLDepthPair(result.group(i),depth));
        }
        s.close();

        

    }
}
