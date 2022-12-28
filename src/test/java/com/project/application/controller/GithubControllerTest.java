package com.project.application.controller;

import com.project.application.exception.ErrorAttributes;
import com.project.application.exception.UserNotFoundException;
import com.project.application.model.entity.RepositoryWithBranches;
import com.project.application.service.GithubBrunchesRemoteService;
import com.project.application.service.GithubRepositoriesRemoteService;
import com.project.application.service.GithubService;
import com.project.application.utils.RepositoryWithBranchUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;

import static com.project.application.utils.BranchUtils.getBranch;
import static com.project.application.utils.RepositoryUtils.getForkRepository;
import static com.project.application.utils.RepositoryUtils.getNotForkRepository;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@WebFluxTest(GithubController.class)
@Import(GithubService.class)
public class GithubControllerTest {

    @Autowired
    WebTestClient webClient;

    @MockBean
    GithubRepositoriesRemoteService repositories;

    @MockBean
    GithubBrunchesRemoteService brunches;


    @Test
    void shouldReturnListRepositoriesAndBranches() {

        when(repositories.getRepositoriesByUser(any())).thenReturn(Flux.just(getNotForkRepository()));
        when(brunches.getBranchesByRepository(any(), any())).thenReturn(Flux.just(getBranch()));

        webClient.get()
                .uri("/github/repositories/username")
                .header(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE)
                .exchange()
                .expectStatus()
                .isOk()
                .expectBodyList(RepositoryWithBranches.class)
                .value(RepositoryWithBranchUtils::validateRepositoryWithBranch);
    }

    @Test
    void shouldFilterForkRepositories() {

        when(repositories.getRepositoriesByUser(any())).thenReturn(Flux.just(getNotForkRepository(), getForkRepository()));
        when(brunches.getBranchesByRepository(any(), any())).thenReturn(Flux.just(getBranch()));

        webClient.get()
                .uri("/github/repositories/username")
                .header(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE)
                .exchange()
                .expectStatus()
                .isOk()
                .expectBodyList(RepositoryWithBranches.class)
                .value(RepositoryWithBranchUtils::validateRepositoryWithBranch);
    }

    @Test
    void shouldReturnEmptyListForUserWithNoRepositories() {

        when(repositories.getRepositoriesByUser(any())).thenReturn(Flux.empty());

        webClient.get()
                .uri("/github/repositories/username")
                .header(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE)
                .exchange()
                .expectStatus()
                .isOk()
                .expectBodyList(RepositoryWithBranches.class)
                .value(r -> assertEquals(0, r.size()));
    }

    @Test
    void shouldReturnEmptyListForRepositoriesWithoutBranches() {

        when(repositories.getRepositoriesByUser(any())).thenReturn(Flux.just(getNotForkRepository()));
        when(brunches.getBranchesByRepository(any(), any())).thenReturn(Flux.empty());

        webClient.get()
                .uri("/github/repositories/username")
                .header(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE)
                .exchange()
                .expectStatus()
                .isOk()
                .expectBodyList(RepositoryWithBranches.class)
                .value(RepositoryWithBranchUtils::validateRepositoryWithNoBranches);
    }

    @Test
    void shouldReturn404ErrorForUnknownUser() {

        String username = "user";
        String errorMsg = String.format("User %s not found", username);

        when(repositories.getRepositoriesByUser(any())).thenThrow(new UserNotFoundException(username));

        webClient.get()
                .uri(String.format("/github/repositories/%s", username))
                .header(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE)
                .exchange()
                .expectStatus()
                .is4xxClientError()
                .expectBodyList(ErrorAttributes.class)
                .value(er -> assertEquals(errorMsg, er.get(0).getMessage()));
    }

    @Test
    void shouldReturn406ErrorForIncorrectResponseMediaType() {

        String username = "user";
        String errorMsg = "Content type not supported, allowed content type: application/json";

        when(repositories.getRepositoriesByUser(any())).thenThrow(new UserNotFoundException(username));

        webClient.get()
                .uri(String.format("/github/repositories/%s", username))
                .header(HttpHeaders.ACCEPT, MediaType.APPLICATION_XML_VALUE)
                .exchange()
                .expectStatus()
                .is4xxClientError()
                .expectBodyList(ErrorAttributes.class)
                .value(er -> assertEquals(errorMsg, er.get(0).getMessage()));
    }

}

