package com.markmzy.mywork.wx.service;

import com.markmzy.mywork.wx.model.TbFaceModel;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.core.metadata.IPage;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Zhiyue Ma
 * @since 2021-07-16
 */
public interface ITbFaceModelService extends IService<TbFaceModel> {

    /**
     * 查询分页数据
     *
     * @param page      页码
     * @param pageCount 每页条数
     * @return IPage<TbFaceModel>
     */
    IPage<TbFaceModel> findListByPage(Integer page, Integer pageCount);

    /**
     * 添加
     *
     * @param tbFaceModel 
     * @return int
     */
    int add(TbFaceModel tbFaceModel);

    /**
     * 删除
     *
     * @param id 主键
     * @return int
     */
    int delete(Long id);

    /**
     * 修改
     *
     * @param tbFaceModel 
     * @return int
     */
    int updateData(TbFaceModel tbFaceModel);

    /**
     * id查询数据
     *
     * @param id id
     * @return TbFaceModel
     */
    TbFaceModel findById(Long id);
}
