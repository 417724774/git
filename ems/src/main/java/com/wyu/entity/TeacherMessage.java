package com.wyu.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
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
public class TeacherMessage implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 消息表id
     */
    @TableId(value = "tm_id", type = IdType.AUTO)
    private Integer tmId;

    /**
     * 消息内容
     */
    private String tmContent;

    /**
     * 消息标题
     */
    private String tmTitle;

    /**
     * 消息类型
     */
    private String tmType;

    /**
     * 消息接收人代号（学生1企业2学生企业3）
     */
    private Integer tmAccept;

    /**
     * 消息发送人姓名
     */
    private String tmMan;

    /**
     * 消息发送人id
     */
    private String tmUserid;

    /**
     * 消息发送时间
     */
    @JsonFormat(locale = "zh",pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date tmPtime;


}
