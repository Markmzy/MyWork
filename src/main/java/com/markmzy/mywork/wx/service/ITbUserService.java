package com.markmzy.mywork.wx.service;

import com.markmzy.mywork.wx.model.TbUser;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.core.metadata.IPage;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author Zhiyue Ma
 * @since 2021-07-16
 */
public interface ITbUserService extends IService<TbUser> {

    /**
     * 查询用户表分页数据
     *
     * @param page      页码
     * @param pageCount 每页条数
     * @return IPage<TbUser>
     */
    IPage<TbUser> findListByPage(Integer page, Integer pageCount);

    /**
     * 添加用户表
     *
     * @param tbUser 用户表
     * @return int
     */
    int add(TbUser tbUser);

    /**
     * 删除用户表
     *
     * @param id 主键
     * @return int
     */
    int delete(Long id);

    /**
     * 修改用户表
     *
     * @param tbUser 用户表
     * @return int
     */
    int updateData(TbUser tbUser);

    /**
     * id查询数据
     *
     * @param id id
     * @return TbUser
     */
    TbUser findById(Long id);
}
