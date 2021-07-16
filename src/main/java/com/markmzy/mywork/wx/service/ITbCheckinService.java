package com.markmzy.mywork.wx.service;

import com.markmzy.mywork.wx.model.TbCheckin;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.core.metadata.IPage;

/**
 * <p>
 * 签到表 服务类
 * </p>
 *
 * @author Zhiyue Ma
 * @since 2021-07-17
 */
public interface ITbCheckinService extends IService<TbCheckin> {

    /**
     * 查询签到表分页数据
     *
     * @param page      页码
     * @param pageCount 每页条数
     * @return IPage<TbCheckin>
     */
    IPage<TbCheckin> findListByPage(Integer page, Integer pageCount);

    /**
     * 添加签到表
     *
     * @param tbCheckin 签到表
     * @return int
     */
    int add(TbCheckin tbCheckin);

    /**
     * 删除签到表
     *
     * @param id 主键
     * @return int
     */
    int delete(Long id);

    /**
     * 修改签到表
     *
     * @param tbCheckin 签到表
     * @return int
     */
    int updateData(TbCheckin tbCheckin);

    /**
     * id查询数据
     *
     * @param id id
     * @return TbCheckin
     */
    TbCheckin findById(Long id);
}
