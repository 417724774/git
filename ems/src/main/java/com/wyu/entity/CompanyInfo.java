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
public class CompanyInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    private String cUserid;

    private String cName;

    private String cUnit;

    private String cPassword;

    private LocalDate cRtime;

    private String cProperty;

    private String cSize;

    private String cPhone;

    private String cIntroduction;


}
