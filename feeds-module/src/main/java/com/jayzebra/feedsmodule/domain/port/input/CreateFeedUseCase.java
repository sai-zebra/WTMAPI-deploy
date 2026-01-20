package com.jayzebra.feedsmodule.domain.port.input;


import com.jayzebra.feedsmodule.adapter.out.entity.FeedEntity;
import com.jayzebra.feedsmodule.domain.dto.FeedCreateRequestDto;


/**
 * Input Port for the use case of creating a new feed.
 */
@FunctionalInterface
public interface CreateFeedUseCase {
    void createFeed(FeedCreateRequestDto feedCreateRequestDto);
}
