package com.wyu.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wyu.entity.CompanyJob;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wyu.vo.ComJobList;

/**
 * <p>
 *  Mapper 接口
 * </p>
 */
public interface CompanyJobMapper extends BaseMapper<CompanyJob> {

    IPage<ComJobList> searchJobBycunit(IPage<ComJobList> page,String cunit);

    IPage<ComJobList> searchJobBycuserid(IPage<ComJobList> page,String cuserid);

    IPage<ComJobList> searchJobAllBycjtype(IPage<ComJobList> page,String cjtype);

}
