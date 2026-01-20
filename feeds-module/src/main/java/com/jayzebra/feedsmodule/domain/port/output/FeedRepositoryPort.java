package com.jayzebra.feedsmodule.domain.port.output;

import com.jayzebra.feedsmodule.domain.dto.FeedCreateRequestDto;
import com.jayzebra.feedsmodule.domain.dto.FeedResponseDto;
import com.jayzebra.feedsmodule.domain.model.Feed;

import java.util.List;
import java.util.UUID;

public interface FeedRepositoryPort {

    void save(FeedCreateRequestDto feedCreateRequestDto);

    List<FeedResponseDto> getAllFeeds();

    void deleteFeed(UUID feedId);
}
