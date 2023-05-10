package com.lab7;

import java.util.LinkedList;

public class URLPool {

    int waitCnt;
    int maxDepth;
    LinkedList<URLDepthPair> visitedUrls = new LinkedList<URLDepthPair>();
    LinkedList<URLDepthPair> unvisitedUrls = new LinkedList<URLDepthPair>();
    int linksProcessed;

    URLPool(String url, int maxDepth){
        unvisitedUrls.add(new URLDepthPair(url));
        waitCnt = 0;
        linksProcessed = 0;
        this.maxDepth = maxDepth;
    }

    public synchronized URLDepthPair pop(){
        while (unvisitedUrls.isEmpty()){
            waitCnt++;
            try{
                wait();
            }
            catch (InterruptedException e){
                ;
            }
            waitCnt--;
        }
        URLDepthPair url = unvisitedUrls.pop();
        linksProcessed++;
        visitedUrls.add(url);
        return url;
    }

    public synchronized void add(URLDepthPair url) {
        if(visitedUrls.contains(url)||url.getDepth()>maxDepth)
        return;
        unvisitedUrls.add(url);
        notify();
    }

    public int getWaitCount(){
        return waitCnt;
    }

    public int getLinksProcessed(){
        return linksProcessed;
    }

    public String toString(){
        String str = "";
        for (URLDepthPair pair: visitedUrls){
            if (!pair.getUrl().isBlank())
                str+=(pair.toString()+"\n");
        }
        return str;
    }
}
