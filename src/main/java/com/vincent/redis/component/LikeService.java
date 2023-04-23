package com.vincent.redis.component;

import com.vincent.redis.model.LikePO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class LikeService {
    private final Set<LikePO> likeDB = new HashSet<>();

    @Autowired
    private UserService userService;

    @PostConstruct
    private void init() {
        likeDB.addAll(LikePO.getTestData());
    }

    public void createLike(String creatorId, String itemId) {
        likeDB.add(LikePO.of(creatorId, itemId));
    }

    public void deleteLike(String creatorId, String itemId) {
        likeDB.removeIf(like -> like.getCreatorId().equals(creatorId) && like.getItemId().equals(itemId));
    }

    public List<String> getLikerNamesByItem(String itemId) {
        return likeDB.stream()
                .filter(like -> like.getItemId().equals(itemId))
                .map(like -> userService.getUserName(like.getCreatorId()))
                .collect(Collectors.toList());
    }

    public void deleteLikesByItem(String itemId) {
        likeDB.removeIf(like -> like.getItemId().equals(itemId));
    }
}
