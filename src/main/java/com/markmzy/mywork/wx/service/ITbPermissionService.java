package com.markmzy.mywork.wx.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.markmzy.mywork.wx.model.TbPermission;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author Zhiyue Ma
 * @since 2021-07-16
 */
public interface ITbPermissionService extends IService<TbPermission>
{

    /**
     * 查询分页数据
     *
     * @param page      页码
     * @param pageCount 每页条数
     * @return IPage<TbPermission>
     */
    IPage<TbPermission> findListByPage(Integer page, Integer pageCount);

    /**
     * 添加
     *
     * @param tbPermission
     * @return int
     */
    int add(TbPermission tbPermission);

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
     * @param tbPermission
     * @return int
     */
    int updateData(TbPermission tbPermission);

    /**
     * id查询数据
     *
     * @param id id
     * @return TbPermission
     */
    TbPermission findById(Long id);
}
