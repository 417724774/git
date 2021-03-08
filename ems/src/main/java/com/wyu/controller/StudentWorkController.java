package com.wyu.controller;


import com.wyu.common.lang.Result;
import com.wyu.entity.StudentInfo;
import com.wyu.entity.StudentWork;
import com.wyu.service.StudentWorkService;
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
public class StudentWorkController {

    @Autowired
    StudentWorkService studentWorkService;

    @PostMapping("/studentworkinfo")
    public Result studentWorkInfo(@RequestBody StudentWork id) {
        StudentWork res = studentWorkService.getById(id.getSwStuid());
        if(res != null){
            return Result.success(res);
        }else {
            return Result.fail(res.toString());
        }
    }

    @PostMapping("/studentworkupdate")
    public Result studentWorkUpdate(@RequestBody StudentWork id) {
        System.out.println(id);
        Boolean res = studentWorkService.saveOrUpdate(id);

        if(res){
            return Result.success(res);
        }else {
            return Result.fail(res.toString());
        }
    }
}
