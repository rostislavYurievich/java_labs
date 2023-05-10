package com.lab7;

import java.util.LinkedList;
import java.util.Scanner;

import org.apache.commons.io.FileUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;



class Crawler {
    public static final String URL_PREFIX = "http://";
    LinkedList<URLDepthPair> visitedUrls = new LinkedList<URLDepthPair>();
    LinkedList<URLDepthPair> unvisitedUrls = new LinkedList<URLDepthPair>();
    String downloadPath;

    Crawler(String downloadPath){
        this.downloadPath = downloadPath;
        File dir = new File(downloadPath);
        if (!dir.exists()){
            dir.mkdirs();
        }
    }

    public LinkedList<URLDepthPair> getSites()
    {
        return visitedUrls;
    }

    private void download(URL site,File file) throws IOException{
        try{
            FileUtils.copyURLToFile(site, file,500,500);    
        }
        catch(IOException e){
            System.err.println(e.getMessage());
        }   

    }
    private void visitURL(URLDepthPair udp){
        visitedUrls.add(udp);
        unvisitedUrls.remove(udp);
    }

    private boolean isDownloaded(URLDepthPair udp){
        return visitedUrls.contains(udp);
    }

    private void parse(File file,int depth) throws FileNotFoundException{
        Scanner scanner = new Scanner(file);
        while(scanner.hasNextLine()) {
            String s = scanner.nextLine();
            Document doc = Jsoup.parse(s);
            Element link = doc.select("a").first();
            if (link!=null)
                unvisitedUrls.add(new URLDepthPair(link.attr("href"),depth));
        }
        scanner.close();
    }

    public void work(String startUrl,int depth) throws IOException{
        unvisitedUrls.add(new URLDepthPair(startUrl));
        while (!unvisitedUrls.isEmpty()){
            URLDepthPair udp = unvisitedUrls.getFirst();
            if ((udp.getDepth()<=depth)&&(!isDownloaded(udp))){
                String url = udp.getUrl();
                URL site;
                try{
                    site = new URL(url);
                }
                catch (MalformedURLException e){
                    System.err.println(url+" MalformedURL!!!");
                    visitURL(udp);
                    continue;
                }
                
                File file = new File(downloadPath+(site.toString().replace(site.getProtocol()+"://","")));
                if(!file.getAbsolutePath().endsWith(".html")){
                    file = new File(file.getAbsolutePath()+"/index.html");
                }
                System.out.println(site.toString());
                download(site,file);
                try{
                    parse(file,udp.getDepth()+1);
                }
                catch (FileNotFoundException e){
                    visitURL(udp);
                    continue;
                }
            }
            visitURL(udp);
        }
    }

    
}