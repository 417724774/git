package com.wyu.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wyu.entity.StudentWork;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wyu.vo.stuWorkList;

/**
 * <p>
 *  Mapper 接口
 * </p>
 */
public interface StudentWorkMapper extends BaseMapper<StudentWork> {
    IPage<stuWorkList> stuWorkList( IPage<stuWorkList> page);

    IPage<stuWorkList> stuWorkSearchBySname( IPage<stuWorkList> page, String sname);

    IPage<stuWorkList> stuWorkSearchByswStuid( IPage<stuWorkList> page, String swStuid);
}
