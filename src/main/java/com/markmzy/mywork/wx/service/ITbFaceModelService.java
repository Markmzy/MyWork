package com.markmzy.mywork.wx.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.markmzy.mywork.wx.model.TbFaceModel;

/**
 * <p>
 * 人脸模型表 服务类
 * </p>
 *
 * @author Zhiyue Ma
 * @since 2021-07-17
 */
public interface ITbFaceModelService extends IService<TbFaceModel>
{

    /**
     * 查询人脸模型表分页数据
     *
     * @param page      页码
     * @param pageCount 每页条数
     * @return IPage<TbFaceModel>
     */
    IPage<TbFaceModel> findListByPage(Integer page, Integer pageCount);

    /**
     * 添加人脸模型表
     *
     * @param tbFaceModel 人脸模型表
     * @return int
     */
    int add(TbFaceModel tbFaceModel);

    /**
     * 删除人脸模型表
     *
     * @param id 主键
     * @return int
     */
    int delete(Long id);

    /**
     * 修改人脸模型表
     *
     * @param tbFaceModel 人脸模型表
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
