package com.markmzy.mywork.wx.dao;

import com.markmzy.mywork.wx.model.MessageRef;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

/**
 * @title: MessageRefMapper
 * @Author Zhiyue Ma
 * @Date: 7/22/21 19:07
 * @Version 1.0
 */

@Repository
public class MessageRefMapper
{
    @Autowired
    private MongoTemplate mongoTemplate;

    public String insertRef(MessageRef messageRef)
    {
        messageRef = mongoTemplate.save(messageRef);
        return messageRef.get_id();
    }

    public long searchUnreadCount(int userId)
    {
        Query query = new Query();
        query.addCriteria(Criteria.where("readFlag").is(false).and("receiverId").is(userId));
        long count = mongoTemplate.count(query, MessageRef.class);
        return count;
    }

    public long searchLastCount(int userId)
    {
        Query query = new Query();
        query.addCriteria(Criteria.where("lastFlag").is(true).and("receiverId").is(userId));
        Update update = new Update();
        update.set("lastFlag", false);
        UpdateResult result = mongoTemplate.updateMulti(query, update, "message_ref");
        long rows = result.getModifiedCount();
        return rows;
    }

    public long updateUnreadMessage(String id)
    {
        Query query = new Query();
        query.addCriteria(Criteria.where("_id").is(id));
        Update update = new Update();
        update.set("readFlag", true);
        UpdateResult result = mongoTemplate.updateFirst(query, update, "message_ref");
        long rows = result.getModifiedCount();
        return rows;
    }

    public long deleteMessageRefById(String id)
    {
        Query query = new Query();
        query.addCriteria(Criteria.where("_id").is(id));
        DeleteResult result = mongoTemplate.remove(query, "message_ref");
        long rows = result.getDeletedCount();
        return rows;
    }

    public long deleteUserMessageRef(int userId)
    {
        Query query = new Query();
        query.addCriteria(Criteria.where("receiverId").is(userId));
        DeleteResult result = mongoTemplate.remove(query, "message_ref");
        long rows = result.getDeletedCount();
        return rows;
    }
}
