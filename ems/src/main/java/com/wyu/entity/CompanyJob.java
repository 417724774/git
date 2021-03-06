package com.wyu.entity;

import java.time.LocalDate;
import java.io.Serializable;
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
    private LocalDate cjPtime;

    /**
     * 有效时间
     */
    private LocalDate cjDeadline;

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


}
