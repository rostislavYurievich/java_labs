package com.lab7;

import java.util.LinkedList;
import java.util.Scanner;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;


class Crawler {
    public static final String URL_PREFIX = "http://";
    LinkedList<URLDepthPair> visitedUrls = new LinkedList<URLDepthPair>();
    LinkedList<URLDepthPair> unvisitedUrls = new LinkedList<URLDepthPair>();
    String downloadPath;

    Crawler(String downloadPath){
        this.downloadPath = downloadPath;
    }

    public LinkedList<URLDepthPair> getSites()
    {
        return visitedUrls;
    }

    private void download(URL site,File file) throws IOException{
        ReadableByteChannel rbc = Channels.newChannel(site.openStream());
        FileOutputStream fos = new FileOutputStream(file);
        try {
            fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
            }
        catch (Exception x){
            System.out.println("File IO error!");
        }
        fos.close();
        
    }
    private void visitURL(URLDepthPair udp){
        visitedUrls.add(udp);
        unvisitedUrls.remove(udp);
    }

    private boolean downloadURL(URLDepthPair udp){
        return !visitedUrls.contains(udp);
    }

    private void parse(String path,int depth){
        File file = new File(path);
        Scanner s = new Scanner(file);
        s.useDelimiter("</a>");
        while (s.hasNext()){
            str = s.
        }
        str.substring(str.indexOf("<a href =") + 1, str.indexOf(">"));

        for (int i = 1; i<result.groupCount(); i++){
            udp = new URLDepthPair(result.group(i),depth)
            
            unvisitedUrls.add();
        }
        s.close();
    }

    public void work(String startUrl,int depth) throws MalformedURLException{
        unvisitedUrls.add(new URLDepthPair(startUrl,0));
        for (URLDepthPair udp: unvisitedUrls){
            if (udp.getDepth()<=depth){
                String url = udp.getUrl();
                URL site = new URL(URL_PREFIX+url);
                File file = new File(downloadPath+site.getPath());
                parse(url,udp.getDepth()+1);
            }
            visitURL(udp);
        }
    }
}
