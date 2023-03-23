package com.api.devSpace.comment.service;

import com.api.devSpace.comment.entity.Comment;

import java.util.List;

public interface CommentServiceInterface {
    List<Comment> comments();
    List<Comment> commentsByContent(String content);
    Object comment(Long commentId);
    Object createComment(Long postId, Comment commentInput);
    Object updateComment(Long commentId, Comment commentInput);
    String deleteComment(Long commentId);

}
