package com.lab7;

import java.io.File;

import org.apache.commons.io.FileUtils;

public class App 
{
    public static void main(String[] args){
        if (args.length != 2){
            System.err.println("RTFM!");
            System.exit(1);
        }
        String downloadPath = ".download/";
        try {
            FileUtils.deleteDirectory(new File(downloadPath));
            Crawler c = new Crawler(downloadPath);
            c.work(args[0], Integer.parseInt(args[1]));
        }
        catch(Exception e){
            System.err.println("Critical error!");
            System.err.println(e.getMessage());
            e.printStackTrace();
            System.exit(1);
        }
    }
}
