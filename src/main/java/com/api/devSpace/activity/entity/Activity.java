/*
package com.api.devSpace.activity.entity;

import com.api.devSpace.comment.entity.Comment;
import com.api.devSpace.post.entity.Post;
import com.api.devSpace.space.Entity.Space;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Entity(name = "Activity")
@NoArgsConstructor
@AllArgsConstructor
public class Activity {
    @Id
    @SequenceGenerator(name = "activity_sequence", sequenceName = "activity_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "activity_sequence")
    @Column(name = "activity_id", nullable = false)
    private Long id;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private List<Post> posts;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "comment_id")
    private List<Comment> comments;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "space_id")
    private List<Space> spaces;
}
*/
