package com.markmzy.mywork.wx.controller;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.FileUtil;
import com.markmzy.mywork.wx.config.shiro.JwtUtil;
import com.markmzy.mywork.wx.controller.form.CheckinForm;
import com.markmzy.mywork.wx.exception.MyException;
import com.markmzy.mywork.wx.service.ITbCheckinService;
import com.markmzy.mywork.wx.util.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Objects;

/**
 * <p>
 * 签到表 前端控制器
 * </p>
 *
 * @author Zhiyue Ma
 * @since 2021-07-17
 */
@Api(tags = {"签到表"})
@Slf4j
@RestController
@RequestMapping("/checkin")
public class TbCheckinController
{
    @Resource
    private ITbCheckinService tbCheckinService;

    @Autowired
    private JwtUtil jwtUtil;

    @Value("${mywork.image-folder}")
    private String imageFolder;

    @GetMapping("/validCanCheckIn")
    @ApiOperation("查看用户今天是否可以签到")
    public R validCanCheckIn(@RequestHeader("token") String token)
    {
        int userId = jwtUtil.getUserId(token);
        String result = tbCheckinService.validCanCheckIn(userId, DateUtil.today());
        return R.ok(result);
    }

    @PostMapping("/checkin")
    @ApiOperation("签到")
    public R checkin(@Valid CheckinForm form, @RequestParam("photo") MultipartFile file, @RequestHeader("token") String token)
    {
        if(file == null)
        {
            return R.error("没有上传文件");
        }

        int userId = jwtUtil.getUserId(token);
        String fileName = Objects.requireNonNull(file.getOriginalFilename()).toLowerCase();

        if(!fileName.endsWith(".jpg") || !fileName.endsWith(".jpeg"))
        {
            return R.error("必须提交JPG/JPEG格式图片");
        }
        else
        {
            String path = imageFolder + "/" + fileName;
            try
            {
                file.transferTo(Paths.get(path));
                HashMap param = new HashMap();
                param.put("userId", userId);
                param.put("path", path);
                param.put("city", form.getCity());
                param.put("district", form.getDistrict());
                param.put("address", form.getAddress());
                param.put("country", form.getCountry());
                param.put("province", form.getProvince());
                tbCheckinService.checkin(param);

                return R.ok("签到成功");
            } catch(IOException e)
            {
                log.error(e.getMessage(), e);
                throw new MyException("图片保存错误");
            } finally
            {
                FileUtil.del(path);
            }
        }
    }

    @PostMapping("/createFaceModel")
    @ApiOperation("创建人脸模型")
    public R createFaceModel(@RequestParam("photo") MultipartFile file, @RequestHeader("token") String token)
    {
        if(file == null)
        {
            return R.error("没有上传文件");
        }

        int userId = jwtUtil.getUserId(token);
        String fileName = Objects.requireNonNull(file.getOriginalFilename()).toLowerCase();

        if(!fileName.endsWith(".jpg") || !fileName.endsWith(".jpeg"))
        {
            return R.error("必须提交JPG/JPEG格式图片");
        }
        else
        {
            String path = imageFolder + "/" + fileName;
            try
            {
                file.transferTo(Paths.get(path));
                tbCheckinService.createFaceModel(userId, path);
                return R.ok("人脸建模成功");
            } catch(IOException e)
            {
                log.error(e.getMessage(), e);
                throw new MyException("图片保存错误");
            } finally
            {
                FileUtil.del(path);
            }
        }
    }


}
