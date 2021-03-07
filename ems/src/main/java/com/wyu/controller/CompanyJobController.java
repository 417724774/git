package com.wyu.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wyu.common.lang.Result;
import com.wyu.entity.CompanyInfo;
import com.wyu.entity.CompanyJob;
import com.wyu.entity.StudentWork;
import com.wyu.entity.StuempInfo;
import com.wyu.service.CompanyJobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.nio.charset.StandardCharsets;

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

    @GetMapping("/joblist")
    public Result jobList(@RequestParam(defaultValue = "1") Integer currentPage,@RequestParam String id) {
        Page page = new Page(currentPage,5);
        IPage pageData = companyJobService.page(page,new QueryWrapper<CompanyJob>().eq("cj_cuserid",id));

        if(pageData.getTotal() >= 1){
            return Result.success(pageData);
        }else {
            return Result.fail("数据不存在！");
        }
    }

    @PostMapping("/jobdetail")
    public Result jobDetail(@RequestBody String id) {

        CompanyJob res = companyJobService.getOne(new QueryWrapper<CompanyJob>().eq("cj_id",Integer.parseInt(id.substring(0,id.length()-1))));

        if(res != null){
            return Result.success(res);
        }else {
            return Result.fail("查询失败！");
        }
    }

    @PostMapping("/jobupdate")
    public Result jobUpdate(@RequestBody CompanyJob companyJob) {
        Boolean res = companyJobService.update(companyJob,new UpdateWrapper<CompanyJob>().eq("cj_cuserid",companyJob.getCjCuserid()));
        if(res){
            return Result.success(res);
        }else {
            return Result.fail(res.toString());
        }
    }

}
