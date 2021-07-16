package com.markmzy.mywork.wx.service;

import com.markmzy.mywork.wx.model.TbWorkday;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.core.metadata.IPage;

/**
 * <p>
 * 工作日表 服务类
 * </p>
 *
 * @author Zhiyue Ma
 * @since 2021-07-17
 */
public interface ITbWorkdayService extends IService<TbWorkday> {

    /**
     * 查询工作日表分页数据
     *
     * @param page      页码
     * @param pageCount 每页条数
     * @return IPage<TbWorkday>
     */
    IPage<TbWorkday> findListByPage(Integer page, Integer pageCount);

    /**
     * 添加工作日表
     *
     * @param tbWorkday 工作日表
     * @return int
     */
    int add(TbWorkday tbWorkday);

    /**
     * 删除工作日表
     *
     * @param id 主键
     * @return int
     */
    int delete(Long id);

    /**
     * 修改工作日表
     *
     * @param tbWorkday 工作日表
     * @return int
     */
    int updateData(TbWorkday tbWorkday);

    /**
     * id查询数据
     *
     * @param id id
     * @return TbWorkday
     */
    TbWorkday findById(Long id);
}
