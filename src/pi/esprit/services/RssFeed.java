/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pi.esprit.services;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

/**
 *
 * @author bureau
 */
public class RssFeed {
    
     public static void main(String[] args) throws MalformedURLException, IOException  {
         System.out.println(readrss("http://rss.cnn.com/rss/edition.rss"));
     }
    public static String readrss(String urladress) throws MalformedURLException, IOException{
        URL rssurl=new URL(urladress);
        BufferedReader in=new BufferedReader(new InputStreamReader(rssurl.openStream()));
        String sourcecode="";
        String line;
        while((line=in.readLine())!=null){
            if(line.contains("<title>")){
                int firstpos=line.indexOf("<title>");
                String temp=line.substring(firstpos);
                temp=temp.replace("<title>","");
                int lastpos=temp.indexOf("</title>");
                temp=temp.substring(0, lastpos);
                sourcecode += temp+"\n";
            }
        }
        in.close();
         return sourcecode;
    }
  
}
