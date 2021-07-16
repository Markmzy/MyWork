package com.markmzy.mywork.wx.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.markmzy.mywork.wx.model.TbRole;

/**
 * <p>
 * 角色表 服务类
 * </p>
 *
 * @author Zhiyue Ma
 * @since 2021-07-16
 */
public interface ITbRoleService extends IService<TbRole>
{

    /**
     * 查询角色表分页数据
     *
     * @param page      页码
     * @param pageCount 每页条数
     * @return IPage<TbRole>
     */
    IPage<TbRole> findListByPage(Integer page, Integer pageCount);

    /**
     * 添加角色表
     *
     * @param tbRole 角色表
     * @return int
     */
    int add(TbRole tbRole);

    /**
     * 删除角色表
     *
     * @param id 主键
     * @return int
     */
    int delete(Long id);

    /**
     * 修改角色表
     *
     * @param tbRole 角色表
     * @return int
     */
    int updateData(TbRole tbRole);

    /**
     * id查询数据
     *
     * @param id id
     * @return TbRole
     */
    TbRole findById(Long id);
}
