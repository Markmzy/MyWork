package com.markmzy.mywork.wx.service.impl;

import com.markmzy.mywork.wx.model.TbWorkday;
import com.markmzy.mywork.wx.dao.TbWorkdayMapper;
import com.markmzy.mywork.wx.service.ITbWorkdayService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Zhiyue Ma
 * @since 2021-07-16
 */
@Service
public class TbWorkdayServiceImpl extends ServiceImpl<TbWorkdayMapper, TbWorkday> implements ITbWorkdayService {

    @Override
    public  IPage<TbWorkday> findListByPage(Integer page, Integer pageCount){
        IPage<TbWorkday> wherePage = new Page<>(page, pageCount);
        TbWorkday where = new TbWorkday();

        return   baseMapper.selectPage(wherePage, Wrappers.query(where));
    }

    @Override
    public int add(TbWorkday tbWorkday){
        return baseMapper.insert(tbWorkday);
    }

    @Override
    public int delete(Long id){
        return baseMapper.deleteById(id);
    }

    @Override
    public int updateData(TbWorkday tbWorkday){
        return baseMapper.updateById(tbWorkday);
    }

    @Override
    public TbWorkday findById(Long id){
        return  baseMapper.selectById(id);
    }
}
