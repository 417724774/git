package com.wyu.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wyu.common.lang.Result;
import com.wyu.entity.CompanyInfo;
import com.wyu.entity.Jion;
import com.wyu.entity.TeacherNotification;
import com.wyu.entity.User;
import com.wyu.service.CompanyInfoService;
import com.wyu.service.JionService;
import com.wyu.service.TeacherNotificationService;
import com.wyu.service.UserService;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 */
@RestController
public class TeacherNotificationController {

    @Autowired
    TeacherNotificationService teacherNotificationService;

    @Autowired
    JionService jionService;

    @Autowired
    CompanyInfoService companyInfoService;

    @RequiresAuthentication
    @PostMapping("/teacher/sendnotification1")
    public Result sendNotification1(@RequestBody TeacherNotification teacherNotification){

        Boolean res = new Boolean(true);

        List<Jion> hasJion = jionService.list(new QueryWrapper<Jion>().select("j_cuserid").eq("j_tjid",teacherNotification.getTnJfid()));

        for (Jion item : hasJion) {
            res = teacherNotificationService.save(teacherNotification.setTnAccept(item.getJCuserid()).setTnPtime(new Date()));
            if (!res)
                return Result.fail(res.toString());
        }
        return Result.success(res);
    }

    @RequiresAuthentication
    @PostMapping("/teacher/sendnotification2")
    public Result sendNotification2(@RequestBody TeacherNotification teacherNotification){

        Boolean res = new Boolean(true);

        List<String> hasjionId = new ArrayList<>();

        List<Jion> hasJion = jionService.list(new QueryWrapper<Jion>().select("j_cuserid").eq("j_tjid",teacherNotification.getTnJfid()));

        for (Jion item: hasJion) {
            hasjionId.add(item.getJCuserid());
        }

        List<CompanyInfo> nojion = companyInfoService.list(new QueryWrapper<CompanyInfo>().select("c_userid").notIn("c_userid",hasjionId));

//        for (CompanyInfo item : nojion) {
//            res = teacherNotificationService.save(teacherNotification.setTnAccept(item.getCUserid()).setTnPtime(new Date()));
//            if (!res)
//                return Result.fail(res.toString());
//        }
        return Result.success(res);
    }

    @RequiresAuthentication
    @PostMapping("/teacher/sendnotification3")
    public Result sendNotification3(@RequestBody TeacherNotification teacherNotification){

        Boolean res = new Boolean(true);

        List<CompanyInfo> nojion = companyInfoService.list(new QueryWrapper<CompanyInfo>().select("c_userid"));
        for (CompanyInfo item : nojion) {
            res = teacherNotificationService.save(teacherNotification.setTnAccept(item.getCUserid()).setTnPtime(new Date()));
            if (!res)
                return Result.fail(res.toString());
        }
        return Result.success(res);
    }

    @RequiresAuthentication
    @GetMapping("/company/noread")
    public Result noRead(@RequestParam String tnaccept){

        List<TeacherNotification> res = teacherNotificationService.list(new QueryWrapper<TeacherNotification>().eq("tn_accept",tnaccept).eq("tn_status","未读"));

            return Result.success(res);

    }

    @RequiresAuthentication
    @GetMapping("/company/read")
    public Result read(@RequestParam String tnaccept){

        List<TeacherNotification> res = teacherNotificationService.list(new QueryWrapper<TeacherNotification>().eq("tn_accept",tnaccept).eq("tn_status","已读"));

            return Result.success(res);

    }

    @RequiresAuthentication
    @PostMapping("/company/changenotificationstatus")
    public Result changeNotificationStatus(@RequestBody TeacherNotification teacherNotification){

        Boolean res = teacherNotificationService.updateById(teacherNotification);

        return Result.success(res);

    }

    @RequiresAuthentication
    @GetMapping("/company/isexistnoread")
    public Result isExistNoRead(@RequestParam String tnaccept){

        int res = teacherNotificationService.count(new QueryWrapper<TeacherNotification>().eq("tn_accept",tnaccept).eq("tn_status","未读"));

        return Result.success(res);

    }
}
