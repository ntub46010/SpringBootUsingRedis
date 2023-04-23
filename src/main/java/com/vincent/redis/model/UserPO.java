package com.vincent.redis.model;

import java.util.List;

public class UserPO {
    private String id;
    private String name;

    public static UserPO of(String id, String name) {
        var user = new UserPO();
        user.id = id;
        user.name = name;

        return user;
    }

    public static List<UserPO> getTestData() {
        return List.of(
                UserPO.of("U1", "Vincent"),
                UserPO.of("U2", "Ivy"),
                UserPO.of("U3", "Dora")
        );
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
