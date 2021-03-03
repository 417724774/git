package com.wyu.controller;


import com.wyu.common.lang.Result;
import com.wyu.entity.CompanyInfo;
import com.wyu.service.CompanyInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
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
@RequestMapping("/company")
public class CompanyInfoController {
    @Autowired
    CompanyInfoService companyInfoService;
    @PostMapping("/register")
    public Object save(@Validated @RequestBody CompanyInfo companyInfo){
        System.out.println(companyInfo.toString());
        companyInfo.setCStatus("未审核");
        Boolean res = companyInfoService.saveOrUpdate(companyInfo);

        if(res){
            return Result.success(res);
        }else {
            return Result.fail("注册失败！");
        }
//        return Result.success(companyInfo);
    }
}
