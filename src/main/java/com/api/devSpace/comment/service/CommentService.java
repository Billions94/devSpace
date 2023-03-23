package com.api.devSpace.comment.service;

import com.api.devSpace.comment.entity.Comment;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CommentService implements CommentServiceInterface {
    @Override
    public List<Comment> comments() {
        return null;
    }

    @Override
    public List<Comment> commentsByContent(String content) {
        return null;
    }

    @Override
    public Object comment(Long commentId) {
        return null;
    }

    @Override
    public Object createComment(Long postId, Comment commentInput) {
        return null;
    }

    @Override
    public Object updateComment(Long commentId, Comment commentInput) {
        return null;
    }

    @Override
    public String deleteComment(Long commentId) {
        return null;
    }
}
