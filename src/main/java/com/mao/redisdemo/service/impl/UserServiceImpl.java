package com.mao.redisdemo.service.impl;

import com.mao.redisdemo.dao.UserMapper;
import com.mao.redisdemo.entity.User;
import com.mao.redisdemo.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author Mingpeidev
 * @date 2019/9/20 17:34
 */
@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;

    @Override
    public User getUser(Integer id) {

        User user = userMapper.selectByPrimaryKey(id);
        return user;
    }
}
