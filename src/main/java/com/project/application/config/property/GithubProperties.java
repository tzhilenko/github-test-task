package com.project.application.config.property;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties("github.api")
public class GithubProperties {
    String url;

    String mediaType;

    String token;
}
