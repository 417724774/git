package com.wyu.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class ComJobList implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer cjId;

    private String cUnit;

    private String cUserid;

    private String cjType;

    private String cjSalary;

    private String cjDemand;

    private String cjProperty;

    private String cjAdress;

    private String cjDescription;


}
