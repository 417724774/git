package com.wyu.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wyu.common.lang.Result;
import com.wyu.entity.TeacherMessage;
import com.wyu.service.TeacherMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 */
@RestController
@RequestMapping("/teacher")
public class TeacherMessageController {
    @Autowired
    TeacherMessageService teacherMessageService;

    @GetMapping("/messagelist")
    public Result messageList(@RequestParam(defaultValue = "1L") Integer currentPage){

        Page page = new Page(currentPage,5);

        IPage<TeacherMessage> res = teacherMessageService.page(page,new QueryWrapper<TeacherMessage>().orderByDesc("tm_id"));

        if (res.getTotal()>=0){
            return Result.success(res);
        }else {

            return Result.fail("查询失败！");
        }

    }

    @PostMapping("/messageadd")
    public Result messageAdd(@RequestBody TeacherMessage teacherMessage){

        teacherMessage.setTmPtime(new Date());

        Boolean res = teacherMessageService.save(teacherMessage);

        if (res){
            return Result.success(res);
        }else {

            return Result.fail(res.toString());
        }

    }

    @GetMapping("/messagedetail")
    public Result messageAdd(@RequestParam Integer tmid){

        TeacherMessage res = teacherMessageService.getById(tmid);

        if (res!=null){
            return Result.success(res);
        }else {

            return Result.fail(res.toString());
        }

    }

    @PostMapping("/messagedelete")
    public Result messageDelete(@RequestBody TeacherMessage teacherMessage){

        Boolean res = teacherMessageService.removeById(teacherMessage.getTmId());

        if (res){
            return Result.success(res);
        }else {

            return Result.fail(res.toString());
        }

    }

    @GetMapping("messagelogs")
    public Result messageLogs(){

        List<TeacherMessage> res = teacherMessageService.list();
        if (!res.isEmpty()){
            return Result.success(res);
        }else {
            return Result.fail("无数据！");
        }

    }
}
