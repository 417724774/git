package com.wyu.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wyu.common.lang.Result;
import com.wyu.entity.CompanyInfo;
import com.wyu.entity.Policy;
import com.wyu.service.PolicyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 */
@RestController
public class PolicyController {
    @Autowired
    PolicyService policyService;

    @GetMapping("/student/policylist")
    public Result policyList (@RequestParam(defaultValue = "1") Integer currentPage){

        List<Policy> res = policyService.list(new QueryWrapper<Policy>().orderByDesc("p_id"));

        if(!res.isEmpty()){
            return Result.success(res);
        }else {
            return Result.fail("数据不存在！");
        }
    }

    @PostMapping("/student/policy_detail")
    public Object studentPolicyDetail( @RequestBody Policy policy){
        Policy res = policyService.getById(policy.getPId());

        if(res != null){
            return Result.success(res);
        }else {
            return Result.fail("获取信息失败！");
        }
//        return Result.success(companyInfo);
    }

    @GetMapping("/teacher/policylist")
    public Result teacherPolicyList (@RequestParam(defaultValue = "1") Integer currentPage){
        Page page = new Page(currentPage,5);
        IPage pageData = policyService.page(page,new QueryWrapper<Policy>());

        if(pageData.getTotal() >= 1){
            return Result.success(pageData);
        }else {
            return Result.fail("数据不存在！");
        }
    }

    @GetMapping("/teacher/policy")
    public Result teacherPolicy (){
        Page page = new Page(1,5);
        IPage pageData = policyService.page(page,new QueryWrapper<Policy>());

        if(pageData.getTotal() >= 1){
            return Result.success(pageData);
        }else {
            return Result.fail("数据不存在！");
        }
    }

    @PostMapping("/teacher/policyfilter")
    public Result policyFilter(@RequestBody Policy policy){

        List<Policy> list = policyService.list(new QueryWrapper<Policy>().eq("p_man",policy.getPMan()));

        List<Integer> res = new ArrayList<Integer>();

        if(!list.isEmpty()) {
            for (Policy item : list) {
                res.add(item.getPId());
            }

            return Result.success(res);

        }else {

            return Result.success("无数据");
        }


    }

    @PostMapping("/teacher/policyadd")
    public Result policyAdd(@RequestBody Policy policy){

        policy.setPUpdate(new Date());

        Boolean res = policyService.save(policy);

        if(res){

            return Result.success(res);

        }else {

            return Result.fail(res.toString());

        }

    }

    @GetMapping ("/teacher/policydelete")
    public Result policyDelete(@RequestParam Integer pid){

        System.out.println(pid);

        Boolean res = policyService.removeById(pid);

        if(res){

            return Result.success(res);

        }else {

            return Result.fail(res.toString());

        }

    }

    @GetMapping ("/teacher/policydetail")
    public Object teacherPolicyDetail( @RequestParam Integer pid){
        Policy res = policyService.getById(pid);

        if(res != null){
            return Result.success(res);
        }else {
            return Result.fail("获取信息失败！");
        }
//        return Result.success(companyInfo);
    }

    @PostMapping("/teacher/policyupdate")
    public Result policyUpdate(@RequestBody Policy policy){

        policy.setPUpdate(new Date());

        Boolean res = policyService.updateById(policy);

        if(res){

            return Result.success(res);

        }else {

            return Result.fail(res.toString());

        }

    }

}
