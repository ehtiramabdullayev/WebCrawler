package com.scalable.capital.webcrawler;

import com.scalable.capital.webcrawler.bean.LinkBean;
import com.scalable.capital.webcrawler.util.CrawlerUtil;
import com.scalable.capital.webcrawler.util.GeneralUtils;
import com.scalable.capital.webcrawler.util.HttpUtil;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Ehtiram Abdullayev
 */
public class Main {

    public static void main(String[] args) throws IOException {
        Scanner scan = new Scanner(System.in);
        while (true) {
            System.out.println("Enter the phrase to google it ! (Type --quit when you want to exit)");

            String searchTerm = scan.nextLine();

            if (searchTerm.equals("--quit")) {
                break;
            }
            String googleResult = HttpUtil.getPage("https://www.google.com/search?q=" + URLEncoder.encode(searchTerm,"UTF-8"));


            
            List<String> foundedUrls = new ArrayList<>();
            ArrayList<LinkBean> allJsLibraries = new ArrayList<>();
            
            foundedUrls = CrawlerUtil.getGoogleLinks(googleResult, "h3.r a");
            
            for (String foundUrl : foundedUrls) {
                ArrayList<LinkBean> libs= CrawlerUtil.getJsLibrariesFromLink(foundUrl);
                allJsLibraries.addAll(libs);

            }
          //  System.out.println(allJsLibraries);

             System.out.println("list : " + CrawlerUtil.printTopFiveLibraries(allJsLibraries));
        }
    }

}
