package com.jayzebra.feedsmodule.domain.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.OffsetDateTime;
import java.util.UUID;

/**
 * DTO representing a single Feed item in API responses.
 * Based on the Feed schema in the OpenAPI specification, using Lombok.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FeedResponseDto {
    private UUID id;
    private String title;
    private String message;
    private String status;
    private OffsetDateTime createdAt;
}
