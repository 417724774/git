package com.wyu.entity;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
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
public class TeacherNotification implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 表id
     */
    @TableId(value = "tn_id", type = IdType.AUTO)
    private Integer tnId;

    /**
     * 消息内容
     */
    private String tnContent;

    /**
     * 接受人
     */
    private String tnAccept;

    /**
     * 发送人
     */
    private String tnMan;

    /**
     * 消息状态
     */
    private String tnStatus;

    private Integer tnJfid;

    private String tnTitle;

    private String tnManid;

    @JsonFormat(locale = "zh",pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date tnPtime;


}
