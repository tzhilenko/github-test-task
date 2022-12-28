package com.project.application.utils;

import com.project.application.model.github.GithubBranch;

public class GithubBranchUtils {

    public static GithubBranch getGithubBranch() {
        return new GithubBranch("branchName", "commitSha");
    }
}
