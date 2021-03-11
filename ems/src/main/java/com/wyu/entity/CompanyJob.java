package com.wyu.entity;

import java.time.LocalDate;
import java.io.Serializable;
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
public class CompanyJob implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "cj_id", type = IdType.AUTO)
    /**
     * id
     */
    private Integer cjId;

    /**
     * 企业用户名
     */
    private String cjCuserid;

    /**
     * 职位类型
     */
    private String cjType;

    /**
     * 发布时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    private Date cjPtime;

    /**
     * 有效时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    private Date cjDeadline;

    /**
     * 工作地点
     */
    private String cjAdress;

    /**
     * 薪酬
     */
    private Integer cjSalary;

    /**
     * 年龄要求
     */
    private Integer cjAge;

    /**
     * 职位描述
     */
    private String cjDescription;

    /**
     * 基本要求
     */
    private String cjDemand;

    /**
     * 职位性质
     */
    private String cjProperty;

    private String cjStatus;

}
