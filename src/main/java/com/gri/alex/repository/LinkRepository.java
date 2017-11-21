package com.gri.alex.repository;

import com.gri.alex.model.Link;
import com.mongodb.client.MongoCollection;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

import static com.mongodb.client.model.Filters.eq;

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

    public List<Link> getAllLinks() {
        List<Link> allLinks = new ArrayList<>();
        for (Document doc : links.find()) {
            allLinks.add(link(doc));
        }
        Logger.info("getAllLinks(): {}", allLinks.size());

        return allLinks;
    }

    public void saveLink(Link link) {
        Document doc = new Document();
        doc.append("url", link.getUrl());
        doc.append("description", link.getDescription());
        links.insertOne(doc);
        Logger.info("saveLink(): {}", link);
    }

    private Link link(Document doc) {
        return new Link(
                doc.get("_id").toString(),
                doc.getString("url"),
                doc.getString("description"));
    }
}
