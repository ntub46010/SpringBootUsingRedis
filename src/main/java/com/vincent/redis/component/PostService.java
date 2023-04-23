package com.vincent.redis.component;

import com.vincent.redis.model.PostPO;
import com.vincent.redis.model.PostVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

@Service
public class PostService {
    private final Map<String, PostPO> postDB = new HashMap<>();

    @Autowired
    private UserService userService;

    @Autowired
    private LikeService likeService;

    @PostConstruct
    private void init() {
        PostPO.getTestData().forEach(x -> postDB.put(x.getId(), x));
    }

    public PostVO getPost(String postId) {
        var postPO = postDB.get(postId);
        var creatorName = userService.getUserName(postPO.getCreatorId());
        var likerNames = likeService.getLikerNamesByItem(postId);

        return PostVO.of(postPO, creatorName, likerNames);
    }

    public void deletePost(String postId) {
        postDB.remove(postId);
        likeService.deleteLikesByItem(postId);
    }
}
