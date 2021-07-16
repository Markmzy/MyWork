package com.markmzy.mywork.wx.service;

import com.markmzy.mywork.wx.model.TbAction;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.core.metadata.IPage;

/**
 * <p>
 * 行为表 服务类
 * </p>
 *
 * @author Zhiyue Ma
 * @since 2021-07-17
 */
public interface ITbActionService extends IService<TbAction> {

    /**
     * 查询行为表分页数据
     *
     * @param page      页码
     * @param pageCount 每页条数
     * @return IPage<TbAction>
     */
    IPage<TbAction> findListByPage(Integer page, Integer pageCount);

    /**
     * 添加行为表
     *
     * @param tbAction 行为表
     * @return int
     */
    int add(TbAction tbAction);

    /**
     * 删除行为表
     *
     * @param id 主键
     * @return int
     */
    int delete(Long id);

    /**
     * 修改行为表
     *
     * @param tbAction 行为表
     * @return int
     */
    int updateData(TbAction tbAction);

    /**
     * id查询数据
     *
     * @param id id
     * @return TbAction
     */
    TbAction findById(Long id);
}
