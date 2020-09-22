package com.mao.redisdemo.controller;

import com.mao.redisdemo.RedisService;
import com.mao.redisdemo.dao.UserMapper;
import com.mao.redisdemo.entity.User;
import com.mao.redisdemo.service.UserCacheService;
import com.mao.redisdemo.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * @author Mingpeidev
 * @date 2019/9/20 17:33
 */
@Controller//同Component，表示控制层
@RequestMapping("/user")
public class UserController {

    private final static Logger logger = LoggerFactory.getLogger(UserController.class);

    private static final String key = "userCache_";

    @Resource
    private UserMapper userMapper;
    @Resource
    private UserService userService;

    @Resource
    private RedisService redisService;
    @Resource
    private UserCacheService userCacheService;

    /**
     * 纯mysql
     * 根据id获取用户信息
     *
     * @param id id
     * @return 如果需要返回JSON，XML或自定义mediaType内容到页面，则需要在对应的方法上加上@ResponseBody注解
     */
    @RequestMapping("/getUser")
    @ResponseBody
    public User getUserInfo(@RequestParam("id") Integer id) {
        return userService.getUser(id);
    }

    /**
     * redis做mysql缓存
     * set值和get值的时候序列化方式必须保持一致
     *
     * @param id
     * @return
     */
    @RequestMapping("/getUserCache")
    @ResponseBody
    public User getUseCache(String id) {

        //step1 先从redis里面取值
        User user = (User) redisService.get(key + id);

        //step2 如果拿不到则从DB取值
        if (user == null) {
            User userDB = userMapper.find(Integer.valueOf(id));
            System.out.println("fresh value from DB id:" + id);

            //step3 DB非空情况刷新redis值
            if (userDB != null) {
                redisService.set(key + id, userDB);
                return userDB;
            }
        }
        return user;
    }

    /**
     * 使用spring自带spring-boot-starter-cache整合缓存
     *
     * @param id
     * @return
     */
    @RequestMapping("/getByCache")
    @ResponseBody
    public User getByCache(String id) {
        User user = userCacheService.findById(id);
        return user;
    }
}
