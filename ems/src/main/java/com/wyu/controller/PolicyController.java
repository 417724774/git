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

/**
 * <p>
 *  前端控制器
 * </p>
 */
@RestController
public class PolicyController {
    @Autowired
    PolicyService policyService;

    @GetMapping("/student_policylist")
    public Result policyList (@RequestParam(defaultValue = "1") Integer currentPage){
        Page page = new Page(currentPage,5);
        IPage pageData = policyService.page(page,new QueryWrapper<Policy>().orderByDesc("p_date"));

        if(pageData.getTotal() >= 1){
            return Result.success(pageData);
        }else {
            return Result.fail("数据不存在！");
        }
    }

    @PostMapping("/student_policy_detail")
    public Object policyDetail( @RequestBody Policy policy){
        Policy res = policyService.getById(policy.getPId());

        if(res != null){
            return Result.success(res);
        }else {
            return Result.fail("获取信息失败！");
        }
//        return Result.success(companyInfo);
    }
}
