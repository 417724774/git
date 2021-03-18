package com.wyu.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wyu.common.lang.Result;
import com.wyu.entity.CompanyInfo;
import com.wyu.entity.CompanyRemsg;
import com.wyu.entity.Jion;
import com.wyu.service.CompanyInfoService;
import com.wyu.service.JionService;
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
@RequestMapping("/jion")
public class JionController {

    @Autowired
    JionService jionService;

    @Autowired
    CompanyInfoService companyInfoService;

    @RequiresAuthentication
    @PostMapping("/jionfilter")
    public Result jionFilter(@RequestBody Jion jion){

        List<Jion> res = jionService.list(new QueryWrapper<Jion>().eq("j_cuserid",jion.getJCuserid()));

        if(!res.isEmpty()){
            List<Integer> list = new ArrayList<Integer>();
            for ( Jion jion1: res
            ) {
                list.add(jion1.getJTjid());
            }
            return Result.success(list);
        }else {
            return Result.fail(res.toString());
        }
    }

    @RequiresAuthentication
    @PostMapping("/jionlist")
    public Result jionList(@RequestBody Jion jion){

        List<String> res = new ArrayList<String>();

        List<Jion> list = jionService.list(new QueryWrapper<Jion>().eq("j_tjid",jion.getJTjid()));

        for (Jion item: list) {
            res.add(item.getJCuserid());
        }

        List<CompanyInfo> list1 = companyInfoService.list(new QueryWrapper<CompanyInfo>().eq("c_status","正常"));

        if(!list.isEmpty()){

            for (int i = 0 ;i < list1.size();i++) {

                for (String id: res) {
                    if (id.equals(list1.get(i).getCUserid()))
                        list1.set(i, list1.get(i).setCStatus("已参加"));
                    else
                        list1.set(i, list1.get(i).setCStatus("未参加"));
                }
            }
        }
        else {
            for (int i = 0 ;i < list1.size();i++) {
                list1.set(i, list1.get(i).setCStatus("未参加"));
            }
        }

        if(!list1.isEmpty()){
            return Result.success(list1);
        }else {
            return Result.fail(res.toString());
        }
    }

    @RequiresAuthentication
    @GetMapping("/anyonejion")
    public Result anyonJion(@RequestParam Integer jfid){

        int res = jionService.count(new QueryWrapper<Jion>().eq("j_tjid",jfid));

        return Result.success(res);

    }

}
