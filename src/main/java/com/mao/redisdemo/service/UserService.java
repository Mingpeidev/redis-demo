package com.mao.redisdemo.service;

import com.mao.redisdemo.entity.User;

/**
 * @author Mingpeidev
 * @date 2019/9/20 17:33
 */
public interface UserService {
    /**
     * 获取用户信息
     *
     * @param id
     * @return
     */
    User getUser(Integer id);
}
