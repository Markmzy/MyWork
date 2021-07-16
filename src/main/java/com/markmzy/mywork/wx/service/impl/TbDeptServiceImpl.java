package com.markmzy.mywork.wx.service.impl;

import com.markmzy.mywork.wx.model.TbDept;
import com.markmzy.mywork.wx.dao.TbDeptMapper;
import com.markmzy.mywork.wx.service.ITbDeptService;
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
public class TbDeptServiceImpl extends ServiceImpl<TbDeptMapper, TbDept> implements ITbDeptService {

    @Override
    public  IPage<TbDept> findListByPage(Integer page, Integer pageCount){
        IPage<TbDept> wherePage = new Page<>(page, pageCount);
        TbDept where = new TbDept();

        return   baseMapper.selectPage(wherePage, Wrappers.query(where));
    }

    @Override
    public int add(TbDept tbDept){
        return baseMapper.insert(tbDept);
    }

    @Override
    public int delete(Long id){
        return baseMapper.deleteById(id);
    }

    @Override
    public int updateData(TbDept tbDept){
        return baseMapper.updateById(tbDept);
    }

    @Override
    public TbDept findById(Long id){
        return  baseMapper.selectById(id);
    }
}
