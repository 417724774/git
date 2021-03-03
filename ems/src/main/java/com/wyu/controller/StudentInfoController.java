package com.wyu.controller;


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
        System.out.println(studentInfo);
        return Result.success(studentInfo);
    }
}
