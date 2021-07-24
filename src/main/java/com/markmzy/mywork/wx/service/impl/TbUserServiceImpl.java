package com.markmzy.mywork.wx.service.impl;

import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.markmzy.mywork.wx.dao.TbDeptMapper;
import com.markmzy.mywork.wx.dao.TbUserMapper;
import com.markmzy.mywork.wx.exception.MyException;
import com.markmzy.mywork.wx.model.Message;
import com.markmzy.mywork.wx.model.TbUser;
import com.markmzy.mywork.wx.service.ITbUserService;
import com.markmzy.mywork.wx.task.MessageTask;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author Zhiyue Ma
 * @since 2021-07-17
 */
@Slf4j
@Scope("prototype")
@Service
public class TbUserServiceImpl extends ServiceImpl<TbUserMapper, TbUser> implements ITbUserService
{

    @Value("${wx.app-id}")
    private String appId;

    @Value("${wx.app-secret}")
    private String appSecret;

    @Value("${wx.app-superCode}")
    private String superCode;

    @Autowired
    private TbUserMapper tbUserMapper;

    @Autowired
    private MessageTask messageTask;

    @Autowired
    private TbDeptMapper tbDeptMapper;

    @Override
    public String searchOpenId(String code)
    {
        String url = "https://api.weixin.qq.com/sns/jscode2session";
        HashMap map = new HashMap();
        map.put("appid", appId);
        map.put("secret", appSecret);
        map.put("js_code", code);
        map.put("grant_type", "authorization_code");
        String response = HttpUtil.post(url, map);
        JSONObject json = JSONUtil.parseObj(response);
        String openId = json.getStr("openid");
        if(openId == null || openId.length() == 0)
        {
            throw new RuntimeException("临时登陆凭证错误"); //微信平台的问题
        }
        return openId;
    }

    @Override
    public Integer register(String registerCode, String code, String nickname, String photo)
    {
        // 如果邀请码是superCpde， 代表是超级管理员
        if(registerCode.equals(superCode))
        {
            if(!tbUserMapper.haveRootUser()) //如果没有超级管理员，则注册
            {
                String openId = searchOpenId(code);
                HashMap param = new HashMap();
                param.put("openId", openId);
                param.put("nickname", nickname);
                param.put("photo", photo);
                param.put("role", "[0]");
                param.put("status", 1);
                param.put("createTime", new Date());
                param.put("root", true);
                tbUserMapper.insertUser(param);
                int id = tbUserMapper.searchIdByOpenId(openId);

                Message message = new Message();
                message.setSenderId(0);
                message.setSenderName("系统消息");
                message.setMsg("欢迎您注册成为超级管理员，请及时更新您的员工个人信息。");
                message.setSendTime(new Date());
                messageTask.sendAsync(id + "", message);
                return id;

            }
            else
            {
                //如果已经有超级管理员，就抛出自定义异常
                throw new MyException("已经存在一个超级管理员");
            }
        }
        else // 普通用户
        {
            return 0;
        }

    }

    @Override
    public Set<String> searchPermissions(int id)
    {
        return tbUserMapper.searchPermissionsById(id);
    }

    @Override
    public Integer login(String code)
    {
        String openId = searchOpenId(code);
        Integer id = tbUserMapper.searchIdByOpenId(openId);
        if(id == null)
            throw new MyException("账户不存在");

        return id;
    }

    @Override
    public TbUser searchUserById(int userId)
    {
        return tbUserMapper.searchUserById(userId);
    }

    @Override
    public String searchHireDate(int userId)
    {
        return tbUserMapper.searchHireDate(userId);
    }

    @Override
    public HashMap searchUserSummary(int userId)
    {
        return tbUserMapper.searchUserSummary(userId);
    }

    @Override
    public ArrayList<HashMap> searchUserGroupByDept(String keyword)
    {
        ArrayList<HashMap> list1 = tbDeptMapper.searchDeptMembers(keyword);
        ArrayList<HashMap> list2 = tbUserMapper.searchUserGroupByDept(keyword);

        for(HashMap map1 : list1)
        {
            long deptId = (Long) map1.get("id");
            ArrayList members = new ArrayList();
            for(HashMap map2 : list2)
            {
                long id = (Long) map2.get("deptId");
                if(deptId == id)
                {
                    members.add(map2);
                }
            }

            map1.put("members", members);
        }

        return list1;
    }

    @Override
    public ArrayList<HashMap> searchMembers(List param)
    {
        return tbUserMapper.searchMembers(param);
    }
}
