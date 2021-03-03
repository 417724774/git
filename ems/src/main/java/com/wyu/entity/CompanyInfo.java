package com.wyu.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Date;

/**

 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class CompanyInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "c_userid")
    @NotBlank(message = "用户名不能为空")
    private String cUserid;

    @NotBlank(message = "姓名不能为空")
    private String cName;

    @NotBlank(message = "公司名称不能为空")
    private String cUnit;

    @NotBlank(message = "密码不能为空")
    private String cPassword;

    @JsonFormat(pattern = "yyyy-mm-dd")
//    @NotBlank(message = "注册时间不能为空")
    private Date cRtime;

    @NotBlank(message = "公司性质不能为空")
    private String cProperty;

    @NotBlank(message = "公司规模不能为空")
    private String cSize;

    @NotBlank(message = "联系电话不能为空")
    private String cPhone;

    @NotBlank(message = "公司简介不能为空")
    private String cIntroduction;

    @NotBlank(message = "邮箱不能为空")
    @Email(message = "邮箱格式错误")
    private String cEmail;

    private String cStatus;
}
