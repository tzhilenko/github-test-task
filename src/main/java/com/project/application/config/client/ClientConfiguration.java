package com.project.application.config.client;

import com.project.application.config.property.GithubProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;

import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class ClientConfiguration {

    @Bean
    public WebClient githubClient(GithubProperties githubProperties) {
        return WebClient.builder().baseUrl(githubProperties.getUrl())
                .defaultHeader(HttpHeaders.ACCEPT, githubProperties.getMediaType())
                .defaultHeader(HttpHeaders.AUTHORIZATION, githubProperties.getToken())
                .build();
    }

}
