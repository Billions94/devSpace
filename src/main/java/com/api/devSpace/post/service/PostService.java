package com.api.devSpace.post.service;

import com.api.devSpace.exception.PSQLException;
import com.api.devSpace.post.entity.Post;
import com.api.devSpace.post.repository.PostRepository;
import com.api.devSpace.response.Failed;
import com.api.devSpace.response.Success;
import com.api.devSpace.user.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Service
@AllArgsConstructor
public class PostService implements PostServiceInterface {
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    public List<Post> posts() {
        return postRepository.findAll();
    }

    public List<Post> postsByContent(String content) {
        return postRepository.getPostsByContent(content);
    }

    public Object post(Long postId) {
        try {
            Post post = postRepository
                    .findById(postId)
                    .orElseThrow(() -> new PSQLException("Post with id: " + postId + " does not exist"));

            return new Success(post);
        } catch (PSQLException e) {
            return new Failed(e.getMessage());
        }
    }

    public Object createPost(Long userId, Post postInput) {
        try {
            if (!userRepository.existsById(userId))
                throw new PSQLException("User with id: " + userId + " does not exist");

            postInput.setUser(userRepository.getUserById(userId));
            postInput.setCreatedAt(LocalDateTime.now());

            return new Success(postRepository.save(postInput).getId());
        } catch (PSQLException e) {
            return new Failed(e.getMessage());
        }
    }

    public Object updatePost(Long postId, Post postInput) {
        try {
            Post post = postRepository
                    .findById(postId)
                    .orElseThrow(() -> new PSQLException("Post with id: " + postId + " does not exist"));

            if (
                    postInput.getContent() != null
                            && postInput.getContent().length() > 0
                            && !Objects.equals(post.getContent(), postInput.getContent())
            ) {
                post.setContent(postInput.getContent());
            }

            if (
                    postInput.getMedia() != null
                            && postInput.getMedia().length() > 0
                            && !Objects.equals(post.getContent(), postInput.getMedia())
            ) {
                post.setMedia(postInput.getMedia());
            }

            post.setUpdatedAt(LocalDateTime.now());
            return new Success(postRepository.save(post));
        } catch (PSQLException e) {
            return new Failed(e.getMessage());
        }

    }

    public String deletePost(Long postId) {
        if (!postRepository.existsById(postId))
            return "Post with id: " + postId + " does not exist";

        postRepository.deleteById(postId);
        return "Success";
    }

}
