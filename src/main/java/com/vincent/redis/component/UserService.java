package com.vincent.redis.component;

import com.vincent.redis.RedisConstant;
import com.vincent.redis.model.UserPO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

@Service
public class UserService {
    private final Map<String, UserPO> userDB = new HashMap<>();

    @Autowired
    private RedisService redisService;

    @PostConstruct
    private void init() {
        UserPO.getTestData().forEach(x -> userDB.put(x.getId(), x));
    }

    public String getUserName(String userId) {
        var cacheKey = RedisConstant.KEY_USER_NAME + userId;
        Supplier<String> supplier = () -> userDB.get(userId).getName();

        return redisService.getString(cacheKey, supplier);
    }
}
