package com.vincent.redis.component;

import com.vincent.redis.RedisConstant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;

@Service
public class RedisService {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    public String getString(String key, Supplier<String> dataSupplier) {
        var cache = redisTemplate.opsForValue().get(key);
        if (cache != null) {
            logger.info(RedisConstant.LOG_KEY_IS_FOUND, key);
            return cache;
        }
        logger.info(RedisConstant.LOG_KEY_IS_NOT_FOUND, key);

        var data = dataSupplier.get();
        redisTemplate.opsForValue().set(key, data);

        return data;
    }

    public List<String> getStringList(String key, Supplier<List<String>> dataSupplier) {
        var cache = redisTemplate.opsForList().range(key, 0, -1);
        if (!CollectionUtils.isEmpty(cache)) {
            logger.info(RedisConstant.LOG_KEY_IS_FOUND, key);
            return cache.stream()
                    .map(String.class::cast)
                    .collect(Collectors.toList());
        }
        logger.info(RedisConstant.LOG_KEY_IS_NOT_FOUND, key);

        var data = dataSupplier.get();
        var dataArray = data.toArray(new String[0]);
        redisTemplate.opsForList().rightPushAll(key, dataArray);

        return data;
    }

    public void addToStringList(String key, String data) {
        redisTemplate.opsForList().rightPush(key, data);
    }

    public void removeFromStringList(String key, String data) {
        redisTemplate.opsForList().remove(key, 0, data);
    }

    public void delete(String key) {
        redisTemplate.delete(key);
    }
}
