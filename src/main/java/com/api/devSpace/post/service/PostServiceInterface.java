package com.api.devSpace.post.service;

import com.api.devSpace.post.entity.Post;

import java.util.List;

public interface PostServiceInterface {
    List<Post> posts();

    Object post(Long postId);

    Object createPost(Long postId, Post postInput);

    Object updatePost(Long postId, Post postInput);

    String deletePost(Long postId);
}
