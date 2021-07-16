package com.markmzy.mywork.wx.service.impl;

import com.markmzy.mywork.wx.model.SysConfig;
import com.markmzy.mywork.wx.dao.SysConfigMapper;
import com.markmzy.mywork.wx.service.ISysConfigService;
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
public class SysConfigServiceImpl extends ServiceImpl<SysConfigMapper, SysConfig> implements ISysConfigService {

    @Override
    public  IPage<SysConfig> findListByPage(Integer page, Integer pageCount){
        IPage<SysConfig> wherePage = new Page<>(page, pageCount);
        SysConfig where = new SysConfig();

        return   baseMapper.selectPage(wherePage, Wrappers.query(where));
    }

    @Override
    public int add(SysConfig sysConfig){
        return baseMapper.insert(sysConfig);
    }

    @Override
    public int delete(Long id){
        return baseMapper.deleteById(id);
    }

    @Override
    public int updateData(SysConfig sysConfig){
        return baseMapper.updateById(sysConfig);
    }

    @Override
    public SysConfig findById(Long id){
        return  baseMapper.selectById(id);
    }
}
