package com.markmzy.mywork.wx.service;

import com.markmzy.mywork.wx.model.TbHolidays;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.core.metadata.IPage;

/**
 * <p>
 * 节假日表 服务类
 * </p>
 *
 * @author Zhiyue Ma
 * @since 2021-07-16
 */
public interface ITbHolidaysService extends IService<TbHolidays> {

    /**
     * 查询节假日表分页数据
     *
     * @param page      页码
     * @param pageCount 每页条数
     * @return IPage<TbHolidays>
     */
    IPage<TbHolidays> findListByPage(Integer page, Integer pageCount);

    /**
     * 添加节假日表
     *
     * @param tbHolidays 节假日表
     * @return int
     */
    int add(TbHolidays tbHolidays);

    /**
     * 删除节假日表
     *
     * @param id 主键
     * @return int
     */
    int delete(Long id);

    /**
     * 修改节假日表
     *
     * @param tbHolidays 节假日表
     * @return int
     */
    int updateData(TbHolidays tbHolidays);

    /**
     * id查询数据
     *
     * @param id id
     * @return TbHolidays
     */
    TbHolidays findById(Long id);
}
