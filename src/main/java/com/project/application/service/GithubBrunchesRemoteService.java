package com.project.application.service;

import com.project.application.mapping.GithubInstanceMapper;
import com.project.application.model.entity.Branch;
import com.project.application.model.github.GithubBranch;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

@Service
public class GithubBrunchesRemoteService {

    private static final int PER_PAGE = 1;

    private WebClient webClient;

    public GithubBrunchesRemoteService(WebClient webClient) {
        this.webClient = webClient;
    }

    public Flux<Branch> getBranchesByRepository(String owner, String name) {
        return getBranchesList(owner, name)
                .map(GithubInstanceMapper.INSTANCE::mapBranch);
    }

    public Flux<GithubBranch> getBranchesList(String login, String repo) {
        String url = String.format("/repos/%s/%s/branches?per_page=%s", login, repo, PER_PAGE);

        return this.webClient
                .get()
                .uri(url)
                .retrieve()
                .bodyToFlux(new ParameterizedTypeReference<>() {
                });
    }

}
