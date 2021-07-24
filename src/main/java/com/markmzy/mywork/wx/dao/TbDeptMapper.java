package com.markmzy.mywork.wx.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.markmzy.mywork.wx.model.TbDept;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * <p>
 * 部门表 Mapper 接口
 * </p>
 *
 * @author Zhiyue Ma
 * @since 2021-07-17
 */
public interface TbDeptMapper extends BaseMapper<TbDept>
{
    ArrayList<HashMap> searchDeptMembers(String keyword);
}
