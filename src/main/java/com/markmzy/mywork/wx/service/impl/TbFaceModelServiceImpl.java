package com.markmzy.mywork.wx.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.markmzy.mywork.wx.dao.TbFaceModelMapper;
import com.markmzy.mywork.wx.model.TbFaceModel;
import com.markmzy.mywork.wx.service.ITbFaceModelService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 人脸模型表 服务实现类
 * </p>
 *
 * @author Zhiyue Ma
 * @since 2021-07-17
 */
@Service
public class TbFaceModelServiceImpl extends ServiceImpl<TbFaceModelMapper, TbFaceModel> implements ITbFaceModelService
{

    @Override
    public IPage<TbFaceModel> findListByPage(Integer page, Integer pageCount)
    {
        IPage<TbFaceModel> wherePage = new Page<>(page, pageCount);
        TbFaceModel where = new TbFaceModel();

        return baseMapper.selectPage(wherePage, Wrappers.query(where));
    }

    @Override
    public int add(TbFaceModel tbFaceModel)
    {
        return baseMapper.insert(tbFaceModel);
    }

    @Override
    public int delete(Long id)
    {
        return baseMapper.deleteById(id);
    }

    @Override
    public int updateData(TbFaceModel tbFaceModel)
    {
        return baseMapper.updateById(tbFaceModel);
    }

    @Override
    public TbFaceModel findById(Long id)
    {
        return baseMapper.selectById(id);
    }
}
