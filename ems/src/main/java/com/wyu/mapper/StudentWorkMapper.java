package com.wyu.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wyu.entity.StudentWork;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wyu.vo.ComJobList;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 *  Mapper 接口
 * </p>
 */
public interface StudentWorkMapper extends BaseMapper<StudentWork> {
    IPage<StudentWork> stuWorkList(@Param("page") Page page);
}
