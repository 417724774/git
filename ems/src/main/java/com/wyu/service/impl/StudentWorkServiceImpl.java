package com.wyu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wyu.entity.StudentWork;
import com.wyu.mapper.StudentWorkMapper;
import com.wyu.service.StudentWorkService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wyu.vo.stuWorkList;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 */
@Service
public class StudentWorkServiceImpl extends ServiceImpl<StudentWorkMapper, StudentWork> implements StudentWorkService {

    @Override
    public IPage<stuWorkList> stuWorkList(IPage<stuWorkList> page) {

        IPage<stuWorkList> list= this.baseMapper.stuWorkList(page);
        return list;
    }

    @Override
    public IPage<stuWorkList> stuWorkSearchBySname(IPage<stuWorkList> page, String sname) {

        IPage<stuWorkList> list = this.baseMapper.stuWorkSearchBySname(page,sname);
        return list;
    }

    @Override
    public IPage<stuWorkList> stuWorkSearchByswStuid(IPage<stuWorkList> page, String swStuid) {
        IPage<stuWorkList> list = this.baseMapper.stuWorkSearchByswStuid(page,swStuid);
        return list;
    }
}
