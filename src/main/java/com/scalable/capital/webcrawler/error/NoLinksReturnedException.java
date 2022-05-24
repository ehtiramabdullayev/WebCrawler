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
