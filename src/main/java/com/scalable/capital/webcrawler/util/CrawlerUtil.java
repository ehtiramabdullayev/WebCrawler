package com.scalable.capital.webcrawler.util;

import com.scalable.capital.webcrawler.bean.LinkBean;
import com.scalable.capital.webcrawler.core.CheckSum;
import com.scalable.capital.webcrawler.error.NoLibrariesReturnedException;
import com.scalable.capital.webcrawler.error.NoLinksReturnedException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 *
 * @author Ehtiram Abdullayev
 */
public class CrawlerUtil {

    public static List<String> getGoogleLinks(String content, String selector) throws NoLinksReturnedException {
        ArrayList<String> resultLinks = new ArrayList<>();
        try {
            Document doc = Jsoup.parse(content);
            Elements els = doc.select(selector);

            for (Element e : els) {
                resultLinks.add(e.attr("href").replace("/url?q=", ""));
            }
            if (resultLinks.isEmpty()) {
                throw new NoLinksReturnedException("No links returned from google, something went wrong !");
            }
        } catch (Exception e) {
            System.out.println("Get gooogle link " + e.getLocalizedMessage());
        }

        return resultLinks;
    }

    public static ArrayList<LinkBean> getJsLibrariesFromLink(String url) {
        ArrayList<LinkBean> libraryBeans = new ArrayList<>();
        try {
            String a = HttpUtil.getPage(url);
            Document docJava = Jsoup.parse(a);
            Elements elements = docJava.select("script");
            for (Element elem : elements) {
                LinkBean bean = new LinkBean();
                String jsUrl = elem.attr("src").trim();
                if (jsUrl.length() > 0) {
                    if (jsUrl.startsWith("/")) {
                        jsUrl = url + jsUrl;
                    }
                    String fileName = jsUrl.substring(jsUrl.lastIndexOf('/') + 1, jsUrl.length());
                    bean.setName(fileName);
                    bean.setUrl(jsUrl);
                    bean.setChecksum(CheckSum.checkSumFile(HttpUtil.downloadUrl(bean.getUrl())));
                    libraryBeans.add(bean);
                }

            }
        } catch (Exception ex) {
            System.out.println("Could't get library from link " + ex.getLocalizedMessage());
        }
        return libraryBeans;
    }

    public static HashMap<String, Integer> printTopFiveLibraries(ArrayList<LinkBean> librarylist) throws NoLibrariesReturnedException {
        if (librarylist.isEmpty()) {
            throw new NoLibrariesReturnedException("No libraries returned from all of the search!");
        }

        deduplicateNames(librarylist);
        HashMap<String, Integer> countOfLibs = new HashMap<>();
        HashMap<String, Integer> sortedMap = new HashMap<>();
        LinkedHashMap<String, Integer> fiveLib = new LinkedHashMap<>();

        try {
            for (LinkBean library : librarylist) {
                String libName = library.getName();
                if (countOfLibs.containsKey(libName)) {
                    countOfLibs.put(libName, countOfLibs.get(libName) + 1);
                } else {
                    countOfLibs.put(libName, 1);
                }
            }
            sortedMap = GeneralUtils.sortByValue(countOfLibs);
            int count = 0;
            for (Map.Entry<String, Integer> sort : sortedMap.entrySet()) {
                count++;
                if (count > 5) {
                    break;
                } else {
                    fiveLib.put(sort.getKey(), sort.getValue());
                }

            }
        } catch (Exception e) {
            System.err.println("Error occured " + e.getLocalizedMessage());

        }
        return fiveLib;

    }

    public static void deduplicateNames(ArrayList<LinkBean> librarylist) {
        for (LinkBean outerL : librarylist) {
            for (LinkBean innerL : librarylist) {
                if (outerL.getChecksum().equals(innerL.getChecksum())) {
                    innerL.setName(outerL.getName());
                }
            }
        }

    }

}
