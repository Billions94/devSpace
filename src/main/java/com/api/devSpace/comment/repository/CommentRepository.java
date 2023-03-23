package com.api.devSpace.comment.repository;

import com.api.devSpace.comment.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> getCommentsByContent(String content);
}
