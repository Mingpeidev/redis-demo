package com.mao.redisdemo.controller;

import com.mao.redisdemo.entity.User;
import com.mao.redisdemo.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Mingpeidev
 * @date 2019/9/20 17:33
 */
@Controller
@RequestMapping("/user")
public class UserController {

    private static Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    /**
     * 根据id获取用户信息
     *
     * @param id
     * @return 如果需要返回JSON，XML或自定义mediaType内容到页面，则需要在对应的方法上加上@ResponseBody注解
     */
    @RequestMapping("/getuserbyid")
    @ResponseBody
    public Map<String, Object> getUserInfo(@RequestParam("id") Integer id) {
        Map<String, Object> map = new HashMap<String, Object>();
        User user = userService.getUser(id);

        if (user != null) {
            map.put("username", user.getUsername());
            map.put("password", user.getPassword());
            System.out.println("！！！获取信息成功！！！");
        } else {
            logger.error("不存在用户！");
        }

        logger.warn("哈哈");

        return map;
    }
}
