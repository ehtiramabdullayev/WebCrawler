package com.scalable.capital.webcrawler;

import com.scalable.capital.webcrawler.util.CrawlerUtil;
import com.scalable.capital.webcrawler.util.HttpUtil;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
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
public class Main {

    public static void main(String[] args) throws IOException {
        Scanner scan = new Scanner(System.in);
        while (true) {
            System.out.println("Enter the phrase to google it !");
            
            String searchTerm = scan.nextLine();
            
            if(searchTerm.equals("quit")){
                break;
            }
            String googleResult = HttpUtil.getPage("https://www.google.com/search?q=" + searchTerm);
            System.out.println(" utillll + " + CrawlerUtil.getGoogleLinks(googleResult, "h3.r a"));
            List<String> foundedUrls = new ArrayList<>();

            foundedUrls = CrawlerUtil.getGoogleLinks(googleResult, "h3.r a");
            ArrayList<String> list = new ArrayList<>();
            for (String foundUrl : foundedUrls) {
                list.addAll(CrawlerUtil.getJsLibrariesFromLink(foundUrl));

            }
            System.out.println("aaa" + list);

        }
    }

}
