/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.scalable.capital.webcrawler.bean;

/**
 *
 * @author Ehtiram Abdullayev
 */
public class LinkBean {
    private String url;
    private String name;
    private String checksum;

    public LinkBean() {
    }

    public LinkBean(String url, String name, String checksum) {
        this.url = url;
        this.name = name;
        this.checksum = checksum;
    }
    
    

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getChecksum() {
        return checksum;
    }

    public void setChecksum(String checksum) {
        this.checksum = checksum;
    }

    @Override
    public String toString() {
        return "LinkBean{" + "url=" + url + ", name=" + name + ", checksum=" + checksum + '}';
    }
    
    
    
    
    
}
