package com.markmzy.mywork.wx;

import cn.hutool.core.util.StrUtil;
import com.markmzy.mywork.wx.config.SysConstants;
import com.markmzy.mywork.wx.dao.SysConfigMapper;
import com.markmzy.mywork.wx.model.SysConfig;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

import javax.annotation.PostConstruct;
import java.lang.reflect.Field;
import java.util.List;

@SpringBootApplication
@ServletComponentScan
@MapperScan("com.markmzy.mywork.wx.dao")
@Slf4j
public class MyWorkApplication
{
    @Autowired
    private SysConfigMapper sysConfigMapper;

    @Autowired
    private SysConstants sysConstants;

    public static void main(String[] args)
    {
        SpringApplication.run(MyWorkApplication.class, args);
        System.out.println("后台启动成功～");
    }

    @PostConstruct
    public void init()
    {
        List<SysConfig> list = sysConfigMapper.selectAllParam();
        list.forEach(one ->
        {
            String key = one.getParamKey();
            key = StrUtil.toCamelCase(key);
            String value = one.getParamValue();
            try
            {
                Field field = sysConstants.getClass().getDeclaredField(key); //反射
                field.set(sysConstants, value);
            } catch(Exception e)
            {
                log.error("执行异常", e);
            }
        });
    }
}
