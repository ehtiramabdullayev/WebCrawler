/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.scalable.capital.webcrawler.util;

import com.scalable.capital.webcrawler.bean.LinkBean;
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

    public static ArrayList<LinkBean> getJsLibrariesFromLink(String url) {
         ArrayList<LinkBean> linkBeans = new ArrayList<>();
        try {
            String a = HttpUtil.getPage(url);
            Document docJava = Jsoup.parse(a);
            Elements elements = docJava.select("script");
            for (Element elem : elements) {
                LinkBean bean = new LinkBean();
                String jsUrl = elem.attr("src").trim();
                if (jsUrl.length() > 0) {
                    String fileName = jsUrl.substring(jsUrl.lastIndexOf('/') + 1, jsUrl.length());
                    bean.setName(fileName);
                    bean.setUrl(jsUrl);
                    bean.setChecksum(GeneralUtils.getSHA1(HttpUtil.downloadUrl(bean.getUrl())));
                    linkBeans.add(bean);
                }

            }
        } catch (IOException ex) {
            Logger.getLogger(CrawlerUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
        return linkBeans;
    }

    public static HashMap<String, Integer> printTopLibraries(ArrayList<LinkBean> librarylist) {

        HashMap<String, Integer> countOfLibs = new HashMap<>();
       
        for (LinkBean library : librarylist) {
            String libName = library.getName();
            if (countOfLibs.containsKey(libName)) {
                countOfLibs.put(libName, countOfLibs.get(libName)+1);
            } else {
                countOfLibs.put(libName, 1);
            }
        }
        HashMap<String, Integer> sortedMap = GeneralUtils.sortByValue(countOfLibs);
        
        System.out.println("sortedMapsortedMapsortedMapsortedMapsortedMap    : " + sortedMap);
        return sortedMap;
    }

}
