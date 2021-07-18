package com.markmzy.mywork.wx.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.markmzy.mywork.wx.dao.TbMeetingMapper;
import com.markmzy.mywork.wx.model.TbMeeting;
import com.markmzy.mywork.wx.service.ITbMeetingService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 会议表 服务实现类
 * </p>
 *
 * @author Zhiyue Ma
 * @since 2021-07-17
 */
@Service
public class TbMeetingServiceImpl extends ServiceImpl<TbMeetingMapper, TbMeeting> implements ITbMeetingService
{

    @Override
    public IPage<TbMeeting> findListByPage(Integer page, Integer pageCount)
    {
        IPage<TbMeeting> wherePage = new Page<>(page, pageCount);
        TbMeeting where = new TbMeeting();

        return baseMapper.selectPage(wherePage, Wrappers.query(where));
    }

    @Override
    public int add(TbMeeting tbMeeting)
    {
        return baseMapper.insert(tbMeeting);
    }

    @Override
    public int delete(Long id)
    {
        return baseMapper.deleteById(id);
    }

    @Override
    public int updateData(TbMeeting tbMeeting)
    {
        return baseMapper.updateById(tbMeeting);
    }

    @Override
    public TbMeeting findById(Long id)
    {
        return baseMapper.selectById(id);
    }
}
