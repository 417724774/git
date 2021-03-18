package com.wyu.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wyu.entity.CompanyJob;
import com.wyu.mapper.CompanyJobMapper;
import com.wyu.service.CompanyJobService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wyu.vo.ComJobList;
import com.wyu.vo.stuWorkList;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 */
@Service
public class CompanyJobServiceImpl extends ServiceImpl<CompanyJobMapper, CompanyJob> implements CompanyJobService {

    @Override
    public IPage<ComJobList> searchJobBycunit(IPage<ComJobList> page, String cunit) {
        IPage<ComJobList> list = this.baseMapper.searchJobBycunit(page,cunit);
        return list;
    }

    @Override
    public IPage<ComJobList> searchJobBycuserid(IPage<ComJobList> page, String cuserid) {
        IPage<ComJobList> list = this.baseMapper.searchJobBycuserid(page,cuserid);
        return list;
    }

    @Override
    public IPage<ComJobList> searchJobAllBycjtype(IPage<ComJobList> page, String cjtype) {
        IPage<ComJobList> list = this.baseMapper.searchJobAllBycjtype(page,cjtype);
        return list;
    }
}
