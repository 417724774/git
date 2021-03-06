package com.wyu.controller;


import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.wyu.common.lang.Result;
import com.wyu.entity.StudentInfo;
import com.wyu.entity.StuempInfo;
import com.wyu.service.StuempInfoService;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping("/stuempinfo")
public class StuempInfoController {
    @Autowired
    StuempInfoService stuempInfoService;


    @PostMapping("/studentresume")
    public Result studentresume(@RequestBody StuempInfo stures) {
        StuempInfo res = stuempInfoService.getOne(new QueryWrapper<StuempInfo>().eq("se_stuid",stures.getSeStuid()));
        if(res !=null){
            return Result.success(res);
        }else {
            return Result.fail("查询失败！");
        }
    }

    @PostMapping("/stuemupdate")
    public Result stuemupdate(@RequestBody StuempInfo stures) {
        System.out.println(stures);
        Boolean res = stuempInfoService.update(stures,new UpdateWrapper<StuempInfo>().eq("se_stuid",stures.getSeStuid()));
        if(res){
            return Result.success(res);
        }else {
            return Result.fail(res.toString());
        }
    }
}
