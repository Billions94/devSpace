package com.api.devSpace.post.repository;

import com.api.devSpace.post.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
    Post getPostById(Long postId);
}
