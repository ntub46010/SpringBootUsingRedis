package com.vincent.redis.model;

import java.util.List;

public class PostPO {
    private String id;
    private String creatorId;
    private String content;

    public static PostPO of(String id, String creatorId, String content) {
        var post = new PostPO();
        post.id = id;
        post.creatorId = creatorId;
        post.content = content;

        return post;
    }

    public static List<PostPO> getTestData() {
        return List.of(
                PostPO.of("P1", "U1", "aaa"),
                PostPO.of("P2", "U2", "bbb"),
                PostPO.of("P3", "U3", "ccc")
        );
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(String creatorId) {
        this.creatorId = creatorId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
