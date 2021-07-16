package com.markmzy.mywork.wx.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.markmzy.mywork.wx.dao.TbCheckinMapper;
import com.markmzy.mywork.wx.model.TbCheckin;
import com.markmzy.mywork.wx.service.ITbCheckinService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 签到表 服务实现类
 * </p>
 *
 * @author Zhiyue Ma
 * @since 2021-07-16
 */
@Service
public class TbCheckinServiceImpl extends ServiceImpl<TbCheckinMapper, TbCheckin> implements ITbCheckinService
{

    @Override
    public IPage<TbCheckin> findListByPage(Integer page, Integer pageCount)
    {
        IPage<TbCheckin> wherePage = new Page<>(page, pageCount);
        TbCheckin where = new TbCheckin();

        return baseMapper.selectPage(wherePage, Wrappers.query(where));
    }

    @Override
    public int add(TbCheckin tbCheckin)
    {
        return baseMapper.insert(tbCheckin);
    }

    @Override
    public int delete(Long id)
    {
        return baseMapper.deleteById(id);
    }

    @Override
    public int updateData(TbCheckin tbCheckin)
    {
        return baseMapper.updateById(tbCheckin);
    }

    @Override
    public TbCheckin findById(Long id)
    {
        return baseMapper.selectById(id);
    }
}
