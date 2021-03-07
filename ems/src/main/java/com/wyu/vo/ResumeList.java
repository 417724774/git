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
public class ResumeList implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer crId;

    private String sName;

    private String sCollege;

    private String crStuid;

    private String crStatus;

    /**
     * 投递时间
     */
    @JsonFormat(locale = "zh",pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date crTime;

}
