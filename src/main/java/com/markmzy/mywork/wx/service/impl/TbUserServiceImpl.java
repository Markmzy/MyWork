package com.markmzy.mywork.wx.service.impl;

import com.markmzy.mywork.wx.model.TbUser;
import com.markmzy.mywork.wx.dao.TbUserMapper;
import com.markmzy.mywork.wx.service.ITbUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author Zhiyue Ma
 * @since 2021-07-16
 */
@Service
public class TbUserServiceImpl extends ServiceImpl<TbUserMapper, TbUser> implements ITbUserService {

    @Override
    public  IPage<TbUser> findListByPage(Integer page, Integer pageCount){
        IPage<TbUser> wherePage = new Page<>(page, pageCount);
        TbUser where = new TbUser();

        return   baseMapper.selectPage(wherePage, Wrappers.query(where));
    }

    @Override
    public int add(TbUser tbUser){
        return baseMapper.insert(tbUser);
    }

    @Override
    public int delete(Long id){
        return baseMapper.deleteById(id);
    }

    @Override
    public int updateData(TbUser tbUser){
        return baseMapper.updateById(tbUser);
    }

    @Override
    public TbUser findById(Long id){
        return  baseMapper.selectById(id);
    }
}
