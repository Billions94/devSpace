package com.api.devSpace.post.resolver;

import com.api.devSpace.post.entity.Post;
import com.api.devSpace.post.service.PostService;
import lombok.AllArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@AllArgsConstructor
public class PostResolver {
    private final PostService postService;

    @QueryMapping
    List<Post> posts() {
        return postService.posts();
    }

    @QueryMapping
    Object post(@Argument("postId") Long postId) {
        return postService.post(postId);
    }

    @MutationMapping
    Object createPost(
            @Argument("userId") Long userId,
            @Argument("postInput") Post postInput
    ) {
        return postService.createPost(userId, postInput);
    }

    @MutationMapping
    Object updatePost(
            @Argument("postId") Long postId,
            @Argument("postInput") Post postInput
    ) {
        return postService.updatePost(postId, postInput);
    }

    @MutationMapping
    String deletePost(@Argument("postId") Long postId) {
        return postService.deletePost(postId);
    }
}
