package com.wyu.entity;

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
public class TeacherJobfair implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 招聘会表id
     */
    @TableId(value = "jf_id", type = IdType.AUTO)
    private Integer jfId;

    /**
     * 日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    private Date jfDate;

    /**
     * 标题
     */
    private String jfTitle;

    /**
     * 正文
     */
    private String jfBody;

    /**
     * 举行地点
     */
    private String jfAdress;

    /**
     * 联系人
     */
    private String jfMan;

    /**
     * 联系电话
     */
    private String jfPhone;

    private String jfTuserid;


}
