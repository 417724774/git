package com.wyu.service.impl;

import com.wyu.entity.StudentMessage;
import com.wyu.mapper.StudentMessageMapper;
import com.wyu.service.StudentMessageService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 */
@Service
public class StudentMessageServiceImpl extends ServiceImpl<StudentMessageMapper, StudentMessage> implements StudentMessageService {

}
