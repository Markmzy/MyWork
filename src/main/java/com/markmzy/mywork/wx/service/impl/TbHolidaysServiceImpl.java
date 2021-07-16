package com.markmzy.mywork.wx.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.markmzy.mywork.wx.dao.TbHolidaysMapper;
import com.markmzy.mywork.wx.model.TbHolidays;
import com.markmzy.mywork.wx.service.ITbHolidaysService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 节假日表 服务实现类
 * </p>
 *
 * @author Zhiyue Ma
 * @since 2021-07-16
 */
@Service
public class TbHolidaysServiceImpl extends ServiceImpl<TbHolidaysMapper, TbHolidays> implements ITbHolidaysService
{

    @Override
    public IPage<TbHolidays> findListByPage(Integer page, Integer pageCount)
    {
        IPage<TbHolidays> wherePage = new Page<>(page, pageCount);
        TbHolidays where = new TbHolidays();

        return baseMapper.selectPage(wherePage, Wrappers.query(where));
    }

    @Override
    public int add(TbHolidays tbHolidays)
    {
        return baseMapper.insert(tbHolidays);
    }

    @Override
    public int delete(Long id)
    {
        return baseMapper.deleteById(id);
    }

    @Override
    public int updateData(TbHolidays tbHolidays)
    {
        return baseMapper.updateById(tbHolidays);
    }

    @Override
    public TbHolidays findById(Long id)
    {
        return baseMapper.selectById(id);
    }
}
