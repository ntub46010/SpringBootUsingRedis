package com.vincent.redis.component;

import com.vincent.redis.model.UserPO;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

@Service
public class UserService {
    private final Map<String, UserPO> userDB = new HashMap<>();

    @PostConstruct
    private void init() {
        UserPO.getTestData().forEach(x -> userDB.put(x.getId(), x));
    }

    public String getUserName(String userId) {
        return userDB.get(userId).getName();
    }
}
