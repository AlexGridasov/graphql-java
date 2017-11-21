package com.gri.alex.repository;

import com.gri.alex.model.User;
import com.mongodb.client.MongoCollection;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.mongodb.client.model.Filters.eq;

/**
 * User: Alex
 * Date: 11/21/17
 */
public class UserRepository {
    private final Logger Logger = LoggerFactory.getLogger(LinkRepository.class);

    private final MongoCollection<Document> users;

    public UserRepository(MongoCollection<Document> users) {
        this.users = users;
    }

    public User findByEmail(String email) {
        Document doc = users.find(eq("email", email)).first();
        User user = user(doc);
        Logger.info("findByEmail(): {}", user);

        return user;
    }

    public User findById(String id) {
        Document doc = users.find(eq("_id", new ObjectId(id))).first();
        User user = user(doc);
        Logger.info("findById(): {}", user);

        return user;
    }

    public User saveUser(User user) {
        Document doc = new Document();
        doc.append("name", user.getName());
        doc.append("email", user.getEmail());
        doc.append("password", user.getPassword());
        users.insertOne(doc);
        Logger.info("saveUser(): {}", user);

        return new User(
                doc.get("_id").toString(),
                user.getName(),
                user.getEmail(),
                user.getPassword());
    }

    private User user(Document doc) {
        return new User(
                doc.get("_id").toString(),
                doc.getString("name"),
                doc.getString("email"),
                doc.getString("password"));
    }
}