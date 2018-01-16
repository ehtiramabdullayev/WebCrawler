package com.scalable.capital.webcrawler.core;

import com.scalable.capital.webcrawler.bean.LinkBean;
import com.scalable.capital.webcrawler.error.NoLibrariesReturnedException;
import com.scalable.capital.webcrawler.error.NoLinksReturnedException;
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
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Ehtiram Abdullayev
 */
public class Main {

    static final String GOOGLE_SEARCH_LINK = "https://www.google.com/search?q=";

    public static void main(String[] args) throws IOException {
        try {
            Scanner scan = new Scanner(System.in);
            ArrayList<LinkBean> allJSLibraries = new ArrayList<>();
            while (true) {
                System.out.println("Enter the phrase to google it ! (Type --quit when you want to exit)");
                String searchTerm = scan.nextLine();
                if (searchTerm.equals("--quit")) {
                    break;
                }
                String googleResult = HttpUtil.getPage(GOOGLE_SEARCH_LINK + URLEncoder.encode(searchTerm, "UTF-8"));
                List<String> foundedUrls = new ArrayList<>();
                foundedUrls = CrawlerUtil.getGoogleLinks(googleResult, "h3.r a");

                for (String foundUrl : foundedUrls) {
                    ArrayList<LinkBean> libsInURL = CrawlerUtil.getJsLibrariesFromLink(foundUrl);
                    allJSLibraries.addAll(libsInURL);

                }
                System.out.println("Top five  : " + CrawlerUtil.printTopFiveLibraries(allJSLibraries));
            }

        } catch (NoLinksReturnedException e) {
            System.out.println(e.getLocalizedMessage());
        } catch (NoLibrariesReturnedException ex) {
            System.out.println(ex.getLocalizedMessage());
        } catch (Exception exception) {
            System.out.println(exception.getLocalizedMessage());
        }
    }
}
