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
 *
 * @author 关注公众号：MarkerHub
 * @since 2021-02-08
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class StudentInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    private String sUserid;

    private String sName;

    private String sSex;

    private String sPassword;

    private String sCollege;

    private String sMajor;

    private String sClass;

    private String sEs;

    private LocalDate sYog;


}
