package com.jayzebra.feedsmodule.domain.model;

import lombok.Data;
import java.time.OffsetDateTime;
import java.util.UUID;

@Data
public class Feed {
    private UUID id;
    private String title;
    private String message;
    private String status;
    private OffsetDateTime createdAt;
}

