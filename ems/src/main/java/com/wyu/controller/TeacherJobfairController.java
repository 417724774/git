package com.wyu.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wyu.common.lang.Result;
import com.wyu.entity.CompanyJob;
import com.wyu.entity.CompanyRemsg;
import com.wyu.entity.TeacherJobfair;
import com.wyu.service.TeacherJobfairService;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
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
@RequestMapping("/teacher")
public class TeacherJobfairController {

    @Autowired
    TeacherJobfairService teacherJobfairService;

    @RequiresAuthentication
    @GetMapping("/jobfairlist")
    public Result jobFairList(@RequestParam(defaultValue = "1") Integer currentPage) {
        Page page = new Page(currentPage,5);
        IPage pageData = teacherJobfairService.page(page,new QueryWrapper<TeacherJobfair>().orderByDesc("jf_id"));
        if(pageData.getTotal() >= 1){
            return Result.success(pageData);
        }else {
            return Result.fail("数据不存在！");
        }
    }

    @RequiresAuthentication
    @GetMapping("/jobfairdelete")
    public Result jobFairDelete(@RequestParam Integer jfid) {

        Boolean res = teacherJobfairService.removeById(jfid);
        if(res){
            return Result.success(res);
        }else {
            return Result.fail(res.toString());
        }
    }

    @RequiresAuthentication
    @PostMapping("/jobfairsaveupadate")
    public Result jobFairUpdate(@RequestBody TeacherJobfair teacherJobfair) {

        Boolean res = teacherJobfairService.saveOrUpdate(teacherJobfair,new QueryWrapper<TeacherJobfair>().eq("jf_id",teacherJobfair.getJfId()));
        if(res){
            return Result.success(res);
        }else {
            return Result.fail(res.toString());
        }
    }

    @RequiresAuthentication
    @GetMapping("/jobfairdetail")
    public Result jobFairDetail(@RequestParam Integer jfid) {

        TeacherJobfair res = teacherJobfairService.getOne(new QueryWrapper<TeacherJobfair>().eq("jf_id",jfid));

        if(res != null){
            return Result.success(res);
        }else {
            return Result.fail("查询失败！");
        }
    }

    @RequiresAuthentication
    @PostMapping("/jobfairfilter")
    public Result jobFairFilter(@RequestBody TeacherJobfair teacherJobfair ) {

        List<TeacherJobfair> res = teacherJobfairService.list(new QueryWrapper<TeacherJobfair>().eq("jf_tuserid",teacherJobfair.getJfTuserid()));
        if(!res.isEmpty()){
            List<Integer> list = new ArrayList<Integer>();
            for ( TeacherJobfair teacherJobfair1: res
            ) {
                list.add(teacherJobfair1.getJfId());
            }
            return Result.success(list);
        }else {
            return Result.fail(res.toString());
        }
    }

    @RequiresAuthentication
    @GetMapping("/jobfairlog")
    public Result jobFairLog() {

        List<TeacherJobfair> res = teacherJobfairService.list(new QueryWrapper<TeacherJobfair>());
        if(res.size() >= 1){
            return Result.success(res);
        }else {
            return Result.fail("数据不存在！");
        }
    }

}
