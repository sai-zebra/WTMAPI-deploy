package com.jayzebra.feedsmodule.domain.port.input;


import com.jayzebra.feedsmodule.domain.dto.FeedUpdateRequestDto;

/**
 * Input Port for the use case of updating an existing feed.
 */
@FunctionalInterface
public interface UpdateFeedUseCase {
    void updateFeed(FeedUpdateRequestDto feedUpdateRequestDto);
}

