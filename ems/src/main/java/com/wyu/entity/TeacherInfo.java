package com.wyu.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author 关注公众号：MarkerHub
 * @since 2021-02-08
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class TeacherInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "t_userid")
    private String tUserid;

    private String tName;

    private String tSex;

    private String tPassword;

    private String tCollege;

    private String tPhone;


}
