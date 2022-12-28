package com.project.application.utils;

import com.project.application.model.entity.Branch;
import com.project.application.model.entity.RepositoryWithBranches;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RepositoryWithBranchUtils {

    public static void validateRepositoryWithBranch(List<RepositoryWithBranches> repositoryWithBranchesList) {

        assertEquals(1, repositoryWithBranchesList.size());
        assertEquals("repoName", repositoryWithBranchesList.get(0).getName());
        assertEquals("repoOwner", repositoryWithBranchesList.get(0).getOwner());

        List<Branch> branchList = repositoryWithBranchesList.get(0).getBranches();
        assertEquals(1, branchList.size());
        assertEquals("branchName", branchList.get(0).getName());
        assertEquals("commitSha", branchList.get(0).getCommitSha());

    }

    public static void validateRepositoryWithNoBranches(List<RepositoryWithBranches> repositoryWithBranchesList) {

        assertEquals(1, repositoryWithBranchesList.size());
        assertEquals("repoName", repositoryWithBranchesList.get(0).getName());
        assertEquals("repoOwner", repositoryWithBranchesList.get(0).getOwner());

        List<Branch> branchList = repositoryWithBranchesList.get(0).getBranches();
        assertEquals(0, branchList.size());

    }

}
