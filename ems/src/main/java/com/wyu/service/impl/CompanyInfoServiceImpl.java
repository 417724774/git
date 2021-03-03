package com.wyu.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wyu.entity.CompanyInfo;
import com.wyu.mapper.CompanyInfoMapper;
import com.wyu.service.CompanyInfoService;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 */
@Service
public class CompanyInfoServiceImpl extends ServiceImpl<CompanyInfoMapper, CompanyInfo> implements CompanyInfoService {

}
