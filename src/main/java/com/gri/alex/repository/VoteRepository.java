package com.gri.alex.repository;

import com.gri.alex.graphql.Scalars;
import com.gri.alex.model.Vote;
import com.mongodb.client.MongoCollection;
import org.bson.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

import static com.mongodb.client.model.Filters.eq;

/**
 * User: Alex
 * Date: 11/23/17
 */
public class VoteRepository {
    private final Logger Logger = LoggerFactory.getLogger(LinkRepository.class);

    private final MongoCollection<Document> votes;

    public VoteRepository(MongoCollection<Document> votes) {
        this.votes = votes;
    }

    public List<Vote> findByUserId(String userId) {
        List<Vote> list = new ArrayList<>();
        for (Document doc : votes.find(eq("userId", userId))) {
            list.add(vote(doc));
        }
        Logger.info("findByUserId(): {}, {} votes", userId, list.size());

        return list;
    }

    public List<Vote> findByLinkId(String linkId) {
        List<Vote> list = new ArrayList<>();
        for (Document doc : votes.find(eq("linkId", linkId))) {
            list.add(vote(doc));
        }
        Logger.info("findByLinkId(): {}, {} votes", linkId, list.size());

        return list;
    }

    public Vote saveVote(Vote voteVO) {
        Document doc = new Document();
        doc.append("userId", voteVO.getUserId());
        doc.append("linkId", voteVO.getLinkId());
        doc.append("createdAt", Scalars.dateTime.getCoercing().serialize(voteVO.getCreatedAt()));
        votes.insertOne(doc);

        Vote vote = new Vote(
                doc.get("_id").toString(),
                voteVO.getCreatedAt(),
                voteVO.getUserId(),
                voteVO.getLinkId());

        Logger.info("saveVote() : {}", vote);

        return vote;
    }

    private Vote vote(Document doc) {
        return new Vote(
                doc.get("_id").toString(),
                ZonedDateTime.parse(doc.getString("createdAt")),
                doc.getString("userId"),
                doc.getString("linkId")
        );
    }
}
