package com.wyu.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wyu.entity.CompanyRemsg;
import com.baomidou.mybatisplus.extension.service.IService;
import com.wyu.vo.ComJobList;
import com.wyu.vo.ResumeList;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 *  服务类
 * </p>
 */
public interface CompanyRemsgService extends IService<CompanyRemsg> {

    IPage<ResumeList> resumeList(Page page,String cuserid);

    IPage<ComJobList> comJobList(Page page);

    IPage<ComJobList> teaComJobList(Page page);

}
