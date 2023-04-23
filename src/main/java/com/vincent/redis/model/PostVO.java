package com.vincent.redis.model;

import java.util.List;

public class PostVO extends PostPO {
    private String creatorName;
    private List<String> likerNames;

    public static PostVO of(PostPO postPO, String creatorName, List<String> likerNames) {
        var postVO = new PostVO();
        postVO.setId(postPO.getId());
        postVO.setCreatorId(postPO.getCreatorId());
        postVO.setContent(postPO.getContent());
        postVO.creatorName = creatorName;
        postVO.likerNames = likerNames;

        return postVO;
    }

    public String getCreatorName() {
        return creatorName;
    }

    public List<String> getLikerNames() {
        return likerNames;
    }
}
