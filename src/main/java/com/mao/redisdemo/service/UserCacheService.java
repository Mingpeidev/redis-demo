package com.mao.redisdemo.service;

import com.mao.redisdemo.dao.UserMapper;
import com.mao.redisdemo.entity.User;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import reactor.util.annotation.Nullable;

import javax.annotation.Resource;

/**
 * @author Mingpeidev
 * @date 2020/9/22 14:51
 * @description spring-boot-starter-cache整合redis 服务
 */
@Service
//本类内方法指定使用缓存时，默认的名称就是userInfoCache,redis里key是：userInfoCache::key
@CacheConfig(cacheNames = "userInfoCache")
//事务
@Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = Exception.class)
public class UserCacheService {

    @Resource
    private UserMapper userMapper;

    /**
     * 插入数据后将结果放入缓存
     *
     * @param u
     * @return
     */
    @CachePut(key = "#p0.id")//#p0表示第一个参数
    //必须要有返回值，否则没数据放到缓存中
    public User insertUser(User u) {
        this.userMapper.insert(u);
        //u对象中可能只有只几个有效字段，其他字段值靠数据库生成，比如id
        return this.userMapper.find(u.getId());
    }


    /**
     * 更新数据后将结果放入redis
     *
     * @param u
     * @return
     */
    @CachePut(key = "#p0.id")//CachePut始终执行该方法并将其结果放入缓存中
    public User updateUser(User u) {
        this.userMapper.updateByPrimaryKey(u);
        //可能只是更新某几个字段而已，所以查次数据库把数据全部拿出来全部
        return this.userMapper.find(u.getId());
    }

    /**
     * 会先查询缓存，如果缓存中存在，则不执行方法，直接从缓存取值
     *
     * @param id
     * @return
     */
    @Nullable//可以标注在方法、字段、参数之上，表示对应的值可以为空
    @Cacheable(key = "#p0")
    public User findById(String id) {
        System.err.println("根据id=" + id + "获取用户对象，从数据库中获取");
        Assert.notNull(id, "id不能为空");
        return this.userMapper.find(Integer.valueOf(id));
    }

    /**
     * 删除数据同时删除对应key的缓存
     *
     * @param id
     */
    @CacheEvict(key = "#p0")
    public void deleteById(String id) {
        this.userMapper.deleteByPrimaryKey(Integer.valueOf(id));
    }

    //清空缓存名称为userInfoCache（看类名上的注解)下的所有缓存
    //如果数据失败了，缓存时不会清除的
    /*@CacheEvict(allEntries = true)
    public void deleteAll() {
        this.userMapper.deleteAll();
    }
*/

    /**
     * 自定义key生成规则和超时时间
     * value指定缓存名称，不指定用默认
     *
     * @param id
     * @return
     */
    @Nullable
    @Cacheable(value = "UserInfoList", keyGenerator = "simpleKeyGenerator")
    public User findByIdTtl(String id) {
        System.err.println("根据id=" + id + "获取用户对象，从数据库中获取");
        Assert.notNull(id, "id不能为空");
        return this.userMapper.find(Integer.valueOf(id));
    }

}
