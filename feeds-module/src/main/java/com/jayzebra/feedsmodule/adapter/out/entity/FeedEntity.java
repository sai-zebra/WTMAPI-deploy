package com.jayzebra.feedsmodule.adapter.out.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.OffsetDateTime;
import java.util.UUID;

@Entity
@Table(name = "feeds")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FeedEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID) // Using UUID for IDs as is common for modern APIs
    private UUID id;

    @Column(nullable = false)
    private String title;

    @Lob // For potentially large text content
    @Column(nullable = false)
    private String message;

    @Column(nullable = false)
    private String status; // e.g., NEW, CLAIMED, COMPLETED

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private OffsetDateTime createdAt;

    // Optional: Reference to a user if feeds are assigned to users
    // private UUID assigneeId;
    // @ManyToOne or @OneToOne relationship with UserEntity can be added here if needed
}

