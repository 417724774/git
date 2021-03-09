package com.wyu.controller;


import cn.hutool.core.map.MapUtil;
import cn.hutool.crypto.SecureUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wyu.common.dto.LoginDto;
import com.wyu.common.lang.Result;
import com.wyu.entity.CompanyInfo;
import com.wyu.entity.User;
import com.wyu.service.CompanyInfoService;
import com.wyu.service.UserService;
import com.wyu.util.JwtUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

/**
 * <p>
 *  前端控制器
 * </p>
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;
    @Autowired
    CompanyInfoService companyInfoService;

    @Autowired
    JwtUtils jwtUtils;

    @RequiresAuthentication
    @GetMapping("/index")
    public Object index(){
        User user = userService.getById(1L);
        return Result.success(user);
    }

    @PostMapping("/login")
    public Result login(@Validated @RequestBody LoginDto loginDto, HttpServletResponse response){


        User user = userService.getOne(new QueryWrapper<User>().eq("user_id",loginDto.getUserId()));
        Assert.notNull(user,"用户不存在");

        if(!user.getPassword().equals(SecureUtil.md5(loginDto.getPassword()))){
            return Result.fail("密码不正确");
        }

        String jwt = jwtUtils.generateToken(user.getId());
        response.setHeader("Authorization", jwt);
        response.setHeader("Access-control-Expose-Headers", "Authorization");
        return Result.success(MapUtil.builder()
                .put("userId",user.getUserId())
                .put("username",user.getUsername())
                .put("type",user.getType()).map()
        );
    }

    @RequiresAuthentication
    @GetMapping("/logout")
    public Result logout(){
        System.out.println("退出前");
        SecurityUtils.getSubject().logout();
        System.out.println("退出后");
        return Result.success(null);
    }
}
