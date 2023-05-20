package com.api.devSpace.post.entity;

import com.api.devSpace.comment.entity.Comment;
import com.api.devSpace.user.entity.User;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity(name = "Post")
@NoArgsConstructor
public class Post {
    @Id
    @SequenceGenerator(name = "post_sequence", sequenceName = "post_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "post_sequence")
    @Column(name = "post_id", nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "content", nullable = false, columnDefinition = "text")
    private String content;

    @Column(name = "media", columnDefinition = "text")
    private String media;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "post_id")
    private List<Comment> comments;

    @Column(name = "created_at", nullable = false, columnDefinition = "timestamp")
    private LocalDateTime createdAt;

    @Column(name = "updated_at", columnDefinition = "timestamp")
    private LocalDateTime updatedAt;
}
