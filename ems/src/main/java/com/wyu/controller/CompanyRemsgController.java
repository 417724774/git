package com.wyu.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wyu.common.lang.Result;
import com.wyu.entity.CompanyRemsg;
import com.wyu.service.CompanyRemsgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 */
@RestController
@RequestMapping("/company-remsg")
public class CompanyRemsgController {
    @Autowired
    CompanyRemsgService companyRemsgService;

    @PostMapping("/save")
    public Result companyRemsgSave(@RequestBody CompanyRemsg companyRemsg){

        companyRemsg.setCrStatus("新投递");
        Boolean res =  companyRemsgService.save(companyRemsg);

        if(res){
            return Result.success(res);
        }else {
            return Result.fail(res.toString());
        }
    }

    @PostMapping("/filter")
    public Result companyRemsgFilter(@RequestBody CompanyRemsg sid){
        List<CompanyRemsg> res =  companyRemsgService.list(new QueryWrapper<CompanyRemsg>().eq("cr_stuid",sid.getCrStuid()));

        if(!res.isEmpty()){
            List<String> list = new ArrayList<String>();
            for ( CompanyRemsg companyRemsg: res
            ) {
                list.add(companyRemsg.getCrCid());
            }
            return Result.success(list);
        }else {
            return Result.fail(res.toString());
        }
    }

}
