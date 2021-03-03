//package com.wyu.controller;
//
//import cn.hutool.core.map.MapUtil;
//import cn.hutool.crypto.SecureUtil;
//import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
//import com.wyu.common.dto.LoginDto;
//import com.wyu.common.lang.Result;
//import com.wyu.entity.User;
//import com.wyu.service.UserService;
//import com.wyu.util.JwtUtils;
//import org.apache.shiro.SecurityUtils;
//import org.apache.shiro.authz.annotation.RequiresAuthentication;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.util.Assert;
//import org.springframework.validation.annotation.Validated;
//import org.springframework.web.bind.annotation.*;
//
//import javax.servlet.http.HttpServletResponse;
//
//@RestController
//public class AccountController {
//
//    @Autowired
//    UserService userService;
//
//    @Autowired
//    JwtUtils jwtUtils;
//
//    @PostMapping("/login")
//    public Result login(@Validated @RequestBody LoginDto loginDto, HttpServletResponse response) {
//
//        User user = userService.getOne(new QueryWrapper<User>().eq("username", loginDto.getUsername()));
//        Assert.notNull(user, "用户不存在");
//
//        if(!user.getPassword().equals(SecureUtil.md5(loginDto.getPassword()))){
//            return Result.fail("密码不正确");
//        }
//        String jwt = jwtUtils.generateToken(user.getId());
//
//        response.setHeader("Authorization", jwt);
//        response.setHeader("Access-control-Expose-Headers", "Authorization");
//
//        return Result.succ(MapUtil.builder()
//                .put("id", user.getId())
//                .put("username", user.getUsername())
//                .put("avatar", user.getAvatar())
//                .put("email", user.getEmail())
//                .map()
//        );
//    }
//
//    @RequiresAuthentication
//    @GetMapping("/logout")
//    public Result logout() {
//        SecurityUtils.getSubject().logout();
//        return Result.succ(null);
//    }
//
//}
//==========================================
//package com.wyu.controller;
//
//
//        import cn.hutool.core.map.MapUtil;
//        import cn.hutool.crypto.SecureUtil;
//        import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
//        import com.wyu.common.dto.LoginDto;
//        import com.wyu.common.lang.Result;
//        import com.wyu.entity.User;
//        import com.wyu.service.UserService;
//        import com.wyu.util.JwtUtils;
//        import org.apache.shiro.SecurityUtils;
//        import org.apache.shiro.authz.annotation.RequiresAuthentication;
//        import org.springframework.beans.factory.annotation.Autowired;
//        import org.springframework.util.Assert;
//        import org.springframework.validation.annotation.Validated;
//        import org.springframework.web.bind.annotation.*;
//
//        import javax.servlet.http.HttpServletResponse;
//
///**
// * <p>
// *  前端控制器
// * </p>
// */
//@RestController
//public class AccountController {
//    @Autowired
//    UserService userService;
//
//    @Autowired
//    JwtUtils jwtUtils;
//
//    @RequiresAuthentication
//    @GetMapping("/index")
//    public Object index(){
//        User user = userService.getById(1L);
//        return Result.success(user);
//    }
//
//    @PostMapping("/save")
//    public Object save(@Validated @RequestBody User user){
//
//        return Result.success(user);
//    }
//
//    @PostMapping("/login")
//    public Result login(@Validated @RequestBody LoginDto loginDto, HttpServletResponse response){
//
//
//        User user = userService.getOne(new QueryWrapper<User>().eq("user_Id",loginDto.getUserId()));
//        Assert.notNull(user,"用户不存在");
//
//        if(!user.getPassword().equals(SecureUtil.md5(loginDto.getPassword()))){
//            return Result.fail("密码不正确");
//        }
//
//        String jwt = jwtUtils.generateToken(user.getId());
//        response.setHeader("Authorization", jwt);
//        response.setHeader("Access-control-Expose-Headers", "Authorization");
//        return Result.success(MapUtil.builder()
//
//                .put("username",user.getUsername()).map()
//        );
//    }
//
//    @RequiresAuthentication
//    @GetMapping("/logout")
//    public Result logout(){
//        System.out.println("退出前");
//        SecurityUtils.getSubject().logout();
//        System.out.println("退出后");
//        return Result.success(null);
//    }
//}

