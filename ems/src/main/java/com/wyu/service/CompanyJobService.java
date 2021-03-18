package com.wyu.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wyu.entity.CompanyJob;
import com.baomidou.mybatisplus.extension.service.IService;
import com.wyu.vo.ComJobList;

/**
 * <p>
 *  服务类
 * </p>
 */
public interface CompanyJobService extends IService<CompanyJob> {

    IPage<ComJobList> searchJobBycunit(IPage<ComJobList> page, String cunit);

    IPage<ComJobList> searchJobBycuserid(IPage<ComJobList> page,String cuserid);

    IPage<ComJobList> searchJobAllBycjtype(IPage<ComJobList> page,String cjtype);


}
