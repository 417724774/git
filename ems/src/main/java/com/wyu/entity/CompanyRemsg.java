package com.wyu.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class CompanyRemsg implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "cr_id",type = IdType.AUTO)
    /**
     * id
     */
    private Integer crId;

    /**
     * 企业用户名
     */
    private String crCuserid;

    /**
     * 简历状态
     */
    private String crStatus;

    /**
     * 学生用户名
     */
    private String crStuid;

    /**
     * 投递时间
     */
    @JsonFormat(locale = "zh",pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date crTime;

    /**
     * 投递时间
     */
    private Integer crCjid;
}
