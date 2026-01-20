package com.jayzebra.feedsmodule.adapter;

import com.jayzebra.feedsmodule.adapter.out.entity.FeedEntity;
import com.jayzebra.feedsmodule.adapter.out.repository.FeedRepository;
import com.jayzebra.feedsmodule.domain.dto.FeedCreateRequestDto;
import com.jayzebra.feedsmodule.domain.dto.FeedResponseDto;
import com.jayzebra.feedsmodule.domain.model.Feed;
import com.jayzebra.feedsmodule.domain.port.output.FeedRepositoryPort;
import lombok.AllArgsConstructor;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@Repository
public class FeedRepositoryAdapter implements FeedRepositoryPort {
    private final FeedRepository feedRepository;
    private final ModelMapper modelMapper;


    @Override
    public void save(FeedCreateRequestDto feedCreateRequestDto) {
         FeedEntity feed= modelMapper.map(feedCreateRequestDto,FeedEntity.class);
         feed.setStatus("New");
          feedRepository.save(feed);
    }

    @Override
    public List<FeedResponseDto> getAllFeeds() {
        List<FeedEntity> feeds=feedRepository.findAll();
       return feeds.stream().map(feed -> modelMapper.map(feed, FeedResponseDto.class)).toList();

    }

    @Override
    public void deleteFeed(UUID feedId) {
        feedRepository.deleteById(feedId);
    }
}
