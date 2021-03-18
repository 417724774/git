package com.wyu.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wyu.entity.StudentWork;
import com.baomidou.mybatisplus.extension.service.IService;
import com.wyu.vo.stuWorkList;


/**
 * <p>
 *  服务类
 * </p>
 */
public interface StudentWorkService extends IService<StudentWork> {
    IPage<stuWorkList> stuWorkList(IPage<stuWorkList> page);

    IPage<stuWorkList> stuWorkSearchBySname(IPage<stuWorkList> page,String sname);

    IPage<stuWorkList> stuWorkSearchByswStuid( IPage<stuWorkList> page, String swStuid);
}
