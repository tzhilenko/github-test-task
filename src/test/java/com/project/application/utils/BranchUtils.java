package com.project.application.utils;

import com.project.application.model.entity.Branch;
import com.project.application.model.github.GithubBranch;


import static org.junit.jupiter.api.Assertions.assertEquals;

public class BranchUtils {
    public static Branch getBranch() {
        return Branch.builder()
                .name("branchName")
                .commitSha("commitSha")
                .build();
    }

    public static void validateBranchMapping(GithubBranch githubBranch, Branch branch) {
        assertEquals(githubBranch.getName(), branch.getName());
        assertEquals(githubBranch.getCommitSha(), branch.getCommitSha());

    }
}
