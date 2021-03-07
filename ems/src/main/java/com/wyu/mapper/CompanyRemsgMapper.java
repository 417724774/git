package com.wyu.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wyu.entity.CompanyRemsg;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wyu.vo.ComJobList;
import com.wyu.vo.ResumeList;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 */
public interface CompanyRemsgMapper extends BaseMapper<CompanyRemsg> {

    IPage<ResumeList> resumeList(@Param("page") Page page, @Param("cuserid") String cuserid);

    IPage<ComJobList> comJobList(@Param("page") Page page);

}
