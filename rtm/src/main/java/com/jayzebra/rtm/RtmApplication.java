package com.jayzebra.rtm;

import com.jayzebra.common.config.ModelMapperConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import(ModelMapperConfig.class)
public class RtmApplication {

    public static void main(String[] args) {
        SpringApplication.run(RtmApplication.class, args);
    }

}
