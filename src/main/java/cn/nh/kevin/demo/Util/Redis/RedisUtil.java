package cn.nh.kevin.demo.Util.Redis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * 标题: Redis使用服务类
 * 描述:
 * 版权: Kevin
 * 作者: xck
 * 时间: 2019-09-04 14:32
 */
@Component
public class RedisUtil {
    private static final Logger log = LoggerFactory.getLogger(RedisUtil.class);

    @Autowired
    private RedisTemplate<String,Object> redisTemplate;
    /**
     * @Description 设置缓存失效时间
     * @Author xck
     * @Date 2019/9/4 19:36
     * @param key
     * @param time
     * @return boolean
     **/
    public boolean expire(String key,long time){
        if (time>0){
            redisTemplate.expire(key,time, TimeUnit.SECONDS);
        }
        return true;
    }
    /**
     * @Description 普通缓存存入
     * @Author xck
     * @Date 2019/9/4 19:39
     * @param key
     * @param value
     * @return boolean
     **/
    public boolean set(String key,Object value){
        redisTemplate.opsForValue().set(key,value);
        return true;
    }
    /**
     * @Description 普通缓存获取
     * @Author xck
     * @Date 2019/9/4 19:41
     * @param key
     * @return java.lang.Object
     **/
    public Object get(String key){
        return key==null?null:redisTemplate.opsForValue().get(key);
    }

}
