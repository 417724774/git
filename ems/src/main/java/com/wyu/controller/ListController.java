package com.wyu.controller;



import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wyu.common.lang.Result;
import com.wyu.entity.CompanyInfo;
import com.wyu.entity.StudentInfo;
import com.wyu.entity.TeacherInfo;
import com.wyu.service.CompanyInfoService;
import com.wyu.service.StudentInfoService;
import com.wyu.service.TeacherInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 */
@RestController
public class ListController {

    @Autowired
    StudentInfoService studentInfoService;
    @Autowired
    TeacherInfoService teacherInfoService;
    @Autowired
    CompanyInfoService companyInfoService;

    @GetMapping("/studentlist")
    public Result studentList(@RequestParam(defaultValue = "1") Integer currentPage){

        Page page = new Page(currentPage,5);
        IPage pageData = studentInfoService.page(page,new QueryWrapper<StudentInfo>().select("s_userid","s_name","s_college","s_major").orderByDesc("s_userid"));

        if(pageData.getTotal() >= 1){
            return Result.success(pageData);
        }else {
            return Result.fail("数据不存在！");
        }
    }

    @GetMapping("/companylist")
    public Result companyList(@RequestParam(defaultValue = "1") Integer currentPage){

        Page page = new Page(currentPage,5);
        IPage pageData = companyInfoService.page(page,new QueryWrapper<CompanyInfo>().eq("c_status","已审核"));

        if(pageData.getTotal() >= 1){
            return Result.success(pageData);
        }else {
            return Result.fail("数据不存在！");
        }
        

    }

    @GetMapping("/teacherlist")
    public Result teacherList(@RequestParam(defaultValue = "1") Integer currentPage){

        Page page = new Page(currentPage,5);
        IPage pageData = teacherInfoService.page(page,new QueryWrapper<TeacherInfo>().orderByDesc("t_userid"));

        if(pageData.getTotal() >= 1){
            return Result.success(pageData);
        }else {
            return Result.fail("数据不存在！");
        }
    }



}
