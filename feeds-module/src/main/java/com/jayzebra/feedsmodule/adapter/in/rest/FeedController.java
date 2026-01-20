package com.jayzebra.feedsmodule.adapter.in.rest;

import com.jayzebra.feedsmodule.domain.dto.FeedCreateRequestDto;
import com.jayzebra.feedsmodule.domain.dto.FeedResponseDto;
import com.jayzebra.feedsmodule.domain.model.Feed;
import com.jayzebra.feedsmodule.domain.port.input.CreateFeedUseCase;
import com.jayzebra.feedsmodule.domain.port.input.DeleteFeedUseCase;
import com.jayzebra.feedsmodule.domain.port.input.GetFeedUseCase;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/feeds")
public class FeedController {
    private final CreateFeedUseCase createFeedUseCase;
    private final GetFeedUseCase getFeedUseCase;
    private final ModelMapper modelMapper;
    private final DeleteFeedUseCase deleteFeedUseCase;
    public FeedController(CreateFeedUseCase createFeedUseCase, GetFeedUseCase getFeedUseCase, ModelMapper modelMapper, DeleteFeedUseCase deleteFeedUseCase) {
        this.createFeedUseCase = createFeedUseCase;
        this.getFeedUseCase = getFeedUseCase;
        this.modelMapper = modelMapper;
        this.deleteFeedUseCase = deleteFeedUseCase;
    }

    @GetMapping
    public ResponseEntity<List<FeedResponseDto>> getAllFeeds(){
        return ResponseEntity.ok(getFeedUseCase.getAllFeeds());
    }
    @PostMapping
    public ResponseEntity<Void> createFeed(@RequestBody FeedCreateRequestDto createRequestDto){
        createFeedUseCase.createFeed(createRequestDto);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteFeed(@PathVariable UUID id){
        deleteFeedUseCase.deleteFeed(id);
       return ResponseEntity.noContent().build();
    }
}
