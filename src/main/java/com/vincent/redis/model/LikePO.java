package com.vincent.redis.model;

import java.util.Objects;
import java.util.Set;

public class LikePO {
    private String creatorId;
    private String itemId;

    public static LikePO of(String creatorId, String itemId) {
        var like = new LikePO();
        like.creatorId = creatorId;
        like.itemId = itemId;

        return like;
    }

    public static Set<LikePO> getTestData() {
        return Set.of(
                LikePO.of("U1", "P2"),
                LikePO.of("U2", "P1")
        );
    }

    public String getCreatorId() {
        return creatorId;
    }

    public String getItemId() {
        return itemId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        var other = (LikePO) o;
        return Objects.equals(creatorId, other.getCreatorId()) &&
                Objects.equals(itemId, other.getItemId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(creatorId, itemId);
    }
}
