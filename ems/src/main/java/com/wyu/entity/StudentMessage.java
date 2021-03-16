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
 *
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class StudentMessage implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 学生消息表id
     */
    @TableId(value = "sm_id", type = IdType.AUTO)
    private Integer smId;

    /**
     * 消息内容
     */
    private String smContent;

    /**
     * 接受人
     */
    private String smAccept;

    /**
     * 发送人
     */
    private String smMan;

    /**
     * 消息状态
     */
    private String smStatus;

    /**
     * 发送人id
     */
    private String smManid;

    /**
     * 发送时间
     */
    @JsonFormat(locale = "zh",pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date smPtime;

    /**
     * 消息标题
     */
    private String smTitle;


}
