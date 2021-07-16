package com.markmzy.mywork.wx.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.markmzy.mywork.wx.dao.TbModuleMapper;
import com.markmzy.mywork.wx.model.TbModule;
import com.markmzy.mywork.wx.service.ITbModuleService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 模块资源表 服务实现类
 * </p>
 *
 * @author Zhiyue Ma
 * @since 2021-07-16
 */
@Service
public class TbModuleServiceImpl extends ServiceImpl<TbModuleMapper, TbModule> implements ITbModuleService
{

    @Override
    public IPage<TbModule> findListByPage(Integer page, Integer pageCount)
    {
        IPage<TbModule> wherePage = new Page<>(page, pageCount);
        TbModule where = new TbModule();

        return baseMapper.selectPage(wherePage, Wrappers.query(where));
    }

    @Override
    public int add(TbModule tbModule)
    {
        return baseMapper.insert(tbModule);
    }

    @Override
    public int delete(Long id)
    {
        return baseMapper.deleteById(id);
    }

    @Override
    public int updateData(TbModule tbModule)
    {
        return baseMapper.updateById(tbModule);
    }

    @Override
    public TbModule findById(Long id)
    {
        return baseMapper.selectById(id);
    }
}
