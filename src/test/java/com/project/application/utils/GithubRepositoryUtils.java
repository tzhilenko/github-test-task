package com.project.application.utils;

import com.project.application.model.github.GithubRepository;

public class GithubRepositoryUtils {

    public static GithubRepository getRepository() {
        return new GithubRepository("repoName", "repoOwner", false);
    }
}
