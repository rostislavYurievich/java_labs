package com.lab7;

import java.util.LinkedList;

public class URLPool {

    int waitCnt = 0;
    LinkedList<URLDepthPair> visitedUrls = new LinkedList<URLDepthPair>();
    LinkedList<URLDepthPair> unvisitedUrls = new LinkedList<URLDepthPair>();

    URLPool(String url){
        unvisitedUrls.add(new URLDepthPair(url));
    }

    public synchronized URLDepthPair pop(){
        while (unvisitedUrls.isEmpty()){
            waitCnt++;
            try{
                wait();
            }
            catch (InterruptedException e){
                System.err.println(e.getMessage());
            }
            waitCnt--;
        }
        URLDepthPair url = unvisitedUrls.pop();
        visitedUrls.add(url);
        return url;
    }

    public synchronized void add(URLDepthPair url) {
        if(visitedUrls.contains(url))
        return;
        unvisitedUrls.add(url);
        notify();
    }
}
