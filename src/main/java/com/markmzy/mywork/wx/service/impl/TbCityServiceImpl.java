package com.markmzy.mywork.wx.service.impl;

import com.markmzy.mywork.wx.model.TbCity;
import com.markmzy.mywork.wx.dao.TbCityMapper;
import com.markmzy.mywork.wx.service.ITbCityService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;

/**
 * <p>
 * 疫情城市列表 服务实现类
 * </p>
 *
 * @author Zhiyue Ma
 * @since 2021-07-16
 */
@Service
public class TbCityServiceImpl extends ServiceImpl<TbCityMapper, TbCity> implements ITbCityService {

    @Override
    public  IPage<TbCity> findListByPage(Integer page, Integer pageCount){
        IPage<TbCity> wherePage = new Page<>(page, pageCount);
        TbCity where = new TbCity();

        return   baseMapper.selectPage(wherePage, Wrappers.query(where));
    }

    @Override
    public int add(TbCity tbCity){
        return baseMapper.insert(tbCity);
    }

    @Override
    public int delete(Long id){
        return baseMapper.deleteById(id);
    }

    @Override
    public int updateData(TbCity tbCity){
        return baseMapper.updateById(tbCity);
    }

    @Override
    public TbCity findById(Long id){
        return  baseMapper.selectById(id);
    }
}
