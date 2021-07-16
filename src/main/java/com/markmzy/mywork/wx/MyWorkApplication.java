package com.markmzy.mywork.wx;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@MapperScan("com.markmzy.mywork.wx.dao")
@Slf4j
@EnableAsync
public class MyWorkApplication
{

    public static void main(String[] args)
    {
        SpringApplication.run(MyWorkApplication.class, args);
        System.out.println("后台启动成功～");
    }

}
