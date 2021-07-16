package com.markmzy.mywork.wx.service;

import com.markmzy.mywork.wx.model.SysConfig;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.core.metadata.IPage;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Zhiyue Ma
 * @since 2021-07-16
 */
public interface ISysConfigService extends IService<SysConfig> {

    /**
     * 查询分页数据
     *
     * @param page      页码
     * @param pageCount 每页条数
     * @return IPage<SysConfig>
     */
    IPage<SysConfig> findListByPage(Integer page, Integer pageCount);

    /**
     * 添加
     *
     * @param sysConfig 
     * @return int
     */
    int add(SysConfig sysConfig);

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
     * @param sysConfig 
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
