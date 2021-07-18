package com.markmzy.mywork.wx.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.markmzy.mywork.wx.model.TbCity;

/**
 * <p>
 * 疫情城市列表 服务类
 * </p>
 *
 * @author Zhiyue Ma
 * @since 2021-07-17
 */
public interface ITbCityService extends IService<TbCity>
{

    /**
     * 查询疫情城市列表分页数据
     *
     * @param page      页码
     * @param pageCount 每页条数
     * @return IPage<TbCity>
     */
    IPage<TbCity> findListByPage(Integer page, Integer pageCount);

    /**
     * 添加疫情城市列表
     *
     * @param tbCity 疫情城市列表
     * @return int
     */
    int add(TbCity tbCity);

    /**
     * 删除疫情城市列表
     *
     * @param id 主键
     * @return int
     */
    int delete(Long id);

    /**
     * 修改疫情城市列表
     *
     * @param tbCity 疫情城市列表
     * @return int
     */
    int updateData(TbCity tbCity);

    /**
     * id查询数据
     *
     * @param id id
     * @return TbCity
     */
    TbCity findById(Long id);
}
