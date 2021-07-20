package com.markmzy.mywork.wx.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.markmzy.mywork.wx.model.TbCheckin;

/**
 * <p>
 * 签到表 服务类
 * </p>
 *
 * @author Zhiyue Ma
 * @since 2021-07-17
 */
public interface ITbCheckinService extends IService<TbCheckin>
{
    /**
     * 是否可以签到
     */
    String validCanCheckIn(int userId, String date);
}
