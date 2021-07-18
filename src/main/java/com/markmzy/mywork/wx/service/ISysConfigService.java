package com.markmzy.mywork.wx.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.markmzy.mywork.wx.model.SysConfig;

/**
 * <p>
 * 系统设置表 服务类
 * </p>
 *
 * @author Zhiyue Ma
 * @since 2021-07-17
 */
public interface ISysConfigService extends IService<SysConfig>
{

    /**
     * 查询系统设置表分页数据
     *
     * @param page      页码
     * @param pageCount 每页条数
     * @return IPage<SysConfig>
     */
    IPage<SysConfig> findListByPage(Integer page, Integer pageCount);

    /**
     * 添加系统设置表
     *
     * @param sysConfig 系统设置表
     * @return int
     */
    int add(SysConfig sysConfig);

    /**
     * 删除系统设置表
     *
     * @param id 主键
     * @return int
     */
    int delete(Long id);

    /**
     * 修改系统设置表
     *
     * @param sysConfig 系统设置表
     * @return int
     */
    int updateData(SysConfig sysConfig);

    /**
     * id查询数据
     *
     * @param id id
     * @return SysConfig
     */
    SysConfig findById(Long id);
}
