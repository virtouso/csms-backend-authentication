package com.room.authentication.config;

import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
public class SwaggerConfig {

    private final AppConfig appConfig;

    public SwaggerConfig(AppConfig appConfig) {
        this.appConfig = appConfig;
    }

    @Bean
    public GroupedOpenApi publicApi() {
        if (appConfig.IsProduction()) {
            return null;
        }

        return GroupedOpenApi.builder()
                .group("public")
                .pathsToMatch("/**")
                .build();
    }
}
