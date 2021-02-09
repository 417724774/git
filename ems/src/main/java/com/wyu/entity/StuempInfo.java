package com.wyu.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

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

    @TableId(value = "se_id", type = IdType.AUTO)
    private Integer seId;

    private String seStuid;

    private String seName;

    private String seAddress;

    private String seLocation;

    private String sePostcode;

    private String sePhone;

    private String seEmail;

    private String seIntroduction;

    private String seExprience;

    private String seEducation;

    private String seTrain;

    private String seCerdificate;

    private String seIntention;

    private String seResume;


}
