package com.mao.redisdemo.controller;

import com.mao.redisdemo.RedisService;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author Mingpeidev
 * @date 2020/9/18 14:23
 * @description
 */
@RestController
@RequestMapping("/redis")
public class RedisController {

    @Resource
    private RedisTemplate redisTemplate;

    @Resource
    private RedisService redisService;

    @RequestMapping("/setAndGet")
    public String setAndGetValue(String name, String value) {
        redisTemplate.opsForValue().set(name, value);

        return (String) redisTemplate.opsForValue().get(name);
    }

    @RequestMapping("setAndGetByService")
    public String setAndGetByUtil() {
        redisService.set("mao", "test");

        return (String) redisService.get("mao");
    }
}
