package com.jayzebra.feedsmodule.domain.port.input;

import com.jayzebra.feedsmodule.domain.dto.FeedResponseDto;
import com.jayzebra.feedsmodule.domain.model.Feed;

import java.util.List;
import java.util.UUID;

public interface GetFeedUseCase {
    FeedResponseDto getFeedById(UUID feedId);
    List<FeedResponseDto> getAllFeeds();
}
