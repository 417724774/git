package com.wyu.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
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
public class Jion implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 参与表id
     */
    @TableId(value = "j_id", type = IdType.AUTO)
    private Integer jId;

    /**
     * 招聘会id
     */
    private Integer jTjid;

    /**
     * 企业用户名
     */
    private String jCuserid;


}
