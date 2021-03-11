package com.wyu.controller;


import cn.hutool.crypto.SecureUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wyu.common.lang.Result;
import com.wyu.entity.StudentInfo;
import com.wyu.entity.TeacherInfo;
import com.wyu.entity.User;
import com.wyu.service.TeacherInfoService;
import com.wyu.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 */
@RestController
@RequestMapping("/teacher")
public class TeacherInfoController {
    @Autowired
    TeacherInfoService teacherInfoService;

    @Autowired
    UserService userService;

    @PostMapping("/teacherupper")
    public Result teacherUpPer(@RequestBody TeacherInfo teacherInfo){

        Boolean res = teacherInfoService.saveOrUpdate(teacherInfo,new QueryWrapper<TeacherInfo>().eq("t_userid",teacherInfo.getTUserid()));

        if (res){
            return Result.success(res);
        }else {
            return Result.fail("操作失败！");
        }

    }

    @PostMapping("/teacherinfo")
    public Result studentInfo(@RequestBody TeacherInfo id) {
        TeacherInfo teacherInfo = teacherInfoService.getById(id.getTUserid());
        teacherInfo.setTPassword("");
        if(teacherInfo != null){
            return Result.success(teacherInfo);
        }else {
            return Result.fail("查询错误！");
        }
    }

    @PostMapping("/teacheruppwd")
    public Result teacherUpPwd(@RequestBody TeacherInfo teacherInfo) {
        teacherInfo.setTPassword(SecureUtil.md5(teacherInfo.getTPassword()));

        User user = new User().setUserId(teacherInfo.getTUserid()).setPassword(teacherInfo.getTPassword());

        Boolean res1 = userService.update(user,new QueryWrapper<User>().eq("user_id",user.getUserId()));

        Boolean res = teacherInfoService.updateById(teacherInfo);
        if(res){
            return Result.success(res);
        }else {
            return Result.fail(res.toString());
        }
    }

}