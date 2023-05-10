package com.lab7;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicBoolean;

import org.apache.commons.io.FileUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class CrawlerTask implements Runnable{
    URLPool urlPool;
    String downloadPath;
    AtomicBoolean running;

    CrawlerTask(URLPool urlPool, String downloadPath){
        this.urlPool = urlPool;
        this.downloadPath = downloadPath;
    }


    public void run(){
        while (true){
            URLDepthPair udp = urlPool.pop();
            String url = udp.getUrl();
            URL site;
            try{
                site = new URL(url);
                File file = new File(downloadPath+(site.toString().replace(site.getProtocol()+"://","")));
                download(site,file);
                ArrayList<URLDepthPair> urls = parse(file,udp.getDepth()+1);
                for (URLDepthPair u: urls){
                    urlPool.add(u);
                }
            }
            catch(Exception e){
                ;
            }
        }

    }

    private void download(URL site, File file) throws IOException{
        FileUtils.copyURLToFile(site, file,500,500);     
    }

    private ArrayList<URLDepthPair> parse(File file,int depth) throws FileNotFoundException{
        Scanner scanner = new Scanner(file);
        ArrayList<URLDepthPair> urls = new ArrayList<>();
        while(scanner.hasNextLine()) {
            String s = scanner.nextLine();
            Document doc = Jsoup.parse(s);
            Element link = doc.select("a").first();
            if (link!=null)
                urls.add(new URLDepthPair(link.attr("href"),depth));
        }
        scanner.close();
        return urls;
    }
}
