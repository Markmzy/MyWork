package com.markmzy.mywork.wx;

import com.markmzy.mywork.wx.model.Message;
import com.markmzy.mywork.wx.model.MessageRef;
import com.markmzy.mywork.wx.service.MessageService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

@SpringBootTest
class MyWorkApplicationTests
{
    @Autowired
    private MessageService messageService;

    @Test
    void contextLoads()
    {
        for(int i = 1; i <= 100; i++)
        {
            Message message = new Message();
            message.setSenderId(0);
            message.setSenderName("系统消息");
            message.setMsg("这是第" + i + "条测试消息");
            message.setSendTime(new Date());
            String id = messageService.insertMessage(message);

            MessageRef ref = new MessageRef();
            ref.setMessageId(id);
            ref.setReceiverId(2); //接收人ID
            ref.setLastFlag(true);
            ref.setReadFlag(false);
            messageService.insertRef(ref);
        }
    }

}
