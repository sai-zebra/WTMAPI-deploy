package com.jayzebra.feedsmodule.domain.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO for the PATCH /feeds/{feedId} request body.
 * Based on the FeedUpdate schema in the OpenAPI specification, using Lombok.
 * Fields are optional for a PATCH operation.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FeedUpdateRequestDto {
    private String title;
    private String message;
}

