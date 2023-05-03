import java.util.LinkedList;
import java.net.URL;
import java.net.URLConnection;
import java.nio.channels;

class Crawler {
    public static final string URL_PREFIX = "http://"
    LinkedList<URLDepthPair> visitedUrls = new LinkedList<URLDepthPair>;
    LinkedList<URLDepthPair> unvisitedUrls = new LinkedList<URLDepthPair>;
    String downloadPath;

    Crawler(String downloadPath){
        this.downloadPath = downloadPath;
    }

    public LinkedList<URLDepthPair> getSites()
    {
        return visitedUrls;
    }

    private void download(String url){
        URL site = new URL("http",url.toString,80);
        ReadableByteChannel rbc = Channels.newChannel(site.openStream());
        File file = new File("./"+downloadPath+"/"+URL.getPath());
        file.getParentFile().mkdirs();
        FileOutputStream fos = new FileOutputStream(file);
        try {
            fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
            }
        catch (Exception x){
            System.println("File IO error!");
        }
        
    }
    private void visitURL(URLDepthPair udp){
        visitedUrls.
    }

    private boolean downloadURL(URLDepthPair udp){
        if (!visitedUrls.contains(udp)){
            return true;
        }
        existingUrl = visitedUrls.get(visitedUrls.indexOf(udp));
        if (existingUrl.fullMatch(udp)){
            return false;
        }
        else{
            
        }
    }

    private void parse(String path,int depth){
        File file = new File(path);
        Scanner s = new Scanner(input);
        s.findInLine("<a\s+(?:[^>]*?\s+)?href=([\"'])(.*?)\1");
        MatchResult result = s.match();
        for (int i = 1; i<result.groupCount(); i++){
            udp = new URLDepthPair(result.group(i),depth)
            
            unvisitedUrls.add();
        }
        s.close();
    }

    public void work(int depth){
        for (URLDepthPair udp: unvisitedUrls){
            if (udp.getDepth()<=depth){
                String url = udp.getUrl();
                download(url);
                parse(url,udp.getDepth()++);

            }
        }
    }
}
