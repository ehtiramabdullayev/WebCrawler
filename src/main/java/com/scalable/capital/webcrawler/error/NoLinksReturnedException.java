/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.scalable.capital.webcrawler.error;

/**
 *
 * @author Ehtiram Abdullayev
 */
public class NoLinksReturnedException extends Exception{

    public NoLinksReturnedException() {
    }

    public NoLinksReturnedException(String string) {
        super(string);
    }
    
}
