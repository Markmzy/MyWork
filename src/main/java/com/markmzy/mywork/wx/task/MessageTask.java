package com.markmzy.mywork.wx.task;

import com.markmzy.mywork.wx.exception.MyException;
import com.markmzy.mywork.wx.model.Message;
import com.markmzy.mywork.wx.model.MessageRef;
import com.markmzy.mywork.wx.service.MessageService;
import com.rabbitmq.client.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * @title: MessageTask
 * @Author Zhiyue Ma
 * @Date: 7/23/21 09:36
 * @Version 1.0
 */
@Slf4j
@Component
public class MessageTask
{
    @Autowired
    private ConnectionFactory factory;

    @Autowired
    private MessageService messageService;


    public void send(String topic, Message message)
    {
        String id = messageService.insertMessage(message);
        try(Connection connection = factory.newConnection();
            Channel channel = connection.createChannel();)
        {
            channel.queueDeclare(topic, true, false, false, null);

            HashMap map = new HashMap();
            map.put("messageId", id);
            AMQP.BasicProperties properties = MessageProperties.PERSISTENT_TEXT_PLAIN.builder().headers(map).build();
            channel.basicPublish("", topic, properties, message.getMsg().getBytes());

            log.debug("消息发送成功");
        } catch(Exception e)
        {
            log.error("执行异常", e);
            throw new MyException("向MQ发送消息失败");
        }
    }

    @Async
    public void sendAsync(String topic, Message message)
    {
        send(topic, message);
    }

    public int receive(String topic)
    {
        int i = 0;
        try(Connection connection = factory.newConnection();
            Channel channel = connection.createChannel();)
        {
            channel.queueDeclare(topic, true, false, false, null);

            while(true)
            {
                GetResponse response = channel.basicGet(topic, false);
                if(response != null)
                {
                    AMQP.BasicProperties properties = response.getProps();
                    Map<String, Object> map = properties.getHeaders();
                    String messageId = map.get("messageId").toString();
                    byte[] body = response.getBody();
                    String message = new String(body);
                    log.debug("从MQ接收的消息: " + message);

                    MessageRef messageRef = new MessageRef();
                    messageRef.setMessageId(messageId);
                    messageRef.setReceiverId(Integer.parseInt(topic));
                    messageRef.setReadFlag(false);
                    messageRef.setLastFlag(true);
                    messageService.insertRef(messageRef);

                    long deliveryTag = response.getEnvelope().getDeliveryTag();
                    channel.basicAck(deliveryTag, false);
                    i++;
                }
                else
                {
                    break;
                }
            }
        } catch(Exception e)
        {
            log.error("执行异常", e);
            throw new MyException("从MQ接收消息失败");
        }

        return i;
    }

    @Async
    public int receiveAsync(String topic)
    {
        return receive(topic);
    }

    public void deleteQueue(String topic)
    {
        try(Connection connection = factory.newConnection();
            Channel channel = connection.createChannel();
        )
        {
            channel.queueDelete(topic);
            log.debug("消息队列成功删除");
        } catch(Exception e)
        {
            log.error("执行异常", e);
            throw new MyException("删除队列失败");
        }
    }

    @Async
    public void deleteQueueAsync(String topic)
    {
        deleteQueue(topic);
    }
}
