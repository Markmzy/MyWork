package com.markmzy.mywork.wx.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.markmzy.mywork.wx.model.TbModule;

/**
 * <p>
 * 模块资源表 服务类
 * </p>
 *
 * @author Zhiyue Ma
 * @since 2021-07-16
 */
public interface ITbModuleService extends IService<TbModule>
{

    /**
     * 查询模块资源表分页数据
     *
     * @param page      页码
     * @param pageCount 每页条数
     * @return IPage<TbModule>
     */
    IPage<TbModule> findListByPage(Integer page, Integer pageCount);

    /**
     * 添加模块资源表
     *
     * @param tbModule 模块资源表
     * @return int
     */
    int add(TbModule tbModule);

    /**
     * 删除模块资源表
     *
     * @param id 主键
     * @return int
     */
    int delete(Long id);

    /**
     * 修改模块资源表
     *
     * @param tbModule 模块资源表
     * @return int
     */
    int updateData(TbModule tbModule);

    /**
     * id查询数据
     *
     * @param id id
     * @return TbModule
     */
    TbModule findById(Long id);
}
