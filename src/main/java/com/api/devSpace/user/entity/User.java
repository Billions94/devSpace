package com.api.devSpace.user.entity;

import com.api.devSpace.comment.entity.Comment;
import com.api.devSpace.post.entity.Post;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

@Data
@Entity(name = "User")
@Table(name = "_user", uniqueConstraints = {
        @UniqueConstraint(name = "user_unique_email", columnNames = "email")
})
@NoArgsConstructor
@AllArgsConstructor
public class User implements UserDetails {
    @Id
    @SequenceGenerator(name = "user_sequence", sequenceName = "user_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_sequence")
    @Column(name = "user_id", nullable = false)
    private Long id;

    @Column(name = "name", nullable = false, columnDefinition = "text")
    private String name;

    @Column(name = "username", nullable = false, columnDefinition = "text")
    private String username;

    @Column(name = "image", columnDefinition = "text")
    private String image;

    @Column(name = "bio", columnDefinition = "varchar(100)")
    private String bio;

    @Column(name = "email", nullable = false, columnDefinition = "text")
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @Enumerated(value = EnumType.STRING)
    private Role role;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private List<Post> posts;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private List<Comment> comments;

    @Column(name = "created_at", nullable = false, columnDefinition = "timestamp")
    private LocalDateTime createdAt;

    @Column(name = "updated_at", columnDefinition = "timestamp")
    private LocalDateTime updatedAt;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getUsername() {
        return email;
    };

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
