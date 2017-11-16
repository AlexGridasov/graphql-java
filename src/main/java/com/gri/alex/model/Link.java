package com.gri.alex.model;

/**
 * User: Alex
 * Date: 11/16/17
 */
public class Link {

    private final String url;
    private final String description;

    public Link(String url, String description) {
        this.url = url;
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public String getDescription() {
        return description;
    }
}
