package com.wyu.controller;


import cn.hutool.crypto.SecureUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wyu.common.lang.Result;
import com.wyu.entity.CompanyInfo;
import com.wyu.entity.User;
import com.wyu.service.CompanyInfoService;
import com.wyu.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

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

    @Autowired
    UserService userService;

    @PostMapping("/register")
    public Object save(@Validated @RequestBody CompanyInfo companyInfo){
        System.out.println(companyInfo.toString());

        CompanyInfo get = companyInfoService.getById(companyInfo);

        if (get == null){

            companyInfo.setCStatus("未审核");
            Boolean res = companyInfoService.save(companyInfo);

            if(res){
                return Result.success(res);
            }else {
                return Result.fail("注册失败！");
            }

        }else {

            return Result.fail("用户名已被注册！请重新填写！");

        }


//        return Result.success(companyInfo);
    }

    @PostMapping("/detail")
    public Object companyDetail( @RequestBody CompanyInfo companyInfo){
        CompanyInfo res = companyInfoService.getById(companyInfo.getCUserid());

        if(res != null){
            return Result.success(res);
        }else {
            return Result.fail("获取信息失败！");
        }
//        return Result.success(companyInfo);
    }

    @PostMapping("/companyupper")
    public Result companyUpper(@RequestBody CompanyInfo companyInfo) {
        Boolean res = companyInfoService.updateById(companyInfo);
        if(res){
            return Result.success(res);
        }else {
            return Result.fail(res.toString());
        }

    }

    @PostMapping("/companyuppwd")
    public Result companyUppwd(@RequestBody CompanyInfo companyInfo) {
        companyInfo.setCPassword(SecureUtil.md5(companyInfo.getCPassword()));
        User user = new User().setUserId(companyInfo.getCUserid()).setPassword(companyInfo.getCPassword());

        Boolean res1 = userService.update(user,new QueryWrapper<User>().eq("user_id",user.getUserId()));
        Boolean res = companyInfoService.updateById(companyInfo);
        if(res){
            return Result.success(res);
        }else {
            return Result.fail(res.toString());
        }
    }

    @GetMapping("/companylist")
    public Result companyList(@RequestParam(defaultValue = "1L") Integer currentPage){

        Page page = new Page(currentPage,5);

        IPage pageData = companyInfoService.page(page,new QueryWrapper<CompanyInfo>().orderByDesc("c_userid"));

        if(pageData.getTotal() >= 1){
            return Result.success(pageData);
        }else {
            return Result.fail("数据不存在！");
        }

    }

    @GetMapping("/companyfilter")
    public Result companyFilter(){
        List<String> res = new ArrayList<String>();
        List<CompanyInfo> list = companyInfoService.list(new QueryWrapper<CompanyInfo>().select("c_userid").eq("c_status","已审核"));
        if (!list.isEmpty()){
            for (CompanyInfo item : list) {
                res.add(item.getCUserid());
            }
            return Result.success(res);
        }else {
            return Result.success("无数据！");
        }

    }

    @PostMapping("/companycheck")
    public Result companyCheck(@RequestBody CompanyInfo companyInfo){

        CompanyInfo get = companyInfoService.getById(companyInfo);

        if(get.getCStatus().equals("未审核")){

            companyInfo.setCStatus("已审核");
            Boolean res = companyInfoService.updateById(companyInfo);

            User user = new User().setUserId(get.getCUserid()).setPassword(get.getCPassword()).setUsername(get.getCName()).setType("企业");
            Boolean res1 = userService.save(user);

            if(res&&res1)
                return Result.success(res);
            else
                return Result.fail(res.toString());

        }else {

            companyInfo.setCStatus("未审核");
            Boolean res = companyInfoService.updateById(companyInfo);

            Boolean res1 = userService.remove(new QueryWrapper<User>().eq("user_id",get.getCUserid()));

            if(res&&res1)
                return Result.success(res);
            else
                return Result.fail(res.toString());

        }



    }

}
