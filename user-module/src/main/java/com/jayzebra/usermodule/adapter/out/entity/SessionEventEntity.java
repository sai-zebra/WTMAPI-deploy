package com.jayzebra.usermodule.adapter.out.entity;

import jakarta.persistence.Entity;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.modulith.NamedInterface;

import java.time.Instant;

@NamedInterface
@Entity
@Table(name = "session_events")
@Data
public class SessionEventEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(nullable = false)
    private String userId;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EventType eventType;

    @Column(nullable = false)
    private Instant timestamp;

    public enum EventType {
        LOGIN, LOGOUT
    }
}

