package com.project.application.service;

import com.project.application.exception.UserNotFoundException;
import com.project.application.mapping.GithubInstanceMapper;
import com.project.application.model.entity.Repository;
import com.project.application.model.github.GithubRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class GithubRepositoriesRemoteService {

    private static final int PER_PAGE = 100;

    private final WebClient webClient;

    public GithubRepositoriesRemoteService(WebClient webClient) {
        this.webClient = webClient;
    }

    public Flux<Repository> getRepositoriesByUser(String username) {
        return getReposList(username)
                .map(GithubInstanceMapper.INSTANCE::mapRepo);
    }

    public Flux<GithubRepository> getReposList(String login) {
        String url = String.format("/users/%s/repos?per_page=%s", login, PER_PAGE);

        return this.webClient
                .get()
                .uri(url)
                .retrieve()
                .onStatus(s -> s == HttpStatus.NOT_FOUND,
                        clientResponse -> Mono.error(() -> new UserNotFoundException(login)))
                .bodyToFlux(new ParameterizedTypeReference<>() {
                });
    }


}
