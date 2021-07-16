package com.markmzy.mywork.wx.service.impl;

import com.markmzy.mywork.wx.model.TbRole;
import com.markmzy.mywork.wx.dao.TbRoleMapper;
import com.markmzy.mywork.wx.service.ITbRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;

/**
 * <p>
 * 角色表 服务实现类
 * </p>
 *
 * @author Zhiyue Ma
 * @since 2021-07-16
 */
@Service
public class TbRoleServiceImpl extends ServiceImpl<TbRoleMapper, TbRole> implements ITbRoleService {

    @Override
    public  IPage<TbRole> findListByPage(Integer page, Integer pageCount){
        IPage<TbRole> wherePage = new Page<>(page, pageCount);
        TbRole where = new TbRole();

        return   baseMapper.selectPage(wherePage, Wrappers.query(where));
    }

    @Override
    public int add(TbRole tbRole){
        return baseMapper.insert(tbRole);
    }

    @Override
    public int delete(Long id){
        return baseMapper.deleteById(id);
    }

    @Override
    public int updateData(TbRole tbRole){
        return baseMapper.updateById(tbRole);
    }

    @Override
    public TbRole findById(Long id){
        return  baseMapper.selectById(id);
    }
}
