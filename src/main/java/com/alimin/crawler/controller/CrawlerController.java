package com.alimin.crawler.controller;

import com.alimin.crawler.CrawlData;
import com.alimin.crawler.CrawlDataRepository;
import com.alimin.crawler.CrawlService;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

/**
 * @author Ali Minnet
 * @version 2.0
 */
@Controller
public class CrawlerController {

    @Autowired
    CrawlService crawlService;

    @Autowired
    CrawlDataRepository repository;

    @GetMapping("/crawl")
    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public String crawl(@RequestParam(name="urls", required=false) List<String> urls,
                           @RequestParam(name="keywords", required=false) List<String> keywords,
                           @RequestParam(name="filterDate", required=false, defaultValue="2010-01-01") String filterDate) throws IOException {
        crawlService.setFilterDate(filterDate);
        crawlService.setUrls(urls);
        crawlService.setKeywords(keywords);
        try {
            crawlService.init();
        } catch (Exception e){
            //
        }
        return "Crawl Started";
    }

    @GetMapping("/getData")
    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public List<CrawlData> getData(){
        List<CrawlData> results = repository.findAll();
        return Lists.reverse(results).subList(0,5);
    }
}
