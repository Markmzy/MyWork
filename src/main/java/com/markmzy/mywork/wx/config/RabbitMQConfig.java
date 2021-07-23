package com.markmzy.mywork.wx.config;

import com.rabbitmq.client.ConnectionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @title: RabbitMQConfig
 * @Author Zhiyue Ma
 * @Date: 7/23/21 09:39
 * @Version 1.0
 */
@Configuration
public class RabbitMQConfig
{
    @Bean
    public ConnectionFactory getFactory()
    {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("192.168.0.100"); //主机ip地址
        factory.setPort(5672);
        return factory;
    }

}
