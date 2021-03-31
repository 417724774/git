package com.wyu.controller;


import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wyu.common.lang.Result;
import com.wyu.entity.CompanyJob;
import com.wyu.entity.StudentInfo;
import com.wyu.entity.StuempInfo;
import com.wyu.entity.User;
import com.wyu.service.CompanyJobService;
import com.wyu.service.CompanyRemsgService;
import com.wyu.service.StuempInfoService;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.stream.FileImageInputStream;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

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

    @Autowired
    CompanyRemsgService companyRemsgService;

    @Autowired
    CompanyJobService companyJobService;

    @Value("${wyu.paiUrl}")
    private String paiUrl;

    @RequiresAuthentication
    @PostMapping("/studentresume")
    public Result studentresume(@RequestBody StuempInfo stures) {
        StuempInfo res = stuempInfoService.getOne(new QueryWrapper<StuempInfo>().eq("se_stuid",stures.getSeStuid()));
        if(res !=null){
            return Result.success(res);
        }else {
            return Result.fail("查询失败！");
        }
    }

    @RequiresAuthentication
    @PostMapping("/stuemupdate")
    public Result stuemupdate(@RequestBody StuempInfo stures) throws IOException {
        String pictureCachePath = "D:/img/picture/"+stures.getSeStuid()+"_picture_cache";
        StringBuffer pictureUrl = new StringBuffer(paiUrl +"picture/"+stures.getSeStuid()+"_picture");
        StringBuffer picturePath = new StringBuffer("D:/img/picture/"+stures.getSeStuid()+"_picture");
        File jpg = new File(pictureCachePath+".jpg");
        File png = new File(pictureCachePath+".png");
        FileChannel input = null;
        FileChannel output = null;
        if(jpg.exists()){
            picturePath = picturePath.append(".jpg");
            stures.setSePicture(pictureUrl+".jpg");
            File picture = new File(picturePath.toString());
            picture.createNewFile();
            input = new FileInputStream(jpg).getChannel();
            output = new FileOutputStream(picture).getChannel();
            output.transferFrom(input,0,input.size());
            input.close();
            output.close();
        }
        if (png.exists()){
            picturePath = picturePath.append(".png");
            stures.setSePicture(pictureUrl+".png");
            File picture = new File(picturePath.toString());
            picture.createNewFile();
            input = new FileInputStream(png).getChannel();
            output = new FileOutputStream(picture).getChannel();
            output.transferFrom(input,0,input.size());
            input.close();
            output.close();
        }
        jpg.delete();
        png.delete();
        Boolean res = stuempInfoService.saveOrUpdate(stures,new UpdateWrapper<StuempInfo>().eq("se_stuid",stures.getSeStuid()));
        if(res){
            return Result.success(res);
        }else {
            return Result.fail(res.toString());
        }
    }

    @RequiresAuthentication
    @GetMapping("/comjoblist")
    public Result comJobList(@RequestParam(defaultValue = "1") Integer currentPage){

        Page page = new Page(currentPage,5);

        IPage pageData = companyRemsgService.comJobList(page);

        if(pageData.getTotal() >= 1){
            return Result.success(pageData);
        }else {
            return Result.fail("数据不存在！");
        }
    }

    @RequiresAuthentication
    @GetMapping("/companydetailjoblist")
    public Result companyDetailJobList(@RequestParam String cuserid){
        List<CompanyJob> res = companyJobService.list(new QueryWrapper<CompanyJob>().eq("cj_cuserid",cuserid).eq("cj_status","已通过"));

        if(res != null){
            String strList ="";

            for (CompanyJob cj: res) {
                String str = "招聘岗位："+cj.getCjType()+" \n"+
                        "有效时间："+new SimpleDateFormat("yyyy-MM-dd").format(cj.getCjDeadline()) +"\n"+
                        "工作地点："+cj.getCjAdress()+"\n"+
                        "薪资："+cj.getCjSalary()+"\n"+
                        "年龄要求："+cj.getCjAge()+" 岁以上"+"\n"+
                        "职位性质："+cj.getCjProperty()+"\n"+
                        "基本要求："+cj.getCjDemand()+"\n"+
                        "职位描述："+cj.getCjDescription()+"\n\n";

                strList += str;
            }
            return Result.success(strList);
        }else {
            return Result.fail(res.toString());
        }
    }

    @RequiresAuthentication
    @PostMapping(value = "studentPicture", produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> studentPictureAndText(HttpServletRequest request,
                                                      @RequestParam(value = "userid", required = false) String userid,
                                                      @RequestParam(value = "file", required = false) MultipartFile file) {
        if (file.isEmpty()) {
            HashMap<String, Object> resultMap = new HashMap<>();
            resultMap.put("msg", "请上传图片");
            return resultMap;
        } else {
            String fileName = file.getOriginalFilename();  // 文件名
            String suffixName = fileName.substring(fileName.lastIndexOf("."));
            String filePath = "D:/img/picture/";//这个path就是你要存在服务器上的
            fileName = userid + "_picture_cache" + suffixName; // 新文件名
            //删除上一次头像
            File del1 = new File(filePath+userid + "_picture_cache" +".jpg");
            File del2 = new File(filePath+userid + "_picture_cache" +".png");
            if(del1.exists())
            FileSystemUtils.deleteRecursively(del1);
            if(del2.exists())
            FileSystemUtils.deleteRecursively(del2);

            File dest = new File(filePath + fileName);
            ////////
            if (!dest.getParentFile().exists()) {
                dest.getParentFile().mkdirs();
            }
            try {
                file.transferTo(dest);
            } catch (IOException e) {
                e.printStackTrace();
            }
            String fileUrl = paiUrl +"picture/"+  fileName;

//            User user = new User().setAvatar(fileUrl);
//            Boolean res = userService.update(user,new QueryWrapper<User>().eq("user_id",userid));
            HashMap<String, Object> resultMap = new HashMap<>();
            if(paiUrl != "") {
                resultMap.put("msg", "上传图片成功");
                resultMap.put("code", 200);
                resultMap.put("url", fileUrl);
                dest.deleteOnExit();
                return resultMap;//这里就是上传图片返回的信息，成功失败异常等，前端根据字段接收就是了
            }else {

                dest.delete();
                resultMap.put("msg", "上传图片失败");
                return resultMap;//这里就是上传图片返回的信息，成功失败异常等，前端根据字段接收就是了
            }
        }
    }
}
