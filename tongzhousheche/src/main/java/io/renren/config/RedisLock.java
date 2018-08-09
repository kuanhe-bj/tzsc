package io.renren.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import com.tceasy.common.utils.string.StringUtil;

import lombok.extern.slf4j.Slf4j;

/**
 * redis分布式锁
 * Created by shuaijun on 2015/10/22.
 */
@Slf4j
@Component
public class RedisLock {


    @Autowired
    private StringRedisTemplate redisTemplate;

    private com.tceasy.common.utils.lock.RedisLock redisLock = new com.tceasy.common.utils.lock.RedisLock();

    public boolean lock(String key, String value, long timeOut, Long tryTime) {
        return lock(key, value, timeOut, tryTime, 20);
    }

    /**
     * 加锁
     *
     * @param key
     * @param value
     * @param timeOut         超时时间，单位为毫秒
     * @param tryTime         自动重试时间，如果为null，直到获取到锁为止
     * @param intervalTryTime 尝试获取锁，间隔时间,单位毫秒
     * @return
     * @throws InterruptedException
     */
    public boolean lock(String key, String value, long timeOut, Long tryTime, long intervalTryTime) {
        long startTime = System.currentTimeMillis();
        boolean result = false;
        try {
            while (true) {
                result = redisLock.lock(key, String.valueOf(System.currentTimeMillis()), timeOut, redisTemplate);
                if (result || (null != tryTime && (System.currentTimeMillis() - startTime) > tryTime.longValue())) {
                    break;
                }
                Thread.sleep(intervalTryTime);
            }
        } catch (InterruptedException e) {
            log.error("加锁失败,错误信息:{}", e.getMessage(), e);
        }
        log.info("lock end. result[{}]", result);
        return result;
    }

    /**
     * 解锁
     *
     * @param key
     */
    public void unLock(String key) {
        if (StringUtil.isBlank(key)) {
            return;
        }
        redisLock.unLock(key, redisTemplate);
    }

}
