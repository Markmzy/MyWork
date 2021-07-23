package com.markmzy.mywork.wx.service;

import com.markmzy.mywork.wx.model.Message;
import com.markmzy.mywork.wx.model.MessageRef;

import java.util.HashMap;
import java.util.List;

/**
 * <p>
 * 消息表 服务类
 * </p>
 *
 * @author Zhiyue Ma
 * @since 2021-07-22
 */
public interface MessageService
{
    /**
     * 插入消息主体
     */
    String insertMessage(Message message);

    /**
     * 分页查询用户消息
     */
    List<HashMap> searchMessageByPage(int userId, long start, int pageSize);

    /**
     * 查询一条消息
     */
    HashMap searchMessageById(String id);

    /**
     * 删除用户所有消息主体
     */
    long deleteUserMessage(int receiverId);

    /**
     * 插入消息关联
     */
    String insertRef(MessageRef messageRef);

    /**
     * 查询未读消息数量
     */
    long searchUnreadCount(int userId);

    /**
     * 查询新消息数量
     */
    long searchLastCount(int userId);

    /**
     * 未读消息改为已读
     */
    long updateUnreadMessage(String id);

    /**
     * 删除一条消息关联
     */
    long deleteMessageRefById(String id);

    /**
     * 删除用户所有消息关联
     */
    long deleteUserMessageRef(int userId);
}
