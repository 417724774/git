package com.wyu.controller;


import cn.hutool.crypto.SecureUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wyu.common.lang.Result;
import com.wyu.entity.CompanyInfo;
import com.wyu.entity.User;
import com.wyu.service.CompanyInfoService;
import com.wyu.service.IMailService;
import com.wyu.service.UserService;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
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

    @Autowired
    IMailService iMailService;

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

    @RequiresAuthentication
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

    @RequiresAuthentication
    @PostMapping("/companyupper")
    public Result companyUpper(@RequestBody CompanyInfo companyInfo) {
        Boolean res = companyInfoService.updateById(companyInfo);
        if(res){
            return Result.success(res);
        }else {
            return Result.fail(res.toString());
        }

    }

    @RequiresAuthentication
    @PostMapping("/delete")
    public Result companyDelete(@RequestBody CompanyInfo companyInfo) {
        Boolean res = companyInfoService.removeById(companyInfo.getCUserid());
        if(res){
            String to = "q417724774@163.com";
            String subject = "五邑大学毕业生就业管理系统企业用户注册结果";
            String content = "尊敬的 "+companyInfo.getCUnit()+"---"+companyInfo.getCName()+" 很抱歉的告诉您，由于您的注册填写资料未能通过校方审核，企业用户注册失败，请前往系统重新进行填写资料注册！http://localhost:8081/company_register";
            iMailService.sendSimpleMail(to,subject,content);
            return Result.success(res);
        }else {
            return Result.fail(res.toString());
        }
    }

    @RequiresAuthentication
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

    @RequiresAuthentication
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

    @RequiresAuthentication
    @GetMapping("/companyfilter")
    public Result companyFilter(){
        List<String> res = new ArrayList<String>();
        List<CompanyInfo> list = companyInfoService.list(new QueryWrapper<CompanyInfo>().select("c_userid").eq("c_status","未审核"));
        if (!list.isEmpty()){
            for (CompanyInfo item : list) {
                res.add(item.getCUserid());
            }
            return Result.success(res);
        }else {
            return Result.success("无数据！");
        }

    }

    @RequiresAuthentication
    @PostMapping("/companycheck")
    public Result companyCheck(@RequestBody CompanyInfo companyInfo){

        if(companyInfo.getCStatus().equals("未审核")){

            companyInfo.setCStatus("正常");
            Boolean res = companyInfoService.updateById(companyInfo);

            User user = new User().setUserId(companyInfo.getCUserid())
                                  .setPassword(companyInfo.getCPassword())
                                  .setUsername(companyInfo.getCName())
                                  .setType("企业")
                                  .setStatus("正常");
            Boolean res1 = userService.save(user);

            if(res&&res1)
                return Result.success(res);
            else
                return Result.fail(res.toString());

        }
        if(companyInfo.getCStatus().equals("已冻结")) {

            companyInfo.setCStatus("正常");
            User user = new User().setStatus("正常").setUserId(companyInfo.getCUserid());
            Boolean res = companyInfoService.updateById(companyInfo);
            Boolean res1 = userService.update(user,new QueryWrapper<User>().eq("user_id",user.getUserId()));
            if(res&&res1)
                return Result.success(res);
            else
                return Result.fail(res.toString());

        }else {
            companyInfo.setCStatus("已冻结");
            Boolean res = companyInfoService.updateById(companyInfo);
            Boolean res1 = userService.update(new User().setStatus("已冻结"),new QueryWrapper<User>().eq("user_id",companyInfo.getCUserid()));
            if(res&&res1)
                return Result.success(res);
            else
                return Result.fail(res.toString());
        }

    }

    @RequiresAuthentication
    @GetMapping("/searchbycunit")
    public Result searchBycunit(@RequestParam(defaultValue = "1") Integer currentPage,@RequestParam String cunit){

        Page page = new Page(currentPage,5);

        IPage pageData = companyInfoService.page(page,new QueryWrapper<CompanyInfo>().like("c_unit",cunit));

        if(pageData.getTotal() >= 1){
            return Result.success(pageData);
        }else {
            return Result.fail("数据不存在！");
        }
    }

    @RequiresAuthentication
    @GetMapping("/searchbycuserid")
    public Result searchBycuserid(@RequestParam(defaultValue = "1") Integer currentPage,@RequestParam String cuserid){

        Page page = new Page(currentPage,5);

        IPage pageData = companyInfoService.page(page,new QueryWrapper<CompanyInfo>().like("c_userid",cuserid));

        if(pageData.getTotal() >= 1){
            return Result.success(pageData);
        }else {
            return Result.fail("数据不存在！");
        }
    }

    @RequiresAuthentication
    @GetMapping("/searchbycaddress")
    public Result searchBycaddress(@RequestParam(defaultValue = "1") Integer currentPage,@RequestParam String caddress){

        Page page = new Page(currentPage,5);

        IPage pageData = companyInfoService.page(page,new QueryWrapper<CompanyInfo>().like("c_address",caddress));

        if(pageData.getTotal() >= 1){
            return Result.success(pageData);
        }else {
            return Result.fail("数据不存在！");
        }
    }

    @PostMapping("/sendemail")
    public Result sendEmail(@RequestBody CompanyInfo companyInfo){

        String to = "1021530563@qq.com";
        String subject = "您有一份新注册确认，请前往认证！";
        String content = "新企业 "+companyInfo.getCUnit()+"---"+companyInfo.getCName()+" 请求注册用户，请前往系统认证！http://localhost:8081/";
        iMailService.sendSimpleMail(to,subject,content);

        return Result.success("发送成功！");

    }

}
