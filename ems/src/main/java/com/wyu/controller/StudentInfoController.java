package com.wyu.controller;


import cn.hutool.crypto.SecureUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.wyu.common.dto.LoginDto;
import com.wyu.common.lang.Result;
import com.wyu.entity.StudentInfo;
import com.wyu.entity.User;
import com.wyu.service.StudentInfoService;
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

    @PostMapping("/studentuppwd")
    public Result studentuppwd(@RequestBody StudentInfo student) {
        student.setSPassword(SecureUtil.md5(student.getSPassword()));
        Boolean res = studentInfoService.updateById(student);
        if(res){
            return Result.success(res);
        }else {
            return Result.fail(res.toString());
        }
    }

    @PostMapping("/studentupper")
    public Result studentupper(@RequestBody StudentInfo student) {
        Boolean res = studentInfoService.updateById(student);
        if(res){
            return Result.success(res);
        }else {
            return Result.fail(res.toString());
        }

    }
}
