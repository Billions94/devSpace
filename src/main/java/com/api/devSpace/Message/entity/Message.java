package com.api.devSpace.Message.entity;

import com.api.devSpace.Message.enums.Status;
import com.api.devSpace.space.Entity.Space;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Entity(name = "Messages")
@NoArgsConstructor
@AllArgsConstructor
public class Message {
    @Id
    @SequenceGenerator(name = "message_sequence", sequenceName = "message_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "message_sequence")
    @Column(name = "message_id", nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "space_id")
    private Space space;

    @Column(name = "sender_name")
    private String senderName;

    @Column(name = "receiver_name")
    private String receiverName;

    @Column(name = "content")
    private String content;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "status")
    private Status status;

    @Column(name = "created_at", nullable = false, columnDefinition = "timestamp")
    private LocalDateTime createdAt;

    @Column(name = "updated_at", columnDefinition = "timestamp")
    private LocalDateTime updatedAt;

}
