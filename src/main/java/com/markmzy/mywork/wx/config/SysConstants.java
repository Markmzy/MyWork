package com.markmzy.mywork.wx.config;

import lombok.Data;
import org.springframework.stereotype.Component;

/**
 * @title: SysConstants
 * @Author Zhiyue Ma
 * @Date: 7/20/21 13:49
 * @Version 1.0
 */

@Data
@Component
public class SysConstants
{
    public String attendanceStartTime;
    public String attendanceTime;
    public String attendanceEndTime;
    public String closingStartTime;
    public String closingTime;
    public String closingEndTime;
}
