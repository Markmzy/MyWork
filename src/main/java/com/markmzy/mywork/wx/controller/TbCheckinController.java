package com.markmzy.mywork.wx.controller;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.FileUtil;
import com.markmzy.mywork.wx.config.SysConstants;
import com.markmzy.mywork.wx.config.shiro.JwtUtil;
import com.markmzy.mywork.wx.controller.form.CheckinForm;
import com.markmzy.mywork.wx.controller.form.SearchMonthCheckInForm;
import com.markmzy.mywork.wx.exception.MyException;
import com.markmzy.mywork.wx.service.ITbCheckinService;
import com.markmzy.mywork.wx.service.ITbUserService;
import com.markmzy.mywork.wx.util.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
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
    private ITbUserService tbUserService;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private SysConstants sysConstants;

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

        if(!fileName.endsWith(".jpg"))
        {
            return R.error("必须提交JPG格式图片");
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
            } catch(DuplicateKeyException e)
            {
                log.error(e.getMessage(), e);
                throw new MyException("你今天已经签到过了，无法重复签到");
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

        if(!fileName.endsWith(".jpg"))
        {
            return R.error("必须提交JPG格式图片");
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

    @GetMapping("/searchTodayCheckIn")
    @ApiOperation("查询用户当日签到数据")
    public R searchTodayCheckIn(@RequestHeader("token") String token)
    {
        int userId = jwtUtil.getUserId(token);
        HashMap map = tbCheckinService.searchTodayCheckIn(userId);
        map.put("attendanceTime", sysConstants.attendanceTime);
        map.put("closingTime", sysConstants.closingTime);

        long days = tbCheckinService.searchCheckInDays(userId);
        map.put("checkInDays", days);

        DateTime hireDate = DateUtil.parse(tbUserService.searchHireDate(userId));
        DateTime startDate = DateUtil.beginOfWeek(DateUtil.date()); //本周起始
        if(startDate.isBefore(hireDate))
            startDate = hireDate;
        DateTime endDate = DateUtil.endOfWeek(DateUtil.date()); //本周结束
        map.put("weekStart", DateUtil.format(startDate, "yyyy-MM-dd"));
        map.put("weekEnd", DateUtil.format(endDate, "yyyy-MM-dd"));

        HashMap param = new HashMap();
        param.put("startDate", startDate.toString());
        param.put("endDate", endDate.toString());
        param.put("userId", userId);
        ArrayList<HashMap> list = tbCheckinService.searchWeekCheckIn(param);
        map.put("weekCheckIn", list);

        return R.ok("成功获取考勤数据").put("result", map);
    }

    @PostMapping("/searchMonthCheckIn")
    @ApiOperation("查询用户某月签到数据")
    public R searchMonthCheckIn(@Valid @RequestBody SearchMonthCheckInForm form, @RequestHeader("token") String token)
    {
        int userId = jwtUtil.getUserId(token);

        //查询员工入职时间
        DateTime hireDate = DateUtil.parse(tbUserService.searchHireDate(userId));

        //月份变成两位数
        String month = form.getMonth() < 10 ? "0" + form.getMonth() : form.getMonth().toString();

        DateTime startDate = DateUtil.parse(form.getYear() + "-" + month + "-01");

        //入职之前的月份
        if(startDate.isBefore(DateUtil.beginOfMonth(hireDate)))
        {
            throw new MyException("只能查询入职之后的数据");
        }
        //同一个月
        else if(startDate.isBefore(hireDate))
        {
            startDate = hireDate;
        }
        DateTime endDate = DateUtil.endOfMonth(startDate);

        HashMap param = new HashMap();
        param.put("userId", userId);
        param.put("startDate", startDate);
        param.put("endDate", endDate);
        ArrayList<HashMap> list = tbCheckinService.searchMonthCheckIn(param);

        int normal = 0, late = 0, absent = 0;
        for(HashMap<String, String> one : list)
        {
            String type = one.get("type");
            String status = one.get("status");
            if("工作日".equals(type))
            {
                if("正常".equals(status))
                    normal++;
                else if("迟到".equals(status))
                    late++;
                else if("缺勤".equals(status))
                    absent++;
            }
        }

        return Objects.requireNonNull(R.ok().put("list", list).put("normal", normal).put("late", late)).put("absent", absent);
    }

}
