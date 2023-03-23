package com.api.devSpace.user.entity;

import com.api.devSpace.post.entity.Post;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity(name = "User")
@Table(name = "author", uniqueConstraints = {
        @UniqueConstraint(name = "author_unique_email", columnNames = "email")
})
@NoArgsConstructor
public class User {
    @Id
    @SequenceGenerator(name = "author_sequence", sequenceName = "author_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "author_sequence")
    @Column(name = "user_id", nullable = false)
    private Long id;

    @Column(name = "name", nullable = false, columnDefinition = "text")
    private String name;

    @Column(name = "username", nullable = false, columnDefinition = "text")
    private String username;

    @Column(name = "image", nullable = false, columnDefinition = "text")
    private String image;

    @Column(name = "bio", nullable = false, columnDefinition = "varchar(100)")
    private String bio;

    @Column(name = "email", nullable = false, columnDefinition = "text")
    private String email;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private List<Post> posts;

    @Column(name = "created_at", nullable = false, columnDefinition = "timestamp")
    private LocalDateTime createdAt;

    @Column(name = "updated_at", columnDefinition = "timestamp")
    private LocalDateTime updatedAt;
}
