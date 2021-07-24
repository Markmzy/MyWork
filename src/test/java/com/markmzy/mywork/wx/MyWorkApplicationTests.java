package com.markmzy.mywork.wx;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.IdUtil;
import com.markmzy.mywork.wx.model.Message;
import com.markmzy.mywork.wx.model.MessageRef;
import com.markmzy.mywork.wx.model.TbMeeting;
import com.markmzy.mywork.wx.service.ITbMeetingService;
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

    @Autowired
    private ITbMeetingService tbMeetingService;

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

    @Test
    void createMeetingData()
    {
        for(int i = 1; i <= 100; i++)
        {
            TbMeeting meeting = new TbMeeting();
            meeting.setId(i);
            meeting.setUuid(IdUtil.simpleUUID());
            meeting.setTitle("测试会议" + i);
            meeting.setCreatorId(2); //ROOT用户ID
            meeting.setDate(DateUtil.today());
            meeting.setPlace("线上会议室");
            meeting.setStart("08:30");
            meeting.setEnd("10:30");
            meeting.setType(1);
            meeting.setMembers("[2,3]");
            meeting.setDesc("会议研讨Emos项目上线测试");
            meeting.setInstanceId(IdUtil.simpleUUID());
            meeting.setStatus(3);
            tbMeetingService.insertMeeting(meeting);
        }
    }


}
