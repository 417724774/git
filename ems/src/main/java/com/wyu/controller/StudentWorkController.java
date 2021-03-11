package com.wyu.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wyu.common.lang.Result;
import com.wyu.entity.StudentInfo;
import com.wyu.entity.StudentWork;
import com.wyu.service.StudentInfoService;
import com.wyu.service.StudentWorkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 */
@RestController
public class StudentWorkController {

    @Autowired
    StudentWorkService studentWorkService;

    @Autowired
    StudentInfoService studentInfoService;

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
        Boolean res = studentWorkService.saveOrUpdate(id);

        if(res){
            return Result.success(res);
        }else {
            return Result.fail(res.toString());
        }
    }

    @GetMapping("/teacher/stuworklist")
    public Result stuWorkList(@RequestParam(defaultValue = "1L") Integer currentPage){

        Page page = new Page(currentPage,5);

        IPage<StudentWork> res = studentWorkService.stuWorkList(page);

        if(res.getTotal()>=0){
            return Result.success(res);
        }else {

            return Result.fail("查询失败！");
        }

    }

    @GetMapping("/teacher/countstuwork")
    public Result countStuWork(){

        int stuWork = studentWorkService.count();

        int stu = studentInfoService.count();

        int stuNotWork = stu - stuWork;

        List<Integer> list1 = new ArrayList<Integer>();
        list1.add(stu);
        list1.add(stuWork);
        list1.add(stuNotWork);

        List<String> list2 = new ArrayList<String>();

        return Result.success(list1);

    }
}
