package com.markmzy.mywork.wx.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.markmzy.mywork.wx.model.TbMeeting;

/**
 * <p>
 * 会议表 服务类
 * </p>
 *
 * @author Zhiyue Ma
 * @since 2021-07-16
 */
public interface ITbMeetingService extends IService<TbMeeting>
{

    /**
     * 查询会议表分页数据
     *
     * @param page      页码
     * @param pageCount 每页条数
     * @return IPage<TbMeeting>
     */
    IPage<TbMeeting> findListByPage(Integer page, Integer pageCount);

    /**
     * 添加会议表
     *
     * @param tbMeeting 会议表
     * @return int
     */
    int add(TbMeeting tbMeeting);

    /**
     * 删除会议表
     *
     * @param id 主键
     * @return int
     */
    int delete(Long id);

    /**
     * 修改会议表
     *
     * @param tbMeeting 会议表
     * @return int
     */
    int updateData(TbMeeting tbMeeting);

    /**
     * id查询数据
     *
     * @param id id
     * @return TbMeeting
     */
    TbMeeting findById(Long id);
}
