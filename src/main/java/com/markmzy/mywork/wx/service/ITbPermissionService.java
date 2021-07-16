package com.markmzy.mywork.wx.service;

import com.markmzy.mywork.wx.model.TbPermission;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.core.metadata.IPage;

/**
 * <p>
 * 权限表 服务类
 * </p>
 *
 * @author Zhiyue Ma
 * @since 2021-07-17
 */
public interface ITbPermissionService extends IService<TbPermission> {

    /**
     * 查询权限表分页数据
     *
     * @param page      页码
     * @param pageCount 每页条数
     * @return IPage<TbPermission>
     */
    IPage<TbPermission> findListByPage(Integer page, Integer pageCount);

    /**
     * 添加权限表
     *
     * @param tbPermission 权限表
     * @return int
     */
    int add(TbPermission tbPermission);

    /**
     * 删除权限表
     *
     * @param id 主键
     * @return int
     */
    int delete(Long id);

    /**
     * 修改权限表
     *
     * @param tbPermission 权限表
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
