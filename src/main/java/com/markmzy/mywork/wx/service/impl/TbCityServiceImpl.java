package com.markmzy.mywork.wx.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.markmzy.mywork.wx.dao.TbCityMapper;
import com.markmzy.mywork.wx.model.TbCity;
import com.markmzy.mywork.wx.service.ITbCityService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 疫情城市列表 服务实现类
 * </p>
 *
 * @author Zhiyue Ma
 * @since 2021-07-17
 */
@Service
public class TbCityServiceImpl extends ServiceImpl<TbCityMapper, TbCity> implements ITbCityService
{

}
