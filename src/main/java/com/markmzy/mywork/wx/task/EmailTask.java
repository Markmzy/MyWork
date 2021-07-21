package com.markmzy.mywork.wx.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * @title: EmailTask
 * @Author Zhiyue Ma
 * @Date: 7/21/21 13:00
 * @Version 1.0
 */
@Component
@Scope("prototype")
public class EmailTask implements Serializable
{
    @Autowired
    private JavaMailSender javaMailSender;

    @Value("${mywork.email.system}")
    private String system;

    @Async
    public void sendAsync(SimpleMailMessage message)
    {
        message.setFrom(system);
        javaMailSender.send(message);
    }
}
