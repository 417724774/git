package com.wyu.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableId;
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
public class StudentWork implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 学生用户名
     */
    @TableId(value = "sw_stuid")
    private Integer swStuid;

    /**
     * 公司名称
     */
    private String swName;

    /**
     * 工作地点
     */
    private String swAddress;

    /**
     * 合同性质
     */
    private String swAgreement;

    /**
     * 薪资
     */
    private Integer swSalary;

    /**
     * 工作性质
     */
    private String swPorperty;

    private Integer swDeadline;


}
