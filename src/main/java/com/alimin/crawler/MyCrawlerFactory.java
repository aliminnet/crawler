package com.alimin.crawler;

import edu.uci.ics.crawler4j.crawler.CrawlController;
import edu.uci.ics.crawler4j.crawler.Page;
import edu.uci.ics.crawler4j.crawler.WebCrawler;
import org.apache.xmlbeans.impl.xb.xsdschema.ListDocument;

import java.util.List;

/**
 * @author Ali Minnet
 * @version 2.0
 */
public class MyCrawlerFactory implements CrawlController.WebCrawlerFactory {

    CrawlDataRepository repository;

    List<String> keywords;

    List<String> urls;

    String filterDate;


    public MyCrawlerFactory(CrawlDataRepository repository, List<String> urls, List<String> keywords, String filterDate) {
        this.repository = repository;
        this.urls = urls;
        this.keywords = keywords;
        this.filterDate = filterDate;
    }

    @Override
    public WebCrawler newInstance() {
        return new MyCrawler(repository,urls,keywords,filterDate);
    }
}
