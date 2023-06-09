package com.api.devSpace.response;

import com.api.devSpace.post.entity.Post;
import com.api.devSpace.user.entity.User;
import lombok.Data;

@Data
public class Success {
    private Long id;
    private User user;
    private Post post;

    public Success(Long id) {
        this.id = id;
    }

    public Success(User user) {
        this.user = user;
    }

    public Success(Post post) {
        this.post = post;
    }
}