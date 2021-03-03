package com.wyu.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Date;

/**
 *
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class StudentInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "s_userid")
    @NotBlank(message = "用户名不能为空")
    private String sUserid;

    @NotBlank(message = "姓名不能为空")
    private String sName;

    @NotBlank(message = "性别不能为空")
    private String sSex;

    @NotBlank(message = "密码不能为空")
    private String sPassword;

    @NotBlank(message = "学部不能为空")
    private String sCollege;

    @NotBlank(message = "专业不能为空")
    private String sMajor;

    @NotBlank(message = "班级不能为空")
    private String sClass;

    @NotBlank(message = "学制不能为空")
    private String sEs;

    @NotBlank(message = "毕业日期不能为空")
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    private Date sYog;


}
