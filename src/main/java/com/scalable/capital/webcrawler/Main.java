package com.scalable.capital.webcrawler;

import com.scalable.capital.webcrawler.bean.LinkBean;
import com.scalable.capital.webcrawler.util.CrawlerUtil;
import com.scalable.capital.webcrawler.util.GeneralUtils;
import com.scalable.capital.webcrawler.util.HttpUtil;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
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
            System.out.println("Enter the phrase to google it !");

            String searchTerm = scan.nextLine();

            if (searchTerm.equals("quit")) {
                break;
            }
            String googleResult = HttpUtil.getPage("https://www.google.com/search?q=" + searchTerm);

            String everything = null;
            try (BufferedReader br = new BufferedReader(new FileReader("a.txt"))) {
                StringBuilder sb = new StringBuilder();
                String line = br.readLine();

                while (line != null) {
                    sb.append(line);
                    sb.append(System.lineSeparator());
                    line = br.readLine();
                }
                everything = sb.toString();
            }

            googleResult = everything;
            System.out.println(everything);
         
            
            List<String> foundedUrls = new ArrayList<>();
            ArrayList<LinkBean> allJsLibraries = new ArrayList<>();
            
            foundedUrls = CrawlerUtil.getGoogleLinks(googleResult, "h3.r a");
            
            ArrayList<String> list = new ArrayList<>();
            for (String foundUrl : foundedUrls) {
                System.out.println(GeneralUtils.getSHA1(HttpUtil.downloadUrl(foundUrl)));
                allJsLibraries.addAll(CrawlerUtil.getJsLibrariesFromLink(foundUrl));

            }
            System.out.println(allJsLibraries);

            // System.out.println("list : " + CrawlerUtil.printTopLibraries(hm));
        }
    }

}
