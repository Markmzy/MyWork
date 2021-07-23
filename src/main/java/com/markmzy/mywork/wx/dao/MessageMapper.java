package com.markmzy.mywork.wx.dao;

import cn.hutool.core.date.DateField;
import cn.hutool.core.date.DateUtil;
import cn.hutool.json.JSONObject;
import com.markmzy.mywork.wx.model.Message;
import com.markmzy.mywork.wx.model.MessageRef;
import com.mongodb.client.result.DeleteResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * @title: MessageMapper
 * @Author Zhiyue Ma
 * @Date: 7/22/21 18:44
 * @Version 1.0
 */

@Repository
public class MessageMapper
{
    @Autowired
    private MongoTemplate mongoTemplate;


    public String insertMessage(Message message)
    {
        // 把北京时间转成格林尼治时间
        Date sendTime = message.getSendTime();
        sendTime = DateUtil.offset(sendTime, DateField.HOUR, 8);
        message.setSendTime(sendTime);

        message = mongoTemplate.save(message);
        return message.get_id();
    }


    public List<HashMap> searchMessageByPage(int userId, long start, int pageSize)
    {
        JSONObject json = new JSONObject();
        json.set("$toString", "$_id");
        Aggregation aggregation = Aggregation.newAggregation( //集合查询
                Aggregation.addFields().addField("id").withValue(json).build(),
                Aggregation.lookup("message_ref", "id", "messageId", "ref"),
                Aggregation.match(Criteria.where("ref.receiverId").is(userId)), // Where
                Aggregation.sort(Sort.by(Sort.Direction.DESC, "sendTime")), //按照发送时间降序
                Aggregation.skip(start),
                Aggregation.limit(pageSize)
        );

        AggregationResults<HashMap> results = mongoTemplate.aggregate(aggregation, "message", HashMap.class);
        List<HashMap> list = results.getMappedResults();
        list.forEach(one ->
        {
            List<MessageRef> refList = (List<MessageRef>) one.get("ref");
            MessageRef messageRef = refList.get(0);
            boolean readFlag = messageRef.getReadFlag();
            String refId = messageRef.get_id();

            //拿到合并表的数据
            one.put("readFlag", readFlag);
            one.put("refId", refId);

            //删除多余数据
            one.remove("ref");
            one.remove("_id");

            //把格林尼治时间转成北京时间
            Date sendTime = (Date) one.get("sendTime");
            sendTime = DateUtil.offset(sendTime, DateField.HOUR, -8);

            //处理时间故事格式
            String today = DateUtil.today();
            if(today.equals(DateUtil.date(sendTime).toDateStr())) //今天的消息只用看时间
            {
                one.put("sendTime", DateUtil.format(sendTime, "HH:mm"));
            }
            else //之前的消息只看日期
            {
                one.put("sendTime", DateUtil.format(sendTime, "yyyy/MM/dd"));
            }
        });

        return list;
    }

    public HashMap searchMessageById(String id)
    {
        HashMap map = mongoTemplate.findById(id, HashMap.class, "message");
        Date sendTime = (Date) map.get("sendTime");
        sendTime = DateUtil.offset(sendTime, DateField.HOUR, 8);
        map.replace("sendTime", DateUtil.format(sendTime, "yyyy-MM-dd HH:mm"));
        return map;
    }

    public long deleteUserMessage(int receiverId)
    {
        Query query = new Query();
        query.addCriteria(Criteria.where("receiverId").is(receiverId));
        DeleteResult result = mongoTemplate.remove(query, "message");
        long rows = result.getDeletedCount();
        return rows;
    }

}
