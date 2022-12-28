package com.project.application.utils;

import com.project.application.model.entity.Repository;
import com.project.application.model.github.GithubRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RepositoryUtils {

    public static Repository getNotForkRepository() {
        return Repository.builder()
                .name("repoName")
                .owner("repoOwner")
                .fork(false)
                .build();
    }

    public static Repository getForkRepository() {
        return Repository.builder()
                .name("repoName")
                .owner("repoOwner")
                .fork(true)
                .build();
    }

    public static void validateRepositoryMapping(GithubRepository githubRepository, Repository repository) {

        assertEquals(githubRepository.getName(), repository.getName());
        assertEquals(githubRepository.getOwner(), repository.getOwner());
        assertEquals(githubRepository.isFork(), repository.isFork());

    }

}
