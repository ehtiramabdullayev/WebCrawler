/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.scalable.capital.webcrawler.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 *
 * @author Ehtiram Abdullayev
 */
public class CrawlerUtil {

    public static List<String> getGoogleLinks(String content, String selector) {
        ArrayList<String> resultLinks = new ArrayList<>();
        Document doc = Jsoup.parse(content);
        // "h3.r a"  

        Elements els = doc.select(selector);

        for (Element e : els) {

            resultLinks.add(e.attr("href").replace("/url?q=", ""));

        }
        return resultLinks;
    }

    public static ArrayList<String> getJsLibrariesFromLink(String url) {
        ArrayList<String> jsLibraries = new ArrayList<>();
        HashMap<String,String> map = new HashMap<>();
        try {
            String a = HttpUtil.getPage(url);
            Document docJava = Jsoup.parse(a);
            Elements elements = docJava.select("script");
            for (Element elem : elements) {                
                String hashIndx = elem.attr("src").trim();
                if (hashIndx.length() > 0) {
                    char[] hashArr = hashIndx.toCharArray();
                    int indxSlash = 0;
                    int countIdx = 0;
                    int len = hashArr.length - 1;
                    for (int i = len; i >= 0; i--) {
                        if (hashArr[i] == '/') {
                            countIdx++;
                            if (countIdx == 1) {
                                indxSlash = i;
                                String jsLib = hashIndx.substring(indxSlash + 1, len + 1);
                                if (jsLib.contains("?")) {
                                    jsLib = jsLib.substring(0, jsLib.indexOf("?"));
                                }
                                map.put(hashIndx, jsLib);
                                System.out.println(map);
                                jsLibraries.add(jsLib);
                                break;
                            }
                        }

                    }

                }

            }
        } catch (IOException ex) {
            Logger.getLogger(CrawlerUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
        return jsLibraries;
    }

    public static HashMap<String,Integer> printTopLibraries(ArrayList<String> libraries){
        HashMap<String,Integer> hashMap = new HashMap<>();
        for(String library : libraries){
            if(hashMap.containsKey(library)){
                hashMap.put(library, hashMap.get(library)+1);
            }
            else
                hashMap.put(library, 1);
        }
        HashMap<String, Integer> sortedMap = GeneralUtils.sortByValue(hashMap);
        return  sortedMap;
    }
    
}
