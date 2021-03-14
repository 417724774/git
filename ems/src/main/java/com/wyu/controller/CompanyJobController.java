package com.wyu.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wyu.common.lang.Result;
import com.wyu.entity.*;
import com.wyu.service.CompanyJobService;
import com.wyu.service.JionService;
import com.wyu.service.TeacherJobfairService;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 */
@RestController
@RequestMapping("/company")
public class CompanyJobController {
    @Autowired
    CompanyJobService companyJobService;

    @Autowired
    TeacherJobfairService teacherJobfairService;

    @Autowired
    JionService jionService;

    @RequiresAuthentication
    @GetMapping("/joblist")
    public Result jobList(@RequestParam String id) {
        List<CompanyJob> res = companyJobService.list(new QueryWrapper<CompanyJob>().eq("cj_cuserid",id).eq("cj_status","已通过"));
        if(!res.isEmpty()){
            return Result.success(res);
        }else {
            return Result.fail("数据不存在！");
        }
    }

    @RequiresAuthentication
    @GetMapping("/getjobstatus")
    public Result getJobStatus() {
        List<CompanyJob> res = companyJobService.list(new QueryWrapper<CompanyJob>());
        if(!res.isEmpty()){
            return Result.success(res);
        }else {
            return Result.fail("数据不存在！");
        }
    }

    @RequiresAuthentication
    @GetMapping("/joblistbyid")
    public Result jobListById(@RequestParam(defaultValue = "1") Integer currentPage,@RequestParam String id) {
        Page page = new Page(currentPage,5);
        IPage pageData = companyJobService.page(page,new QueryWrapper<CompanyJob>().eq("cj_cuserid",id));

        if(pageData.getTotal() >= 1){
            return Result.success(pageData);
        }else {
            return Result.fail("数据不存在！");
        }
    }

    @RequiresAuthentication
    @PostMapping("/jobdetail")
    public Result jobDetail(@RequestBody String id) {

        CompanyJob res = companyJobService.getOne(new QueryWrapper<CompanyJob>().eq("cj_id",Integer.parseInt(id.substring(0,id.length()-1))));

        if(res != null){
            return Result.success(res);
        }else {
            return Result.fail("查询失败！");
        }
    }

    @RequiresAuthentication
    @PostMapping("/jobsaveorupdate")
    public Result jobUpdate(@RequestBody CompanyJob companyJob) {

        companyJob.setCjStatus("未审核");

        Boolean res = companyJobService.saveOrUpdate(companyJob,new UpdateWrapper<CompanyJob>().eq("cj_id",companyJob.getCjId()));
        if(res){
            return Result.success(res);
        }else {
            return Result.fail(res.toString());
        }
    }

    @RequiresAuthentication
    @GetMapping("/jobdelete")
    public Result jobDelete(@RequestParam Integer cjid) {

        Boolean res = companyJobService.removeById(cjid);
        if(res){
            return Result.success(res);
        }else {
            return Result.fail(res.toString());
        }
    }

    @RequiresAuthentication
    @GetMapping("/jobfairlist")
    public Result getJobFairList(@RequestParam(defaultValue = "1") Integer currentPage){
        Page page = new Page(currentPage,5);
        IPage pageData = teacherJobfairService.page(page,new QueryWrapper<TeacherJobfair>().orderByDesc("jf_id"));

        if(pageData.getTotal() >= 1){
            return Result.success(pageData);
        }else {
            return Result.fail("数据不存在！");
        }

    }

    @RequiresAuthentication
    @PostMapping("/jionjobfair")
    public Result jionJobFair(@RequestBody Jion jion){

        Boolean res = jionService.save(jion);

        if(res){
            return Result.success(res);
        }else {
            return Result.fail("数据不存在！");
        }

    }

    @RequiresAuthentication
    @PostMapping("/changestatus")
    public Result changeStatus(@RequestBody CompanyJob job) {
        Boolean res =  companyJobService.updateById(job);

        if(res){
            return Result.success(res);
        }else {
            return Result.fail("操作失败！");
        }
    }

}
