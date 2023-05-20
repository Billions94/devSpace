package com.api.devSpace.response;

import com.api.devSpace.Message.entity.Message;
import com.api.devSpace.comment.entity.Comment;
import com.api.devSpace.post.entity.Post;
import com.api.devSpace.space.Entity.Space;
import com.api.devSpace.user.entity.User;
import lombok.Data;

@Data
public class Success {
    private Long id;
    private User user;
    private Post post;
    private Comment comment;
    private Space space;
    private Message message;
    private String joinedSpace;

    public Success(Long id) {
        this.id = id;
    }

    public Success(User user) {
        this.user = user;
    }

    public Success(Post post) {
        this.post = post;
    }

    public Success(Comment comment) {
        this.comment = comment;
    }

    public Success(Space space) {
        this.space = space;
    }

    public Success(Message message) {
        this.message = message;
    }

    public Success(String joinedSpace) {
        this.joinedSpace = joinedSpace;
    }
}