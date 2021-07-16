package com.markmzy.mywork.wx.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.markmzy.mywork.wx.model.TbDept;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author Zhiyue Ma
 * @since 2021-07-16
 */
public interface ITbDeptService extends IService<TbDept>
{

    /**
     * 查询分页数据
     *
     * @param page      页码
     * @param pageCount 每页条数
     * @return IPage<TbDept>
     */
    IPage<TbDept> findListByPage(Integer page, Integer pageCount);

    /**
     * 添加
     *
     * @param tbDept
     * @return int
     */
    int add(TbDept tbDept);

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
     * @param tbDept
     * @return int
     */
    int updateData(TbDept tbDept);

    /**
     * id查询数据
     *
     * @param id id
     * @return TbDept
     */
    TbDept findById(Long id);
}
