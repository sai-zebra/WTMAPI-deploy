package com.jayzebra.wtmmyworkapi.config;

import com.jayzebra.feedsmodule.domain.port.output.FeedRepositoryPort;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.jayzebra.feedsmodule.domain.service.FeedService;

@Configuration
public class BeanConfiguration {

    //FeedService Bean
    @Bean
    FeedService feedService(FeedRepositoryPort feedRepositoryPort){
        return new FeedService(feedRepositoryPort);
    }
}
