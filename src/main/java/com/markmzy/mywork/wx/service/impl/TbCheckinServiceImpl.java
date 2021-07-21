package com.markmzy.mywork.wx.service.impl;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.http.HttpUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.markmzy.mywork.wx.config.SysConstants;
import com.markmzy.mywork.wx.dao.*;
import com.markmzy.mywork.wx.exception.MyException;
import com.markmzy.mywork.wx.model.TbCheckin;
import com.markmzy.mywork.wx.model.TbFaceModel;
import com.markmzy.mywork.wx.service.ITbCheckinService;
import com.markmzy.mywork.wx.task.EmailTask;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;

/**
 * <p>
 * 签到表 服务实现类
 * </p>
 *
 * @author Zhiyue Ma
 * @since 2021-07-17
 */
@Scope("prototype")
@Service
@Slf4j
public class TbCheckinServiceImpl extends ServiceImpl<TbCheckinMapper, TbCheckin> implements ITbCheckinService
{
    @Autowired
    private SysConstants sysConstants;

    @Autowired
    private TbHolidaysMapper tbHolidaysMapper;

    @Autowired
    private TbWorkdayMapper tbWorkdayMapper;

    @Autowired
    private TbCheckinMapper tbCheckinMapper;

    @Autowired
    private TbFaceModelMapper tbFaceModelMapper;

    @Autowired
    private TbCityMapper tbCityMapper;

    @Autowired
    private TbUserMapper tbUserMapper;

    @Autowired
    private EmailTask emailTask;

    @Value("${mywork.face.createFaceModelUrl}")
    private String createFaceModelUrl;

    @Value("${mywork.face.checkinUrl}")
    private String checkinUrl;

    @Value("${mywork.email.hr}")
    private String hrEmail;

    @Value("${mywork.code}")
    private String code;

    @Override
    public String validCanCheckIn(int userId, String date)
    {
        boolean isHoliday = tbHolidaysMapper.searchtodayIsHoliday() != null ? true : false;
        boolean isWorkday = tbWorkdayMapper.searchTodayIsWorkDay() != null ? true : false;

        String type = "工作日";
        if(DateUtil.date().isWeekend())
            type = "节假日";

        if(isHoliday)
            type = "节假日";
        else if(isWorkday)
            type = "工作日";

        if(type.equals("节假日"))
            return "节假日不需要考勤";
        else
        {
            DateTime now = DateUtil.date();
            String start = DateUtil.today() + " " + sysConstants.attendanceStartTime;
            String end = DateUtil.today() + " " + sysConstants.attendanceEndTime;
            DateTime attendanceStart = DateUtil.parse(start);
            DateTime attendanceEnd = DateUtil.parse(end);
            if(now.isBefore(attendanceStart))
                return "没到上班考勤时间";
            else if(now.isAfter(attendanceEnd))
                return "超过了上班考勤结束时间";
            else
            {
                HashMap map = new HashMap();
                map.put("userId", userId);
                map.put("date", date);
                map.put("start", start);
                map.put("end", end);
                boolean bool = tbCheckinMapper.haveCheckin(map) != null;
                return bool ? "今日已经有考勤记录，无需重复考勤" : "可以考勤";
            }

        }
    }

    @Override
    public void checkin(HashMap param)
    {
        Date now = DateUtil.date();
        Date start = DateUtil.parse(DateUtil.today() + " " + sysConstants.attendanceTime);
        Date end = DateUtil.parse(DateUtil.today() + " " + sysConstants.attendanceEndTime);

        int status = 1; //正常考勤

        if(now.after(start) && now.before(end))
            status = 2; //迟到

        int userId = (Integer) param.get("userId");
        String faceModel = tbFaceModelMapper.searchFaceModel(userId);
        if(faceModel == null)
        {
            throw new MyException("不存在人脸模型");
        }
        else
        {
            String path = (String) param.get("path");
            HttpRequest request = HttpUtil.createPost(checkinUrl);
            request.form("photo", FileUtil.file(path));
            request.form("targetModel", faceModel);
            request.form("code", code);
            HttpResponse response = request.execute();
            if(response.getStatus() != 200)
            {
                log.error("人脸识别服务异常");
                throw new MyException("人脸识别服务异常");
            }

            String body = response.body();
            if("无法识别出人脸".equals(body) || "照片中存在多张人脸".equals(body))
            {
                throw new MyException(body);
            }
            else if("False".equals(body))
            {
                throw new MyException("签到无效，非本人签到");
            }
            else if("True".equals(body))
            {
                int risk = 1; //低风险
                String city = (String) param.get("city");
                String district = (String) param.get("district");
                String address = (String) param.get("address");
                String country = (String) param.get("country");
                String province = (String) param.get("province");

                if(!StrUtil.isBlank(city) && !StrUtil.isBlank(district))
                {
                    String code = tbCityMapper.searchCode(city);
                    try
                    {
                        String url = "";
                        if(code.equals("http://m.bendibao.com/news/yqdengji/")) //深圳本站
                            url = code + "?qu=" + district;
                        else
                            url = "http://m." + code + ".bendibao.com/news/yqdengji/?qu=" + district;
                        Document document = Jsoup.connect(url).get();
                        Elements elements = document.getElementsByClass("list-content");
                        if(elements.size() > 0)
                        {
                            Element element = elements.get(0);
                            String result = element.select("p:last-child").text();
                            if("高风险".equals(result)) //异步发送警告邮件给HR
                            {
                                risk = 3; //高风险
                                HashMap<String, String> map = tbUserMapper.searchNameAndDept(userId);
                                String name = map.get("name");
                                String deptName = map.get("dept_name");
                                deptName = deptName != null ? deptName : "";
                                SimpleMailMessage message = new SimpleMailMessage();
                                message.setTo(hrEmail);
                                message.setSubject(name + "身处高风险疫情地区警告");
                                message.setText(deptName + "员工" + name + "，" + DateUtil.format(new Date(), "yyyy年MM月dd日") + "处于" + address + "，属于新冠疫情高风险地区，请及时与该员工联系，核实情况！");
                                emailTask.sendAsync(message);
                            }
                            else if("中风险".equals(result))
                            {
                                risk = 2; //中风险
                            }
                        }
                    } catch(DuplicateKeyException e)
                    {
                        log.error("无法重复签到", e);
                        throw new MyException("无法重复签到");
                    } catch(Exception e)
                    {
                        log.error("执行异常", e);
                        throw new MyException("获取风险等级失败");
                    }

                }

                TbCheckin entity = new TbCheckin();
                entity.setUserId(userId);
                entity.setAddress(address);
                entity.setCountry(country);
                entity.setProvince(province);
                entity.setCity(city);
                entity.setDistrict(district);
                entity.setStatus(status);
                entity.setRisk(risk);
                entity.setDate(DateUtil.today());
                entity.setCreateTime(now);
                tbCheckinMapper.insertCheckin(entity);
            }
        }

    }

    @Override
    public void createFaceModel(int userId, String path)
    {
        HttpRequest request = HttpUtil.createPost(createFaceModelUrl);
        request.form("photo", FileUtil.file(path));
        request.form("code", code);
        HttpResponse response = request.execute();
        String body = response.body();
        if("无法识别出人脸".equals(body) || "照片中存在多张人脸".equals(body))
        {
            throw new MyException(body);
        }
        else
        {
            TbFaceModel entity = new TbFaceModel();
            entity.setUserId(userId);
            entity.setFaceModel(body);
            tbFaceModelMapper.insertFaceModel(entity);
        }
    }
}
