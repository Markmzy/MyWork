package com.markmzy.mywork.wx.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.markmzy.mywork.wx.dao.TbActionMapper;
import com.markmzy.mywork.wx.model.TbAction;
import com.markmzy.mywork.wx.service.ITbActionService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 行为表 服务实现类
 * </p>
 *
 * @author Zhiyue Ma
 * @since 2021-07-16
 */
@Service
public class TbActionServiceImpl extends ServiceImpl<TbActionMapper, TbAction> implements ITbActionService
{

    @Override
    public IPage<TbAction> findListByPage(Integer page, Integer pageCount)
    {
        IPage<TbAction> wherePage = new Page<>(page, pageCount);
        TbAction where = new TbAction();

        return baseMapper.selectPage(wherePage, Wrappers.query(where));
    }

    @Override
    public int add(TbAction tbAction)
    {
        return baseMapper.insert(tbAction);
    }

    @Override
    public int delete(Long id)
    {
        return baseMapper.deleteById(id);
    }

    @Override
    public int updateData(TbAction tbAction)
    {
        return baseMapper.updateById(tbAction);
    }

    @Override
    public TbAction findById(Long id)
    {
        return baseMapper.selectById(id);
    }
}
