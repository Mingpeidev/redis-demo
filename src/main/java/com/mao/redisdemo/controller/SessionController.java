package com.mao.redisdemo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Mingpeidev
 * @date 2020/9/25 17:10
 * @description redis实现分布式集群配置 实现session共享
 * <p>
 * 配置EnableRedisHttpSession后，Session调用就会自动去Redis存取
 */
@RestController
@RequestMapping("/session")
public class SessionController {
    /**
     * 设置session 50s失效
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/setSession", method = RequestMethod.GET)
    public Map<String, Object> setSession(HttpServletRequest request) {
        Map<String, Object> map = new HashMap<>();
        request.getSession().setAttribute("request Url", request.getRequestURL());
        map.put("request Url", request.getRequestURL());
        return map;
    }

    /**
     * 获取session 50s内未获取失效 清空session信息
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/getSession", method = RequestMethod.GET)
    public Object getSession(HttpServletRequest request) {
        Map<String, Object> map = new HashMap<>();
        map.put("sessionIdUrl", request.getSession().getAttribute("request Url"));
        map.put("sessionId", request.getSession().getId());
        return map;
    }
}
