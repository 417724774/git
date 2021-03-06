package com.wyu.entity;

import com.baomidou.mybatisplus.annotation.IdType;
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
public class StuempInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "se_stuid",type = IdType.AUTO)
    private Integer seId;

    private String seStuid;

    private String seName;

    private String seLocation;

    private String seMajor;

    private String sePhone;

    private String seEmail;

    private String seIntroduction;

    private String seExprience;

    private String seEducation;

    private String seTrain;

    private String seCerdificate;

    private String seIntention;

    private String seResume;

    private String seSex;

    private String seSchool;

    private String sePicture;


}
