package com.alimin.crawler;

import edu.uci.ics.crawler4j.crawler.Page;
import edu.uci.ics.crawler4j.crawler.WebCrawler;
import edu.uci.ics.crawler4j.parser.HtmlParseData;
import edu.uci.ics.crawler4j.url.WebURL;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;

/**
 * @author Ali Minnet
 * @version 2.0
 */
public class MyCrawler extends WebCrawler {

    private final static Pattern FILTERS = Pattern.compile(".*(\\.(css|js|gif|jpg"
            + "|png|mp3|mp4|zip|gz))$");

    CrawlDataRepository repository;

    List<String> keywords;

    List<String> urls;

    String filterDate;


    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-mm-dd");

    public MyCrawler(CrawlDataRepository repository, List<String> urls, List<String> keywords, String filterDate) {
        this.repository = repository;
        this.urls = urls;
        this.keywords = keywords;
        this.filterDate = filterDate;
    }


    @Override
    public boolean shouldVisit(Page referringPage, WebURL url) {
        String href = url.getURL().toLowerCase();

        boolean visit = this.urls.stream().anyMatch(pageUrl -> {
            WebURL webURL = new WebURL();
            webURL.setURL(pageUrl);
            return url.getDomain().contains(webURL.getDomain());
        });

        return !FILTERS.matcher(href).matches() && visit;
    }

    /**
     * This function is called when a page is fetched and ready
     * to be processed by your program.
     */
    @Override
    public void visit(Page page) {
        String url = page.getWebURL().getURL();


        if (page.getParseData() instanceof HtmlParseData) {


            HtmlParseData htmlParseData = (HtmlParseData) page.getParseData();

            if (checkPage(this.keywords, page)) {

                String text = htmlParseData.getText();
                String html = htmlParseData.getHtml();

                Document doc = Jsoup.parse(html);
                Element dateEl = doc.select("[datetime]").first();
                Date date = null;
                try {
                    date = formatter.parse(dateEl.attr("datetime").toString());
                    if(date.after(formatter.parse(this.filterDate))){
                        String dateStr= formatter.format(date);
                        repository.save(new CrawlData(htmlParseData.getTitle(),text,html,dateStr,url));
                    }
                } catch (Exception e) {
                    //TODO
                }
            }
        }
    }

    private boolean checkPage(List<String> keywords, Page page) {
        HtmlParseData htmlParseData = (HtmlParseData) page.getParseData();
        return keywords.stream().anyMatch(str -> htmlParseData.getText().contains(str.trim())) || keywords.stream().anyMatch(str -> page.getWebURL().getURL().contains(str.trim()));
    }
}