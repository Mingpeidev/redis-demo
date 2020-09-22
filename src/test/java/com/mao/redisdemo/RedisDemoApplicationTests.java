package com.mao.redisdemo;

import com.mao.redisdemo.dao.UserMapper;
import com.mao.redisdemo.entity.User;
import com.mao.redisdemo.service.UserCacheService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class RedisDemoApplicationTests {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserCacheService userCacheService;

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

    @Test
    public void redisCache() {

        String flag = "1";
        User user = new User();

        User user1 = new User();
        user1.setUsername("test");
        user1.setPassword("root");

        switch (flag) {
            case "1":
                user = userCacheService.findById("1");
                break;
            case "2":
                user = userCacheService.insertUser(user1);
                break;
            case "3":
                user1.setId(100);
                user = userCacheService.updateUser(user1);
                break;
            case "4":
                userCacheService.deleteById("1");
                break;
            default:
                break;
        }
        System.out.println(user.toString());
    }

}
