package com.wyu.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wyu.entity.StudentWork;
import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 *  服务类
 * </p>
 */
public interface StudentWorkService extends IService<StudentWork> {
    IPage<StudentWork> stuWorkList(@Param("page") Page page);
}
