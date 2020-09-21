package com.mao.redisdemo;

import com.mao.redisdemo.dao.UserMapper;
import com.mao.redisdemo.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class RedisDemoApplicationTests {

    @Autowired
    private UserMapper userMapper;

    @Test
    void contextLoads() {
    }

    @Test
    public void testDelete() {
        User user = userMapper.selectByPrimaryKey(12);
        System.out.println(user.getPassword() + user.getUsername());
    }

    @Test
    public void testFind() {
        User user = userMapper.find(1);
        System.out.println(user.toString());
    }

}
