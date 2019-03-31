package com.alimin.crawler;

import edu.uci.ics.crawler4j.crawler.Page;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @author Ali Minnet
 * @version 2.0
 */
public interface PageRepository extends MongoRepository<Page, String> {
}