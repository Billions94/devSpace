package com.api.devSpace.post.repository;

import com.api.devSpace.post.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> getPostsByContent(String content);
}
