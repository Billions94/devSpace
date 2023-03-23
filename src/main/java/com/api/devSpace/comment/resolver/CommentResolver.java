package com.api.devSpace.comment.resolver;

import com.api.devSpace.comment.entity.Comment;
import com.api.devSpace.comment.service.CommentService;
import lombok.AllArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@AllArgsConstructor
public class CommentResolver {
    private final CommentService commentService;

    @QueryMapping
    List<Comment> comments() {
        return commentService.comments();
    }

    @QueryMapping
    List<Comment> commentsByContent(@Argument("content") String content) {
        return commentService.commentsByContent(content);
    }

    @QueryMapping
    Object comment(@Argument("commentId") Long commentId) {
        return commentService.comment(commentId);
    }

    @MutationMapping
    Object createComment(
            @Argument("postId") Long postId,
            @Argument("commentInput") Comment commentInput
    ) {
        return commentService.createComment(postId, commentInput);
    }

    @MutationMapping
    Object updateComment(
            @Argument("commentId") Long commentId,
            @Argument("commentInput") Comment commentInput
    ) {
        return commentService.updateComment(commentId, commentInput);
    }

    @MutationMapping
    String deleteComment(@Argument("commentId") Long commentId) {
        return commentService.deleteComment(commentId);
    }
}
