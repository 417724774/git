package com.wyu.controller;


import cn.hutool.core.map.MapUtil;
import cn.hutool.crypto.SecureUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
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
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.util.Assert;
import org.springframework.util.FileSystemUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.*;

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

    @Value("${wyu.paiUrl}")
    private String paiUrl;

    @Value("${wyu.file.uploadAvatar}")
    private String avatarPath;

    @RequiresAuthentication
    @GetMapping("/index")
    public Object index(){
        User user = userService.getById(1L);
        return Result.success(user);
    }

    @PostMapping("/login")
    public Result login(@Validated @RequestBody LoginDto loginDto, HttpServletResponse response){


        User user = userService.getOne(new QueryWrapper<User>().eq("user_id",loginDto.getUserId()));
        if(user == null){
            return Result.fail("用户不存在");
        }

        if(!user.getPassword().equals(SecureUtil.md5(loginDto.getPassword()))){
            return Result.fail("用户名或密码不正确");
        }

        if(!user.getStatus().equals("正常")){
            return Result.fail("用户已被冻结，请联系管理员咨询");
        }

        String jwt = jwtUtils.generateToken(user.getId());
        response.setHeader("Authorization", jwt);
        response.setHeader("Access-control-Expose-Headers", "Authorization");
        return Result.success(MapUtil.builder()
                .put("userId",user.getUserId())
                .put("username",user.getUsername())
                .put("avatar",user.getAvatar())
                .put("type",user.getType()).map()
        );
    }

    @RequiresAuthentication
    @GetMapping("/logout")
    public Result logout(){
        SecurityUtils.getSubject().logout();
        return Result.success(null);
    }

    @RequiresAuthentication
    @PostMapping(value = "materialPicture", produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> materialPictureAndText(HttpServletRequest request,
                                                      @RequestParam(value = "userid", required = false) String userid,
                                                      @RequestParam(value = "avatar", required = false) String avatar,
                                                      @RequestParam(value = "file", required = false) MultipartFile file) {
        if (file.isEmpty()) {
            HashMap<String, Object> resultMap = new HashMap<>();
            resultMap.put("msg", "请上传图片");
            return resultMap;
        } else {
            String fileName = file.getOriginalFilename();  // 文件名
            String suffixName = fileName.substring(fileName.lastIndexOf("."));
            String filePath = avatarPath;//这个path就是你要存在服务器上的
            fileName = UUID.randomUUID() + suffixName; // 新文件名
            File dest = new File(filePath + fileName);
            //删除上一次头像
            if(!avatar.equals("null")&&!avatar.equals("")){

               File del = new File(filePath+avatar.substring(avatar.lastIndexOf("/")));
               FileSystemUtils.deleteRecursively(del);

            }
            ////////
            if (!dest.getParentFile().exists()) {
                dest.getParentFile().mkdirs();
            }
            try {
                file.transferTo(dest);
            } catch (IOException e) {
                e.printStackTrace();
            }
            String fileUrl = paiUrl +  fileName;

            User user = new User().setAvatar(fileUrl);
            Boolean res = userService.update(user,new QueryWrapper<User>().eq("user_id",userid));
            HashMap<String, Object> resultMap = new HashMap<>();
            if(res) {
                resultMap.put("msg", "上传图片成功");
                resultMap.put("code", 200);
                resultMap.put("url", fileUrl);
                return resultMap;//这里就是上传图片返回的信息，成功失败异常等，前端根据字段接收就是了
            }else {

                dest.delete();
                resultMap.put("msg", "上传图片失败");
                return resultMap;//这里就是上传图片返回的信息，成功失败异常等，前端根据字段接收就是了
            }
        }
    }

    @RequiresAuthentication
    @GetMapping("/avatar")
    public Result getAvatar(@RequestParam String userid){

        User res = userService.getOne(new QueryWrapper<User>().eq("user_id",userid));

        if (res != null){

            return Result.success(res.getAvatar());

        }else {

            return Result.fail("无数据");

        }

    }

    @RequiresAuthentication
    @PostMapping("/add")
    public Result userAdd(@RequestBody User user){

        user.setPassword(SecureUtil.md5(user.getPassword()));
        user.setStatus("正常");
        Boolean res = userService.save(user);

        if (res){

            return Result.success(res);

        }else {

            return Result.fail("添加失败！");

        }

    }

    @RequiresAuthentication
    @GetMapping("/userlist")
    public Result userList(@RequestParam(defaultValue = "1L")Integer currentPage){

        Page page = new Page(currentPage,10);
        IPage res = userService.page(page,new QueryWrapper<User>());
        if (res.getTotal() > 0){
            return Result.success(res);
        }else {
            return Result.fail("查询失败！");
        }

    }

    @RequiresAuthentication
    @PostMapping("/delete")
    public Result userDelete(@RequestBody List<User> list){
        List<String> failList = new ArrayList<String>();
        for (User user: list) {
            Boolean respone = userService.removeById(user.getId());
            if (!respone){
                failList.add(user.getUserId());
            }
        }

        if (failList.isEmpty()){

            return Result.success("删除成功！");

        }else {

            return Result.fail("添加失败！");

        }

    }

    @RequiresAuthentication
    @GetMapping("/userdetail")
    public Result userDetail(@RequestParam Integer id){

        User user = userService.getById(id);

        if (user != null){
            return Result.success(user);
        }else {
            return Result.fail("查询失败！");
        }

    }

    @RequiresAuthentication
    @PostMapping("/update")
    public Result userUpdate(@RequestBody User user){

        User user1 = userService.getById(user.getId());
        if(!user1.getPassword().equals(user.getPassword())){
            user.setPassword(SecureUtil.md5(user.getPassword()));
        }
        Boolean res = userService.updateById(user);

        if (res){

            return Result.success(res);

        }else {

            return Result.fail("修改失败！");

        }

    }

    @RequiresAuthentication
    @GetMapping("/searchbyname")
    public Result searchByName(@RequestParam String username,@RequestParam(defaultValue = "1L")Integer currentPage){

        Page page = new Page(currentPage,10);
        IPage res = userService.page(page,new QueryWrapper<User>().like("username",username));

        if (res.getTotal() > 0){
            return Result.success(res);
        }else {
            return Result.fail("无当前数据！");
        }

    }

    @RequiresAuthentication
    @GetMapping("/searchbyuserid")
    public Result searchByUserid(@RequestParam String userId,@RequestParam(defaultValue = "1L")Integer currentPage){

        Page page = new Page(currentPage,10);
        IPage res = userService.page(page,new QueryWrapper<User>().like("user_id",userId));

        if (res.getTotal() > 0){
            return Result.success(res);
        }else {
            return Result.fail("无当前数据！");
        }

    }
}
