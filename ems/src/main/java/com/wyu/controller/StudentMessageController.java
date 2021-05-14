package com.wyu.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wyu.common.lang.Result;
import com.wyu.entity.CompanyInfo;
import com.wyu.entity.StudentMessage;
import com.wyu.entity.TeacherNotification;
import com.wyu.service.StudentMessageService;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 */
@RestController
@RequestMapping("/student")
public class StudentMessageController {

    @Autowired
    StudentMessageService studentMessageService;

    @RequiresAuthentication
    @GetMapping("/noread")
    public Result noRead(@RequestParam String smaccept){

        List<StudentMessage> res = studentMessageService.list(
                new QueryWrapper<StudentMessage>()
                        .eq("sm_accept",smaccept)
                        .eq("sm_status","未读")
                        .orderByDesc("sm_id"));

        return Result.success(res);

    }

    @RequiresAuthentication
    @GetMapping("/read")
    public Result read(@RequestParam String smaccept){

        List<StudentMessage> res = studentMessageService.list(
                new QueryWrapper<StudentMessage>()
                        .eq("sm_accept",smaccept)
                        .eq("sm_status","已读")
                        .orderByDesc("sm_id"));

        return Result.success(res);

    }

    @RequiresAuthentication
    @PostMapping("/changemessagestatus")
    public Result changeNotificationStatus(@RequestBody StudentMessage studentMessage){

        Boolean res = studentMessageService.updateById(studentMessage);

        return Result.success(res);

    }

    @RequiresAuthentication
    @GetMapping("/isexistnoread")
    public Result isExistNoRead(@RequestParam String smaccept){

        int res = studentMessageService.count(new QueryWrapper<StudentMessage>().eq("sm_accept",smaccept).eq("sm_status","未读"));

        return Result.success(res);

    }

    @RequiresAuthentication
    @PostMapping("/sendmessage")
    public Result sendMessage(@RequestBody StudentMessage StudentMessage){

        Boolean res = studentMessageService.save(StudentMessage.setSmPtime(new Date()));

        if (!res)
            return Result.fail(res.toString());
        else
            return Result.success(res);
    }

    @RequiresAuthentication
    @PostMapping("/hassend")
    public Result hasSend(@RequestBody StudentMessage studentMessage){

        List<StudentMessage> res = studentMessageService.list(new QueryWrapper<StudentMessage>().eq("sm_accept",studentMessage.getSmAccept()).eq("sm_manid",studentMessage.getSmManid()).orderByDesc("sm_id"));

        if (!res.isEmpty()){
            return Result.success(res);
        }else {
            return Result.fail(res.toString());
        }
    }

}
