package com.vincent.redis.component;

import com.vincent.redis.RedisConstant;
import com.vincent.redis.model.LikePO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Supplier;
import java.util.stream.Collectors;

@Service
public class LikeService {
    private final Set<LikePO> likeDB = new HashSet<>();

    @Autowired
    private UserService userService;

    @Autowired
    private RedisService redisService;

    @PostConstruct
    private void init() {
        likeDB.addAll(LikePO.getTestData());
    }

    public void createLike(String creatorId, String itemId) {
        likeDB.add(LikePO.of(creatorId, itemId));

        var cacheKey = RedisConstant.KEY_ITEM_LIKER_NAMES + itemId;
        var likerName = userService.getUserName(creatorId);
        redisService.addToStringList(cacheKey, likerName);
    }

    public void deleteLike(String creatorId, String itemId) {
        likeDB.removeIf(like -> like.getCreatorId().equals(creatorId) && like.getItemId().equals(itemId));

        var cacheKey = RedisConstant.KEY_ITEM_LIKER_NAMES + itemId;
        var likerName = userService.getUserName(creatorId);
        redisService.removeFromStringList(cacheKey, likerName);
    }

    public List<String> getLikerNamesByItem(String itemId) {
        var cacheKey = RedisConstant.KEY_ITEM_LIKER_NAMES + itemId;
        Supplier<List<String>> supplier = () -> likeDB.stream()
                .filter(like -> like.getItemId().equals(itemId))
                .map(like -> userService.getUserName(like.getCreatorId()))
                .collect(Collectors.toList());

        return redisService.getStringList(cacheKey, supplier);
    }

    public void deleteLikesByItem(String itemId) {
        likeDB.removeIf(like -> like.getItemId().equals(itemId));

        var cacheKey = RedisConstant.KEY_ITEM_LIKER_NAMES + itemId;
        redisService.delete(cacheKey);
    }
}
