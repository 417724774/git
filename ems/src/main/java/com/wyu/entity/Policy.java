package com.wyu.entity;

import cn.hutool.core.date.DateTime;
import com.baomidou.mybatisplus.annotation.IdType;
import java.time.LocalDate;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import java.util.Date;

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
public class Policy implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId(value = "p_id", type = IdType.AUTO)
    private Integer pId;

    /**
     * 标题
     */
    private String pTitle;

    /**
     * 内容
     */
    private String pContent;

    /**
     * 发布日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    private Date pDate;

    private String pMan;

    @JsonFormat(locale = "zh",pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date pUpdate;

    private String pSource;
}
