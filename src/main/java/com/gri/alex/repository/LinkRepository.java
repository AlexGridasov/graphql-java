package com.gri.alex.repository;

import com.gri.alex.model.Link;
import com.gri.alex.model.LinkFilter;
import com.mongodb.client.MongoCollection;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.mongodb.client.model.Filters.and;
import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Filters.regex;

/**
 * User: Alex
 * Date: 11/16/17
 */
public class LinkRepository {
    private final Logger Logger = LoggerFactory.getLogger(LinkRepository.class);

    private final MongoCollection<Document> links;

    public LinkRepository(MongoCollection<Document> links) {
        this.links = links;
    }

    public Link findById(String id) {
        Document doc = links.find(eq("_id", new ObjectId(id))).first();
        Link link = link(doc);
        Logger.info("findById(): {}", link);

        return link;
    }

    public List<Link> getAllLinks(LinkFilter filter) {
        Optional<Bson> mongoFilter = Optional.ofNullable(filter).map(this::buildFilter);

        List<Link> allLinks = new ArrayList<>();
        for (Document doc : mongoFilter.map(links::find).orElseGet(links::find)) {
            allLinks.add(link(doc));
        }
        Logger.info("getAllLinks(): {}", allLinks.size());

        return allLinks;
    }

    public void saveLink(Link link) {
        Document doc = new Document();
        doc.append("url", link.getUrl());
        doc.append("description", link.getDescription());
        doc.append("postedBy", link.getUserId());
        links.insertOne(doc);
        Logger.info("saveLink(): {}", link);
    }

    //builds a Bson from a LinkFilter
    private Bson buildFilter(LinkFilter filter) {
        String descriptionPattern = filter.getDescriptionContains();
        String urlPattern = filter.getUrlContains();
        Bson descriptionCondition = null;
        Bson urlCondition = null;

        if (descriptionPattern != null && !descriptionPattern.isEmpty()) {
            descriptionCondition = regex("description", ".*" + descriptionPattern + ".*", "i");
        }
        if (urlPattern != null && !urlPattern.isEmpty()) {
            urlCondition = regex("url", ".*" + urlPattern + ".*", "i");
        }
        if (descriptionCondition != null && urlCondition != null) {
            return and(descriptionCondition, urlCondition);
        }

        return descriptionCondition != null ? descriptionCondition : urlCondition;
    }

    private Link link(Document doc) {
        return new Link(
                doc.get("_id").toString(),
                doc.getString("url"),
                doc.getString("description"));
    }
}
