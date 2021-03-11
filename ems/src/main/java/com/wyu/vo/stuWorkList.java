package com.wyu.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class stuWorkList implements Serializable {

    private static final long serialVersionUID = 1L;

    private String swStuid;

    private String swName;

    private String swAddress;

    private String swAgreement;

    private Integer swSalary;

    private String swPorperty;

    private Integer swDeadline;

    private String sName;


}
