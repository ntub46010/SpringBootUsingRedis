package com.vincent.redis.component;

import com.vincent.redis.model.LikePO;
import com.vincent.redis.model.PostVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
public class FeatureController {

    @Autowired
    private PostService postService;

    @Autowired
    private LikeService likeService;

    @GetMapping("/posts/{id}")
    public ResponseEntity<PostVO> loadPost(@PathVariable("id") String postId) {
        var post = postService.getPost(postId);
        return ResponseEntity.ok(post);
    }

    @DeleteMapping("/posts/{id}")
    public ResponseEntity<Void> deletePost(@PathVariable("id") String postId) {
        postService.deletePost(postId);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/likes")
    public ResponseEntity<Void> createLike(@RequestBody LikePO like) {
        likeService.createLike(like.getCreatorId(), like.getItemId());
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/likes")
    public ResponseEntity<Void> deleteLike(@RequestBody LikePO like) {
        likeService.deleteLike(like.getCreatorId(), like.getItemId());
        return ResponseEntity.noContent().build();
    }
}
