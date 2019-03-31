package com.alimin.crawler;

/**
 * @author Ali Minnet
 * @version 2.0
 */
public class CrawlData {

    String title;

    String text;

    String html;

    String date;

    String url;

    public CrawlData(String title, String text, String html, String date, String url) {
        this.title = title;
        this.text = text;
        this.html = html;
        this.date = date;
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getHtml() {
        return html;
    }

    public void setHtml(String html) {
        this.html = html;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
