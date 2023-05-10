package com.lab7;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;

public class App 
{
    public static void main(String[] args) throws IOException{
        if (args.length != 3){
            System.err.println("RTFM!\n Crawler <url> <depth> <numThreads>");
            System.exit(1);
        }

        String downloadPath = ".download/";

        FileUtils.deleteDirectory(new File(downloadPath));
        URLPool pool = new URLPool(args[0], Integer.parseInt(args[1]));
        int numThreads = Integer.parseInt(args[2]);
        for (int i = 0; i < numThreads; i++) {
            CrawlerTask c = new CrawlerTask(pool,downloadPath);
            Thread t = new Thread(c);
            t.start();
        }
        
        while (pool.getWaitCount() != numThreads) {
            try {
                Thread.sleep(500); 
                System.out.print("\r"+pool.getLinksProcessed()+" Links processed ");
            } 
            catch (InterruptedException ie) {
                System.out.println("\rCaught unexpected " + "InterruptedException, ignoring...");
            }
        }
        System.out.println("\r"+pool.toString());
        System.out.println(pool.getLinksProcessed()+" Links processed");
        System.exit(0);
    }
}
