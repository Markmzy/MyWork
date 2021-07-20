package com.markmzy.mywork.wx.service.impl;

import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.markmzy.mywork.wx.dao.TbUserMapper;
import com.markmzy.mywork.wx.exception.MyException;
import com.markmzy.mywork.wx.model.TbUser;
import com.markmzy.mywork.wx.service.ITbUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Set;

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

    @Override
    public String getOpenId(String code)
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
                String openId = getOpenId(code);
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
    public Set<String> getPermissions(int id)
    {
        return tbUserMapper.searchPermissionsById(id);
    }

    @Override
    public Integer login(String code)
    {
        String openId = getOpenId(code);
        Integer id = tbUserMapper.searchIdByOpenId(openId);
        if(id == null)
            throw new MyException("账户不存在");

        //从消息队列接收消息
        return id;
    }

    @Override
    public TbUser getUserById(int userId)
    {
        return tbUserMapper.searchUserById(userId);
    }
}
