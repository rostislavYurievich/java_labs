package com.lab7;

import java.util.LinkedList;
import java.util.Scanner;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
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

    private boolean isDownloaded(URLDepthPair udp){
        return visitedUrls.contains(udp);
    }

    private void parse(String path,int depth) throws FileNotFoundException{
        File file = new File(path);
        Scanner scanner = new Scanner(file);
        while(scanner.hasNextLine()) {
            String s = scanner.nextLine();
            Document doc = Jsoup.parse(s);
            Element link = doc.select("a").first();
            unvisitedUrls.add(new URLDepthPair(link.attr("href")));
        }
        scanner.close();
    }

    public void work(String startUrl,int depth) throws IOException{
        unvisitedUrls.add(new URLDepthPair(startUrl,0));
        while (!unvisitedUrls.isEmpty()){
            URLDepthPair udp = unvisitedUrls.getFirst();
            if ((udp.getDepth()<=depth)&&(!isDownloaded(udp))){
                String url = udp.getUrl();
                URL site = new URL(url);
                File file = new File(downloadPath+site.getPath());
                download(site,file);
                parse(url,udp.getDepth()+1);
            }
            visitURL(udp);
        }
    }

    public static void main(String[] args){
        if (args.length != 2){
            System.err.println("RTFM!");
            System.exit(1);
        }
        try {
            String downloadPath = "./download";
            Crawler c = new Crawler(downloadPath);
            c.work(args[0], Integer.parseInt(args[1]));
        }
        catch(Exception e){
            System.err.println("Critical error!");
            System.err.println(e.getMessage());
            System.err.println(e.getStackTrace());
            System.exit(1);
        }
    }
}