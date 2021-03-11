package com.wyu.controller;


import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wyu.common.lang.Result;
import com.wyu.entity.CompanyJob;
import com.wyu.entity.StudentInfo;
import com.wyu.entity.StuempInfo;
import com.wyu.service.CompanyJobService;
import com.wyu.service.CompanyRemsgService;
import com.wyu.service.StuempInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 */
@RestController
@RequestMapping("/stuempinfo")
public class StuempInfoController {
    @Autowired
    StuempInfoService stuempInfoService;

    @Autowired
    CompanyRemsgService companyRemsgService;

    @Autowired
    CompanyJobService companyJobService;


    @PostMapping("/studentresume")
    public Result studentresume(@RequestBody StuempInfo stures) {
        StuempInfo res = stuempInfoService.getOne(new QueryWrapper<StuempInfo>().eq("se_stuid",stures.getSeStuid()));
        if(res !=null){
            return Result.success(res);
        }else {
            return Result.fail("查询失败！");
        }
    }

    @PostMapping("/stuemupdate")
    public Result stuemupdate(@RequestBody StuempInfo stures) {
        System.out.println(stures);
        Boolean res = stuempInfoService.saveOrUpdate(stures,new UpdateWrapper<StuempInfo>().eq("se_stuid",stures.getSeStuid()));
        if(res){
            return Result.success(res);
        }else {
            return Result.fail(res.toString());
        }
    }

    @GetMapping("/comjoblist")
    public Result comJobList(@RequestParam(defaultValue = "1") Integer currentPage){

        Page page = new Page(currentPage,5);

        IPage pageData = companyRemsgService.comJobList(page);

        if(pageData.getTotal() >= 1){
            return Result.success(pageData);
        }else {
            return Result.fail("数据不存在！");
        }
    }

    @GetMapping("/companydetailjoblist")
    public Result companyDetailJobList(@RequestParam String cuserid){
        List<CompanyJob> res = companyJobService.list(new QueryWrapper<CompanyJob>().eq("cj_cuserid",cuserid).eq("cj_status","已通过"));

        if(res != null){
            String strList ="";

            for (CompanyJob cj: res) {
                String str = "招聘岗位："+cj.getCjType()+" \n"+
                        "有效时间："+new SimpleDateFormat("yyyy-MM-dd").format(cj.getCjDeadline()) +"\n"+
                        "工作地点："+cj.getCjAdress()+"\n"+
                        "薪资："+cj.getCjSalary()+"\n"+
                        "年龄要求："+cj.getCjAge()+" 岁以上"+"\n"+
                        "职位性质："+cj.getCjProperty()+"\n"+
                        "基本要求："+cj.getCjDemand()+"\n"+
                        "职位描述："+cj.getCjDescription()+"\n\n";

                strList += str;
            }
            return Result.success(strList);
        }else {
            return Result.fail(res.toString());
        }
    }
}
