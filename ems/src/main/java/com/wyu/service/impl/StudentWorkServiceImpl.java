package com.wyu.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wyu.entity.StudentWork;
import com.wyu.mapper.StudentWorkMapper;
import com.wyu.service.StudentWorkService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wyu.vo.ComJobList;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 */
@Service
public class StudentWorkServiceImpl extends ServiceImpl<StudentWorkMapper, StudentWork> implements StudentWorkService {

    @Override
    public IPage<StudentWork> stuWorkList(Page page) {

        IPage<StudentWork> list= this.baseMapper.stuWorkList(page);
        return list;
    }
}
