package com.wyu.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wyu.common.lang.Result;
import com.wyu.entity.CompanyRemsg;
import com.wyu.entity.Jion;
import com.wyu.service.JionService;
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

}
