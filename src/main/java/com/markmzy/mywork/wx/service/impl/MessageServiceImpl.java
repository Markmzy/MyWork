package com.markmzy.mywork.wx.service.impl;

import com.markmzy.mywork.wx.dao.MessageMapper;
import com.markmzy.mywork.wx.dao.MessageRefMapper;
import com.markmzy.mywork.wx.model.Message;
import com.markmzy.mywork.wx.model.MessageRef;
import com.markmzy.mywork.wx.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

/**
 * @title: MessageServiceImpl
 * @Author Zhiyue Ma
 * @Date: 7/22/21 19:35
 * @Version 1.0
 */

@Service
public class MessageServiceImpl implements MessageService
{

    @Autowired
    private MessageMapper messageMapper;

    @Autowired
    private MessageRefMapper messageRefMapper;

    public String insertMessage(Message message)
    {
        String id = messageMapper.insertMessage(message);
        return id;
    }

    @Override
    public List<HashMap> searchMessageByPage(int userId, long start, int pageSize)
    {
        List<HashMap> list = messageMapper.searchMessageByPage(userId, start, pageSize);
        return list;
    }

    @Override
    public HashMap searchMessageById(String id)
    {
        HashMap map = messageMapper.searchMessageById(id);
        return map;
    }

    @Override
    public long deleteUserMessage(int receiverId)
    {
        long rows = messageMapper.deleteUserMessage(receiverId);
        return rows;
    }

    @Override
    public String insertRef(MessageRef messageRef)
    {
        String id = messageRefMapper.insertRef(messageRef);
        return id;
    }

    @Override
    public long searchUnreadCount(int userId)
    {
        long count = messageRefMapper.searchUnreadCount(userId);
        return count;
    }

    @Override
    public long searchLastCount(int userId)
    {
        long count = messageRefMapper.searchLastCount(userId);
        return count;
    }

    @Override
    public long updateUnreadMessage(String id)
    {
        long rows = messageRefMapper.updateUnreadMessage(id);
        return rows;
    }

    @Override
    public long deleteMessageRefById(String id)
    {
        long rows = messageRefMapper.deleteMessageRefById(id);
        return rows;
    }

    @Override
    public long deleteUserMessageRef(int userId)
    {
        long rows = messageRefMapper.deleteUserMessageRef(userId);
        return rows;
    }
}
