package com.api.devSpace.comment.service;

import com.api.devSpace.comment.entity.Comment;
import com.api.devSpace.comment.repository.CommentRepository;
import com.api.devSpace.exception.PSQLException;
import com.api.devSpace.post.entity.Post;
import com.api.devSpace.post.repository.PostRepository;
import com.api.devSpace.response.Failed;
import com.api.devSpace.response.Success;
import com.api.devSpace.user.entity.User;
import com.api.devSpace.user.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Service
@AllArgsConstructor
public class CommentService implements CommentServiceInterface {
    private final CommentRepository commentRepository;
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    public List<Comment> comments() {
        return commentRepository.findAll();
    }

    public List<Comment> commentsByContent(String content) {
        return commentRepository.findAll().stream()
                                .filter(item -> item.getContent().contains(content))
                                .toList();
    }

    public Object comment(Long commentId) {
        try {
            Comment comment = commentRepository
                    .findById(commentId)
                    .orElseThrow(() -> new PSQLException("Comment with id: " + commentId + " does not exist"));

            return new Success(comment);
        } catch (PSQLException e) {
            return new Failed(e.getMessage());
        }
    }

    public Object createComment(Long postId, Comment commentInput) {
        try {
            User user = userRepository
                    .findById(postRepository.getPostById(postId).getUser().getId())
                    .orElseThrow(() -> new PSQLException("This user does not exist"));

            Post post = postRepository
                    .findById(postId)
                    .orElseThrow(() -> new PSQLException("Post with id: " + postId + " does not exist"));

            commentInput.setPost(post);
            commentInput.setUser(user);
            commentInput.setCreatedAt(LocalDateTime.now());

            postRepository.save(post);
            userRepository.save(user);

            return new Success(commentRepository.save(commentInput).getId());
        } catch (PSQLException e) {
            return new Failed(e.getMessage());
        }
    }

    public Object updateComment(Long commentId, Comment commentInput) {
        try {
            Comment comment = commentRepository
                    .findById(commentId)
                    .orElseThrow(() -> new PSQLException("Comment with id: " + commentId + " does not exist"));

            if (
                    commentInput.getContent() != null
                            && commentInput.getContent().length() > 0
                            && !Objects.equals(comment.getContent(), commentInput.getContent())
            ) {
                comment.setContent(commentInput.getContent());
            }

            if (
                    commentInput.getMedia() != null
                            && commentInput.getMedia().length() > 0
                            && !Objects.equals(comment.getContent(), commentInput.getMedia())
            ) {
                comment.setMedia(commentInput.getMedia());
            }

            comment.setUpdatedAt(LocalDateTime.now());
            return new Success(commentRepository.save(comment));
        } catch (PSQLException e) {
            return new Failed(e.getMessage());
        }
    }

    public String deleteComment(Long commentId) {
        if (!commentRepository.existsById(commentId))
            return "Comment with id: " + commentId + " does not exist";

        commentRepository.deleteById(commentId);
        return "Success";
    }
}
