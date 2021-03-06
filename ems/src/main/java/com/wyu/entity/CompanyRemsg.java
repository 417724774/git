package com.wyu.entity;

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
public class CompanyRemsg implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 企业用户名
     */
    private String crCid;

    /**
     * 简历状态
     */
    private String crStatus;

    /**
     * 学生用户名
     */
    private String crStuid;


}
