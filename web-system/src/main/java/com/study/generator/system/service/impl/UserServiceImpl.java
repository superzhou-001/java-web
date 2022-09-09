package com.study.generator.system.service.impl;

import com.study.generator.system.entity.User;
import com.study.generator.system.mapper.UserMapper;
import com.study.generator.system.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zhouming
 * @since 2022-09-09
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

}
