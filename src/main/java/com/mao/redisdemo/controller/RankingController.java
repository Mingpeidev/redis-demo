package com.mao.redisdemo.controller;

import com.mao.redisdemo.utils.RedisService;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author Mingpeidev
 * @date 2020/9/27 9:24
 * @description redis实现排名
 */
@RestController
@RequestMapping("/ranking")
public class RankingController {
    private final static String SCORE_RANK = "user_score";
    @Resource
    private RedisService redisService;

    /**
     * 添加排名数据
     *
     * @param uid
     * @param score
     * @return
     */
    @ResponseBody
    @RequestMapping("/addScore")
    public String addRank(String uid, Integer score) {
        redisService.zAdd(SCORE_RANK, uid, score);
        return "success";
    }

    /**
     * 添加分数
     *
     * @param uid
     * @param score
     * @return
     */
    @ResponseBody
    @RequestMapping("/incrementScore")
    public String incrementScore(String uid, Integer score) {
        redisService.incrementScore(SCORE_RANK, uid, score);
        return "success";
    }

    /**
     * 当前id排行
     *
     * @param uid
     * @return
     */
    @ResponseBody
    @RequestMapping("/rank")
    public Map<String, Long> rank(String uid) {
        Map<String, Long> map = new HashMap<>();
        //从0开始，所以加1
        map.put(uid, redisService.zRank(SCORE_RANK, uid) + 1);
        return map;
    }

    /**
     * 当前id分数
     *
     * @param uid
     * @return
     */
    @ResponseBody
    @RequestMapping("/score")
    public Long rankNum(String uid) {
        return redisService.zSetScore(SCORE_RANK, uid).longValue();
    }

    /**
     * 增序排列
     *
     * @param start
     * @param end
     * @return
     */
    @ResponseBody
    @RequestMapping("/scoreByRange")
    public Set<ZSetOperations.TypedTuple<Object>> scoreByRange(Integer start, Integer end) {
        return redisService.zRankWithScore(SCORE_RANK, start, end);
    }


}
