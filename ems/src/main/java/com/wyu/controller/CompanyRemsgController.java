package com.wyu.controller;


import cn.hutool.crypto.SecureUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wyu.common.lang.Result;
import com.wyu.entity.CompanyRemsg;
import com.wyu.entity.TeacherInfo;
import com.wyu.service.CompanyRemsgService;
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
@RequestMapping("/company_remsg")
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
                list.add(companyRemsg.getCrCuserid());
            }
            return Result.success(list);
        }else {
            return Result.fail(res.toString());
        }
    }

    @GetMapping("/resumelist")
    public Result resumeList(@RequestParam(defaultValue = "1") Integer currentPage,@RequestParam String cid){

        Page page = new Page(currentPage,5);

        IPage pageData = companyRemsgService.resumeList(page,cid);


        if(pageData.getTotal() >= 1){
            return Result.success(pageData);
        }else {
            return Result.fail("数据不存在！");
        }
    }

    @GetMapping("/resumeupstatus")
    public Result resumeUpStatus(@RequestParam Integer crid){

        CompanyRemsg companyRemsg = new CompanyRemsg().setCrStatus("已阅过").setCrId(crid);
        Boolean res = companyRemsgService.updateById(companyRemsg);
        if(res){
            return Result.success(res);
        }else {
            return Result.fail(res.toString());
        }

    }

    @PostMapping("/resumecount")
    public Result resumeCount(@RequestBody List<Integer> list){

        System.out.println(list);
        List<Integer> res = new ArrayList<Integer>();
//       List<CompanyRemsg> res = companyRemsgService.list(new QueryWrapper<CompanyRemsg>().eq("cr_cuserid",companyRemsg.getCrCuserid()).eq("cr_cjid",companyRemsg.getCrCjid()));

        for (int i=0;i<list.size();i++){

            int count = companyRemsgService.count(new QueryWrapper<CompanyRemsg>().eq("cr_cjid",list.get(i)));
            res.add(count);

        }
        return Result.success(res);
    }

}
