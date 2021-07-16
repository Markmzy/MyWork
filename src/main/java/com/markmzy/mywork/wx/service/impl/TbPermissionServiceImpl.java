package com.markmzy.mywork.wx.service.impl;

import com.markmzy.mywork.wx.model.TbPermission;
import com.markmzy.mywork.wx.dao.TbPermissionMapper;
import com.markmzy.mywork.wx.service.ITbPermissionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;

/**
 * <p>
 * 权限表 服务实现类
 * </p>
 *
 * @author Zhiyue Ma
 * @since 2021-07-17
 */
@Service
public class TbPermissionServiceImpl extends ServiceImpl<TbPermissionMapper, TbPermission> implements ITbPermissionService {

    @Override
    public  IPage<TbPermission> findListByPage(Integer page, Integer pageCount){
        IPage<TbPermission> wherePage = new Page<>(page, pageCount);
        TbPermission where = new TbPermission();

        return   baseMapper.selectPage(wherePage, Wrappers.query(where));
    }

    @Override
    public int add(TbPermission tbPermission){
        return baseMapper.insert(tbPermission);
    }

    @Override
    public int delete(Long id){
        return baseMapper.deleteById(id);
    }

    @Override
    public int updateData(TbPermission tbPermission){
        return baseMapper.updateById(tbPermission);
    }

    @Override
    public TbPermission findById(Long id){
        return  baseMapper.selectById(id);
    }
}
