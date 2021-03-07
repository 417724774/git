package com.wyu.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wyu.entity.CompanyRemsg;
import com.wyu.mapper.CompanyRemsgMapper;
import com.wyu.service.CompanyRemsgService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wyu.vo.ComJobList;
import com.wyu.vo.ResumeList;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 */
@Service
public class CompanyRemsgServiceImpl extends ServiceImpl<CompanyRemsgMapper, CompanyRemsg> implements CompanyRemsgService {

    @Override
    public IPage<ResumeList> resumeList(Page page,String cuserid) {
        IPage<ResumeList> list= this.baseMapper.resumeList(page, cuserid);
        return list;

    }

    @Override
    public IPage<ComJobList> comJobList(Page page) {
        IPage<ComJobList> list= this.baseMapper.comJobList(page);
        return list;

    }

}
