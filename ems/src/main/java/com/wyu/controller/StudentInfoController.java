package com.wyu.controller;


import cn.hutool.crypto.SecureUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.wyu.common.dto.LoginDto;
import com.wyu.common.lang.Result;
import com.wyu.entity.StudentInfo;
import com.wyu.entity.User;
import com.wyu.service.StudentInfoService;
import com.wyu.service.UserService;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

/**
 * <p>
 *  前端控制器
 * </p>
 */
@RestController
@RequestMapping("/student")
public class StudentInfoController {

    @Autowired
    StudentInfoService studentInfoService;

    @Autowired
    UserService userService;

    @RequiresAuthentication
    @PostMapping("/studentinfo")
    public Result studentInfo(@RequestBody StudentInfo id) {
        StudentInfo studentInfo = studentInfoService.getById(id.getSUserid());
        studentInfo.setSPassword("");
        if(studentInfo != null){
            return Result.success(studentInfo);
        }else {
            return Result.fail("查询错误！");
        }
    }

    @RequiresAuthentication
    @PostMapping("/studentuppwd")
    public Result studentuppwd(@RequestBody StudentInfo student) {
        student.setSPassword(SecureUtil.md5(student.getSPassword()));

        User user = new User().setUserId(student.getSUserid()).setPassword(student.getSPassword());

        Boolean res1 = userService.update(user,new QueryWrapper<User>().eq("user_id",user.getUserId()));

        Boolean res = studentInfoService.updateById(student);
        if(res){
            return Result.success(res);
        }else {
            return Result.fail(res.toString());
        }
    }

    @RequiresAuthentication
    @PostMapping("/studentupper")
    public Result studentupper(@RequestBody StudentInfo student) {
        Boolean res = studentInfoService.updateById(student);
        if(res){
            return Result.success(res);
        }else {
            return Result.fail(res.toString());
        }

    }

    @RequiresAuthentication
    @GetMapping("/searchbysname")
    public Result searchByName(@RequestParam String sname,@RequestParam(defaultValue = "1L")Integer currentPage){

        Page page = new Page(currentPage,10);
        IPage res = studentInfoService.page(page,new QueryWrapper<StudentInfo>().like("s_name",sname));

        if (res.getTotal() > 0){
            return Result.success(res);
        }else {
            return Result.fail("无当前数据！");
        }

    }

    @RequiresAuthentication
    @GetMapping("/searchbysuserid")
    public Result searchByswStuid(@RequestParam String suserid,@RequestParam(defaultValue = "1L")Integer currentPage){

        Page page = new Page(currentPage,10);
        IPage res = studentInfoService.page(page,new QueryWrapper<StudentInfo>().like("s_userid",suserid));

        if (res.getTotal() > 0){
            return Result.success(res);
        }else {
            return Result.fail("无当前数据！");
        }

    }

    @RequiresAuthentication
    @GetMapping("/searchbysmajor")
    public Result searchByJmajor(@RequestParam String smajor,@RequestParam(defaultValue = "1L")Integer currentPage){

        Page page = new Page(currentPage,10);
        IPage res = studentInfoService.page(page,new QueryWrapper<StudentInfo>().like("s_major",smajor));

        if (res.getTotal() > 0){
            return Result.success(res);
        }else {
            return Result.fail("无当前数据！");
        }

    }
}
