package com.vincent.redis;

public class RedisConstant {
    private RedisConstant() {}

    public static final String LOG_KEY_IS_FOUND = "The key {} is found in Redis. Use cached data.";
    public static final String LOG_KEY_IS_NOT_FOUND = "The key {} can't be found in Redis. Load data from DB then cached it.";

    public static final String KEY_USER_NAME = "userName_";
    public static final String KEY_ITEM_LIKER_NAMES = "itemLikerNames_";
}
